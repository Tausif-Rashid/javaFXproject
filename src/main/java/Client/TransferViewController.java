package Client;

import SearchFunctions.Movie;
import SearchFunctions.MovieList;
import SearchFunctions.ProductionCompany;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.DataPacket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class TransferViewController {

    public static List <String> companyList = new ArrayList<>();

    public TableView tableView;
    public TextField userInput;

    public String command;
    public TextField companyChoice;
    public Label selectedLabel;

    MovieTableList movieTableList = new MovieTableList(ClientMain.moviesOfCompany);

    ObservableList<MovieTableView> data;

    private boolean init = true;

    public void setMain(ClientMain main) {
        this.main = main;
    }

    public TransferViewController getController()
    {
        return this;
    }

    public Movie selectedMovie;
    public String selectedCompany="";
    public String selfName;

    ClientMain main;


    private void initializeColumns() {
        TableColumn<MovieTableView, String> titleCol = new TableColumn<>("Title");
        titleCol.setMinWidth(180);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("titleString"));

        TableColumn<MovieTableView, String> releaseYear = new TableColumn<>("Release Year");
        releaseYear.setMinWidth(130);
        releaseYear.setCellValueFactory(new PropertyValueFactory<>("releaseYearString"));

        TableColumn<MovieTableView, String> revenue = new TableColumn<>("Revenue");
        revenue.setMinWidth(150);
        revenue.setCellValueFactory(new PropertyValueFactory<>("revenueString"));


        TableColumn<MovieTableView, String> buttonCol = new TableColumn<>("Select Movie");
        buttonCol.setMinWidth(150);
        buttonCol.setCellValueFactory(new PropertyValueFactory<>("buttonSelect"));


        tableView.getColumns().addAll(titleCol, revenue, buttonCol);


    }

    public void load() throws IOException {
        selfName = ClientMain.company.getCompanyName();
        System.out.println("table loaded");
        if (init) {
            initializeColumns();

            init = false;
        }

        List<MovieTableView> tableList= movieTableList.getList();

        for (MovieTableView m : tableList)
        {
            m.setMain(this);
        }


        data = FXCollections.observableArrayList(movieTableList.getList());

        //data.add(ClientMain.moviesOfCompany.get(0));

        tableView.setEditable(true);
        tableView.setItems(data);

        DataPacket dp = new DataPacket();
        dp.setCommand("GetCompanyList");
        main.getSocketWrapper().write(dp);
    }


    public void OnBackClick(ActionEvent actionEvent) throws Exception {
        main.showHomePage(ClientMain.company.getCompanyName());
    }


    public void OnSearchClick(ActionEvent actionEvent) {

        List<MovieTableView> lst = new ArrayList<>();

        if (userInput.getText().length() >= 1)
            lst = movieTableList.getListTitle(userInput.getText());
        else{
            lst = movieTableList.getList();
        }

        for (MovieTableView m : lst)
        {
            m.setMain(this);
        }

        data = FXCollections.observableArrayList(lst);
            if (lst != null) {
            tableView.setItems(data);
            tableView.refresh();
        }

    }

    public void TransferMovie(MovieTableView mov){

        Movie m = mov.getMovie();
        selectedMovie = m ;

        selectedLabel.setText("Selected Movie : " + m.getTitle() + "\nSelected company : " +selectedCompany);
    }

    public void OnSelectCompany(ActionEvent actionEvent) throws IOException {

         selectedCompany = companyChoice.getText() ;

        if(selectedMovie!=null)
            selectedLabel.setText("Selected Movie : " + selectedMovie.getTitle() + "\nSelected Company : " + selectedCompany);
        else
            selectedLabel.setText("Selected Movie : "  + "\nSelected Company : " + selectedCompany);
    }

    public void OnTransferClick(ActionEvent actionEvent) throws IOException {

        if(selectedCompany.length()<2 || selectedMovie==null)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Please select both company and movie");
            a.showAndWait();
        }
        else if(selectedCompany.equalsIgnoreCase(ClientMain.company.getCompanyName()))
        {

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Cannot transfer to self");
            a.showAndWait();
        }
        else
        {
            int foundStatus =0;
            for (String s : companyList)
            {
                if(s.equalsIgnoreCase(selectedCompany))
                {
                    System.out.println(s);
                    foundStatus = 1;
                    break;
                }
            }

            if(foundStatus==0)
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Company Not Found");
                a.showAndWait();
            }

            else
            {
                DataPacket dp = new DataPacket();

                dp.setCommand("Transfer");
                dp.setCompanyName(selectedCompany);

                dp.setNewMovie(selectedMovie);
                dp.setCompanyNameFrom(selfName);
                main.getSocketWrapper().write(dp);


                ClientMain.moviesOfCompany.remove(selectedMovie);
                ClientMain.company = new ProductionCompany(selfName , ClientMain.moviesOfCompany);
                ClientMain.movieList = new MovieList(ClientMain.moviesOfCompany);
                movieTableList = new MovieTableList(ClientMain.moviesOfCompany);

                System.out.println("Transferring to srvr");

                data = FXCollections.observableArrayList(movieTableList.getList());
                //data.remove(new MovieTableView(selectedMovie));
                tableView.setItems(data);
                tableView.refresh();

                selectedMovie=null;
                selectedCompany = null;
                selectedLabel.setText("");
                companyChoice.setText("");




                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Transfer Complete");
                a.showAndWait();


            }
        }
    }
}
