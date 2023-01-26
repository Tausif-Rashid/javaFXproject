package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.DataPacket;

import java.io.IOException;

public class HomeViewController {

    public Label summaryLabel;
    private ClientMain main;

    @FXML
    private Label message;

    @FXML
    private ImageView image;

    @FXML
    private Button button;

    public void init(String msg) {
        message.setText(msg);
        Image img = new Image(ClientMain.class.getResourceAsStream("1.png"));
        image.setImage(img);


    }



    @FXML
    void logoutAction(ActionEvent event) {
        try {

            DataPacket dp= new DataPacket();
            dp.setCommand("Logout");
            main.getSocketWrapper().write(dp);
            main.showLoginPage();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setMain(ClientMain main) {
        this.main = main;
        System.out.println("Home main accessed");
    }

    public void ByTitleAction(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("search_view.fxml"));
        //System.out.println("in show page");
        Parent root = loader.load();
        // Loading the controller
        SearchViewController controller = loader.getController();
        controller.setMain(main);
        controller.setNull();

        Stage stage = main.getStage();
        // Set the primary stage
        stage.setTitle("Search By Title");
        stage.setScene(new Scene(root, 1024, 720));
        stage.show();
    }
    public void ByRelYearAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("search_view_releaseYr.fxml"));
        //System.out.println("in show page");
        Parent root = loader.load();
        // Loading the controller
        SearchViewReleaseYrController controller = loader.getController();
        controller.setMain(main);
        Stage stage = main.getStage();
        // Set the primary stage
        stage.setTitle("Search By Release Year");
        stage.setScene(new Scene(root, 1024, 720));
        stage.show();
    }

    public void ByGenreAction(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("search_view_genre.fxml"));
        //System.out.println("in show page");
        Parent root = loader.load();
        // Loading the controller
        SearchViewGenreController controller = loader.getController();
        controller.setMain(main);
        controller.load();
        Stage stage = main.getStage();
        // Set the primary stage
        stage.setTitle("Search By Genre");
        stage.setScene(new Scene(root, 1024, 720));
        stage.show();

    }

    public void ByRuntimeAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("search_view_Runtime.fxml"));
        //System.out.println("in show page");
        Parent root = loader.load();
        // Loading the controller
        SearchViewRuntimeController controller = loader.getController();
        controller.setMain(main);
        Stage stage = main.getStage();
        // Set the primary stage
        stage.setTitle("Search By Runtime");
        stage.setScene(new Scene(root, 1024, 720));
        stage.show();
    }


    public void ByRecentAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("table_view.fxml"));
        //System.out.println("in show page");
        Parent root = loader.load();
        // Loading the controller
        TableViewController controller = loader.getController();
        controller.setMain(main);
        controller.setCommand("ShowRecent");
        controller.load();
        Stage stage = main.getStage();
        // Set the primary stage
        stage.setTitle("Recent Movies");
        stage.setScene(new Scene(root, 1024, 720));
        stage.show();
    }

    public void ByMaxRevAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("table_view.fxml"));
        //System.out.println("in show page");
        Parent root = loader.load();
        // Loading the controller
        TableViewController controller = loader.getController();
        controller.setMain(main);
        controller.setCommand("ShowMaxRev");
        controller.load();
        Stage stage = main.getStage();

        // Set the primary stage
        stage.setTitle("Maximum Revenue");
        stage.setScene(new Scene(root, 1024, 720));
        stage.show();
    }

    public void ByTotalAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("table_view.fxml"));
        //System.out.println("in show page");
        Parent root = loader.load();
        // Loading the controller
        TableViewController controller = loader.getController();
        controller.setMain(main);
        controller.setCommand("ShowTotalProf");
        controller.load();
        Stage stage = main.getStage();
        // Set the primary stage
        stage.setTitle("Total Profit");
        stage.setScene(new Scene(root, 1024, 720));
        stage.show();
    }


    public void OnAddClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("add_movie_view.fxml"));
        //System.out.println("in show page");
        Parent root = loader.load();
        // Loading the controller
        AddMovieViewController controller = loader.getController();
        controller.setMain(main);

        Stage stage = main.getStage();
        // Set the primary stage
        stage.setTitle("Add New Movie");
        stage.setScene(new Scene(root, 1024, 720));
        stage.show();
    }

    public void ByAllAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("table_view.fxml"));
        //System.out.println("in show page");
        Parent root = loader.load();
        // Loading the controller
        TableViewController controller = loader.getController();
        controller.setMain(main);
        controller.setCommand("ShowAll");
        controller.load();
        Stage stage = main.getStage();
        // Set the primary stage
        stage.setTitle("List of Movies");
        stage.setScene(new Scene(root, 1024, 720));
        stage.show();
    }

    public void OnTransferClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("transfer_view.fxml"));
        //System.out.println("in show page");
        Parent root = loader.load();
        // Loading the controller
        TransferViewController controller = loader.getController();
        controller.setMain(main);
        controller.load();
        Stage stage = main.getStage();
        // Set the primary stage
        stage.setTitle("List of Movies");
        stage.setScene(new Scene(root, 1024, 720));
        stage.show();
    }
}
