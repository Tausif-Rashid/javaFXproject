package Client;

import SearchFunctions.Movie;
import SearchFunctions.MovieList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SearchViewController {


    public Label infoViewLabel;

    public ClientMain main;
    public TextField userInput;
    public Label titleLabelBig;

    public void setCommand(String command) {
        this.command = command;
    }

    public String command;

    public void setMain(ClientMain main) {
    this.main = main;
    }

    public void OnSearchClick(ActionEvent actionEvent) {
        String input = userInput.getText();

        Movie movie =ClientMain.movieList.searchMovieTitle(input);

        if(movie==null)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Movie not found" );
            a.showAndWait();
        }
        else
        {
           String info = movie.printAllInfo();
           infoViewLabel.setText(info);
           titleLabelBig.setText(movie.getTitle());
        }
    }

    public void setNull() {
        userInput.setText("");

        infoViewLabel.setText("");
        titleLabelBig.setText("");
    }

    public void OnBackClick(ActionEvent actionEvent) throws Exception {
        main.showHomePage(ClientMain.company.getCompanyName());
    }

    public void OnTestAction(ActionEvent actionEvent) {
        OnSearchClick(actionEvent);
    }
}
