package Client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;
import java.util.List;

public class SearchViewRuntimeController {

    public ClientMain main;

    public TextField userInput;
    public TextField userInput2;

    private int userInputInt;
    private int userInputInt2;


    public void setCommand(String command) {
        this.command = command;
    }
    private boolean init = true;

    public String command;

    public void setMain(ClientMain main) {
        this.main = main;
    }

    MovieTableList movieTableList =new MovieTableList(ClientMain.moviesOfCompany);

    @FXML
    private TableView tableView;

    ObservableList<MovieTableView> data;

    private void initializeColumns() {
        TableColumn<MovieTableView, String> titleCol = new TableColumn<>("Title");
        titleCol.setMinWidth(280);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("titleString"));

        TableColumn<MovieTableView, String> releaseYear = new TableColumn<>("Release Year");
        releaseYear.setMinWidth(130);
        releaseYear.setCellValueFactory(new PropertyValueFactory<>("releaseYearString"));

        TableColumn<MovieTableView, String> runtime = new TableColumn<>("Runtime");
        runtime.setMinWidth(180);
        runtime.setCellValueFactory(new PropertyValueFactory<>("runtimeString"));

        TableColumn<MovieTableView, String> buttonCol = new TableColumn<>("View");
        buttonCol.setMinWidth(180);
        buttonCol.setCellValueFactory(new PropertyValueFactory<>("button"));


        tableView.getColumns().addAll(titleCol, releaseYear, runtime, buttonCol);
        System.out.println("table loaded");

    }

    public void load() {

        if (init) {
            initializeColumns();

            init = false;
        }

        List<MovieTableView> lst = movieTableList.getListRuntime(userInputInt , userInputInt2);

        if(lst.size()==0)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("No Movies Found with this release year" );
            a.showAndWait();
        }

        data = FXCollections.observableArrayList(lst);

        //data.add(ClientMain.moviesOfCompany.get(0));

        tableView.setEditable(true);
        tableView.setItems(data);
    }

    public void OnBackClick(ActionEvent actionEvent) throws Exception {
        main.showHomePage(ClientMain.company.getCompanyName());
    }

    public void OnSearchClick(ActionEvent actionEvent) {
        String input1 = userInput.getText();
        String input2 = userInput2.getText();

        try{
            userInputInt = Integer.parseInt(input1);
            userInputInt2 = Integer.parseInt(input2);
            load();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Release Year must be an integer!" );
            a.showAndWait();
        }

    }
}
