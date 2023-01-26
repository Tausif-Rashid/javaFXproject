package Client;

import SearchFunctions.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TableViewController {

    public void setTableLabel(Label tableLabel) {
        this.tableLabel = tableLabel;
    }

    public Label tableLabel;

    public Label profitTotal;
    @FXML
    private TableView tableView;

    public void setMain(ClientMain main) {
        this.main = main;
    }

    ClientMain main;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    String command;




    ObservableList<MovieTableView> data;

    MovieTableList movieTableList =new MovieTableList(ClientMain.moviesOfCompany);

    @FXML
    private Button button;

    private boolean init = true;

    private void initializeColumns() {
        TableColumn<MovieTableView, String> titleCol = new TableColumn<>("Title");
        titleCol.setMinWidth(280);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("titleString"));

        TableColumn<MovieTableView, String> releaseYear = new TableColumn<>("Release Year");
        releaseYear.setMinWidth(130);
        releaseYear.setCellValueFactory(new PropertyValueFactory<>("releaseYearString"));

        TableColumn<MovieTableView, String> revenue = new TableColumn<>("Revenue");
        revenue.setMinWidth(180);
        revenue.setCellValueFactory(new PropertyValueFactory<>("revenueString"));

        TableColumn<MovieTableView, String> profit = new TableColumn<>("Profit");
        profit.setMinWidth(180);
        profit.setCellValueFactory(new PropertyValueFactory<>("profitString"));


        TableColumn<MovieTableView, String> buttonCol = new TableColumn<>("View");
        buttonCol.setMinWidth(180);
        buttonCol.setCellValueFactory(new PropertyValueFactory<>("button"));

        if(command.equals("ShowTotalProf"))
        {
            tableView.getColumns().addAll(titleCol, releaseYear,profit, buttonCol);

            profitTotal.setText("Total Profit: " + ClientMain.company.getTotalProfit());
        }
        else {
            tableView.getColumns().addAll(titleCol, releaseYear, revenue, buttonCol);
            profitTotal.setText(" ");
        }


    }

    public void load() {
        System.out.println("table loaded");
        if (init) {
            initializeColumns();

            init = false;
        }

        HashMap<String,String> LabelMap = new HashMap<>();
        LabelMap.put("ShowRecent" , "Recent Movies");
        LabelMap.put("ShowMaxRev" , "Maximum Revenue");
        LabelMap.put("ShowTotalProf" , "Total Profit");
        LabelMap.put("ShowAll" , "List of Movies");

        tableLabel.setText(LabelMap.get(command));


        data = FXCollections.observableArrayList(movieTableList.getRequestList(command));

        //data.add(ClientMain.moviesOfCompany.get(0));


        tableView.setEditable(true);
        tableView.setItems(data);
    }


    public void OnBackClick(ActionEvent actionEvent) throws Exception {
        main.showHomePage(ClientMain.company.getCompanyName());
    }

    public void OnReloadClick(ActionEvent actionEvent) {

        movieTableList = new MovieTableList(ClientMain.moviesOfCompany);
        data = FXCollections.observableArrayList(movieTableList.getRequestList(command));
        //data.add(ClientMain.moviesOfCompany.get(0));
        tableView.setEditable(true);
        tableView.setItems(data);
        tableView.refresh();

    }
}