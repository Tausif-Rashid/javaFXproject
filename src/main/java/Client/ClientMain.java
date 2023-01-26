package Client;

import SearchFunctions.Movie;
import SearchFunctions.MovieList;
import SearchFunctions.ProductionCompany;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.DataPacket;
import utils.SocketWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ClientMain extends Application {

    private Stage stage;
    public static List<Movie> moviesOfCompany = new ArrayList<>();

    public static ProductionCompany company;
    public static MovieList movieList;
    private SocketWrapper socketWrapper;


    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }

    public Stage getStage() {
        return stage;
    }

    public SocketWrapper getSocketWrapper() {
        return socketWrapper;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        //ystem.out.println("started");
        connectToServer();
        showLoginPage();

        stage.setOnCloseRequest(event -> {
            logoutAction();
        });

    }

    void logoutAction() {
        try {
            this.showLoginPage();
            DataPacket dp= new DataPacket();
            dp.setCommand("Logout");
            this.getSocketWrapper().write(dp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void connectToServer() throws IOException {

        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        socketWrapper = new SocketWrapper(serverAddress, serverPort);

        new ReadThread(this);

        //System.out.println("connect");
    }

    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("login_main.fxml"));
        //System.out.println("in show page");
        Parent root = loader.load();


        System.out.println("fxml loaded");
        // Loading the controller
        LoginMainController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 1024, 720));
        stage.show();
    }

    public void showHomePage(String userName) throws Exception {



        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home_view.fxml"));
        Parent root = loader.load();

        // Loading the controller
        HomeViewController controller = loader.getController();

        controller.init(userName);
        System.out.println("home controller");
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 1024, 720));
        stage.show();
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }
}