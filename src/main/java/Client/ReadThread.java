package Client;

import SearchFunctions.Movie;
import SearchFunctions.MovieList;
import SearchFunctions.ProductionCompany;
import javafx.application.Platform;
import server.ServerMain;
import utils.DataPacket;
import utils.SocketWrapper;

import java.io.IOException;

public class ReadThread implements Runnable {


    private Thread thread;
    private ClientMain main;
    private SocketWrapper socketWrapper;

    public static boolean exitStatus;

    public ReadThread(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
        this.thread = new Thread(this);
        thread.start();
    }

    public ReadThread(ClientMain main) {
        this.socketWrapper = main.getSocketWrapper();
        this.main = main;
        this.thread = new Thread(this);
        exitStatus=false;
        thread.start();
    }

    public void run() {
        try {
            while (true) {

                Object o = socketWrapper.read();

                if (o != null) {
                    if (o instanceof DataPacket) {
                        DataPacket obj = (DataPacket) o;
                        System.out.println("Received: " + obj.getUserName() + ", " + obj.isStatus());


                        if(obj.getCommand().equalsIgnoreCase("LoginAttempt")){
                            if (obj.getMovieList() != null) {
                                ClientMain.moviesOfCompany = obj.getMovieList();
                                ClientMain.company = new ProductionCompany(obj.getUserName(), obj.getMovieList());
                                ClientMain.movieList = new MovieList(obj.getMovieList());
                                for (Movie c : ClientMain.company.getCompanyMovie()) {

                                    System.out.println(c.getTitle());
                                }
                            }
//                        if(obj.getUserName().equals("exit"))
//                        {
//                            break;
//                        }
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (obj.isStatus()) {
                                        try {
                                            main.showHomePage(obj.getUserName());

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        main.showAlert();
                                    }

                                }
                            });
                        } else if(obj.getCommand().equalsIgnoreCase("CheckMovie")) {
                            if(obj.getId()==-1)
                            {
                                System.out.println("Movie already exists");
                                AddMovieViewController.addStatus=1;
                            }
                            else
                            {
                                System.out.println("Movie does not exist");
                            }
                        }
                        else if(obj.getCommand().equalsIgnoreCase("GetCompanyList")) {

                            TransferViewController.companyList = obj.getCompanyNames();
                        }
                        else if(obj.getCommand().equalsIgnoreCase("Transfer")) {

                            System.out.println("Transfer Happened");


                            Movie m = obj.getNewMovie();

                                m.setProductionCompany(obj.getCompanyName());

                                ClientMain.moviesOfCompany.add(m);
                                ClientMain.company = new ProductionCompany(obj.getCompanyName(), ClientMain.moviesOfCompany);
                                ClientMain.movieList = new MovieList(ClientMain.moviesOfCompany);


                        }
                        else if(obj.getCommand().equalsIgnoreCase("Exit")) {

                            break;
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
