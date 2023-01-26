package Client;

import SearchFunctions.ProductionCompany;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.DataPacket;

import java.io.IOException;


public class LoginMainController {

    public Button ExitButton;
    public ImageView loginImage;
    private ClientMain main;

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;

    @FXML
    void loginAction(ActionEvent event) {
        String userName = userText.getText();
        String password = passwordText.getText();



        DataPacket dp = new DataPacket();
        dp.setUserName(userName);
        dp.setPassword(password);
        dp.setCommand("login");
        try {
            main.getSocketWrapper().write(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void resetAction(ActionEvent event) {
        userText.setText(null);
        passwordText.setText(null);
    }

    void setMain(ClientMain main) {
        this.main = main;
        System.out.println("main changed");

        loginImage.setImage(new Image(ClientMain.class.getResourceAsStream("2.png")));
    }

    public void exitAction(ActionEvent actionEvent) throws IOException {


        ReadThread.exitStatus=true;
        System.out.println("exiting");

        DataPacket dp= new DataPacket();
        if(ClientMain.moviesOfCompany.size()>0)
        {
            dp.setCompanyNameFrom(ClientMain.company.getCompanyName());
        }

        dp.setCommand("Exit");
        main.getSocketWrapper().write(dp);

        Platform.exit();
    }
}
