package server;

import SearchFunctions.Movie;
import SearchFunctions.ProdCompanyList;
import SearchFunctions.ProductionCompany;
import javafx.stage.Stage;
import utils.SocketWrapper;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ServerMain {


    // public  static ProdCompanyList prodCompanyList;
    public static List<Movie> movies = new ArrayList<>(); //all movies from file

    public static ProdCompanyList companyList; //List of all production companies for generating hashmap of id pass

    private ServerSocket serverSocket;



    public static HashMap<String, SocketWrapper> clientMap=new HashMap<>();

    public static HashMap<String, String> userMap;

    public static FileAccess fileAccess;

    ServerMain() {

        userMap = new HashMap<>();
        List<ProductionCompany> pc = companyList.getListCompany();

        for(ProductionCompany p : pc)
        {
            String key = p.getCompanyName();
            String content = p.getCompanyName();
            userMap.put(key, content);

        }
        //System.out.println(movies.get(0).getTitle() +userMap.get("Universal Pictures"));

        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                System.out.println("waiting for a client");
                Socket clientSocket = serverSocket.accept(); // gets a client socket, a good idea to  store in hashmap with name bcz multi clients
                System.out.println("client connected");
                serve(clientSocket);
                System.out.println("serve done");
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    // Server searches movie list for company name
    public static List<Movie> searchMovieCompany(List<Movie> longList, String companyName) { //Search with company. return the movie list

        List<Movie> moviesCompany = new ArrayList<>();
        for (Movie movie : longList) {

            if (movie.getProductionCompany().equalsIgnoreCase(companyName))
                moviesCompany.add(movie);

        }

        return moviesCompany;
    }

    public static void main(String[] args) {

        fileAccess = new FileAccess();
        //TODO find a way to send desired movies without static

        movies = fileAccess.getMovieList();

        companyList = new ProdCompanyList(movies);

        System.out.println("Movie loaded. server starting");

        new ServerMain();
    }

    public void serve(Socket clientSocket) throws IOException {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket); // send the client socket to read thread
        new ReadThreadServer(socketWrapper);
        //new WriteThread(socketWrapper, "Server");
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

}
