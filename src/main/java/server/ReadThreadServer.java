package server;


import SearchFunctions.Movie;
import SearchFunctions.ProdCompanyList;
import SearchFunctions.ProductionCompany;
import utils.DataPacket;
import utils.SocketWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadThreadServer implements Runnable {
    private Thread thread;
    private SocketWrapper socketWrapper;

    public ReadThreadServer(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
        this.thread = new Thread(this);
        thread.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = socketWrapper.read();
                if (o != null) {
                    if (o instanceof DataPacket) {
                        DataPacket obj = (DataPacket) o;

                        if (obj.getCommand().equals("login")) {

                            System.out.println("server read");


                            // obj.getPassword() != null && obj.getUserName() != null
                            System.out.println("READER THREAD: Received username :" + obj.getUserName() + "pass :" + obj.getPassword());
                            String password = ServerMain.userMap.get(obj.getUserName());
                            System.out.println("actual pass :" + password);
                            if (obj.getPassword() != null) {
                                obj.setStatus(obj.getPassword().equals(password));
                                System.out.println(obj.isStatus());
                            } else
                                obj.setStatus(false);


                            if (obj.isStatus()) {
                                List<Movie> newList = ServerMain.searchMovieCompany(ServerMain.movies, obj.getUserName());

                                //TODO find a way to send desired movies without static

                                ServerMain.clientMap.put(obj.getUserName(), socketWrapper);
                                //System.out.println("wrapper is null "+(socketWrapper == null));

                                obj.setMovieList(newList);

                                obj.setCommand("LoginAttempt");

                            } else {
                                obj.setMovieList(null);

                                obj.setCommand("LoginAttempt");
                            }
                            socketWrapper.write(obj);

                        } else if (obj.getCommand().equals("CheckMovie")) {
                            System.out.println("check command ");
                            obj.setId(0);

                            for (Movie m : ServerMain.movies) {
                                if (m.getTitle().equalsIgnoreCase(obj.getTitle())) {
                                    obj.setId(-1);
                                    break;
                                }
                            }
                            socketWrapper.write(obj);
                        } else if (obj.getCommand().equals("AddMovie")) {
                            System.out.println("add command ");

                            ServerMain.movies.add(obj.getNewMovie());

                            ServerMain.companyList = new ProdCompanyList(ServerMain.movies);

                            System.out.println("Serverside: " + ServerMain.movies);

                        } else if (obj.getCommand().equals("GetCompanyList")) {
                            System.out.println("tr command got");

                            List<String> cmp = new ArrayList<>();

                            for (ProductionCompany pc : ServerMain.companyList.getListCompany()) {
                                cmp.add(pc.getCompanyName());
                            }

                            obj.setCompanyNames(cmp);

                            socketWrapper.write(obj);

                        } else if (obj.getCommand().equals("Transfer")) {
                            System.out.println("transfer command got " + obj.getNewMovie().getTitle());

                            for (int i = 0; i < ServerMain.movies.size(); i++) {

                                if (ServerMain.movies.get(i).getTitle().equalsIgnoreCase(obj.getNewMovie().getTitle())) {
                                    ServerMain.movies.get(i).setProductionCompany(obj.getCompanyName());
                                    System.out.println(ServerMain.movies.get(i).getTitle() + ServerMain.movies.get(i).getProductionCompany());
                                    break;
                                }

                            }

                            ServerMain.companyList = new ProdCompanyList(ServerMain.movies);

                            SocketWrapper s = ServerMain.clientMap.get(obj.getCompanyName());

                            if (s != null) {
                                s.write(obj);
                                System.out.println("sent to second instance");
                            }

                        } else if (obj.getCommand().equals("Logout")) {
                            System.out.println("logout command");

                            ServerMain.fileAccess.writeToFile();

                            //socketWrapper.write(obj);

                        } else if (obj.getCommand().equals("Exit")) {


                            if (ServerMain.clientMap.get(obj.getCompanyNameFrom()) != null)
                                ServerMain.clientMap.remove(obj.getCompanyNameFrom());

                            if (socketWrapper != null)
                                socketWrapper.write(obj);

                        }


                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



