package Client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchViewGenreController {

    public ClientMain main;
    public CheckBox checkAnimation;
    public CheckBox checkComedy;
    public CheckBox checkFamily;
    public CheckBox checkAdventure;
    public CheckBox checkFantasy;
    public CheckBox checkAction;
    public CheckBox checkCrime;
    public CheckBox checkDrama;
    public CheckBox checkMystery;
    public CheckBox checkThriller;
    public CheckBox checkRomance;
    public CheckBox checkHorror;
    public CheckBox checkHistory;
    public CheckBox checkWar;
    public CheckBox checkScienceFiction;

    public void setMain(ClientMain main) {
        this.main = main;
    }

    MovieTableList movieTableList = new MovieTableList(ClientMain.moviesOfCompany);

    @FXML
    private TableView tableView;
    private boolean init = true;

    ObservableList<MovieTableView> data;

    List<String> genre = new ArrayList<>();

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

        List<MovieTableView> lst = movieTableList.getListGenre(genre);

        data = FXCollections.observableArrayList(lst);

        //data.add(ClientMain.moviesOfCompany.get(0));

        tableView.setEditable(true);
        tableView.setItems(data);

    }

    public void OnBackClick(ActionEvent actionEvent) throws Exception {
        main.showHomePage(ClientMain.company.getCompanyName());
    }

    void checkBoxAction(CheckBox box) {
        if (box.isSelected()) {
            genre.add(box.getText());
            List<MovieTableView> lst = movieTableList.getListGenre(genre);
            System.out.println("size of list now action " + lst.size());
            data = FXCollections.observableArrayList(lst);
            tableView.setItems(data);
            tableView.refresh();
            if (lst.size() == 0) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("No Movies Found of this genre");
                a.showAndWait();
            }
        } else {
            genre.remove(box.getText());
            List<MovieTableView> lst = movieTableList.getListGenre(genre);
            data = FXCollections.observableArrayList(lst);
            tableView.setItems(data);
            tableView.refresh();
        }
    }


    public void OnAnimationCheck(ActionEvent actionEvent) {

        checkBoxAction(checkAnimation);
    }

    public void OnComedyCheck(ActionEvent actionEvent) {
        checkBoxAction(checkComedy);
    }

    public void OnFamilyCheck(ActionEvent actionEvent) {
        checkBoxAction(checkFamily);
    }

    public void OnAdventureCheck(ActionEvent actionEvent) {
        checkBoxAction(checkAdventure);
    }

    public void OnFantasyCheck(ActionEvent actionEvent) {
        checkBoxAction(checkFantasy);
    }

    public void OnActionCheck(ActionEvent actionEvent) {
        checkBoxAction(checkAction);
    }

    public void OnCrimeCheck(ActionEvent actionEvent) {
        checkBoxAction(checkCrime);
    }

    public void OnDramaCheck(ActionEvent actionEvent) {
        checkBoxAction(checkDrama);
    }

    public void OnMysteryCheck(ActionEvent actionEvent) {
        checkBoxAction(checkMystery);
    }

    public void OnThrillerCheck(ActionEvent actionEvent) {
        checkBoxAction(checkThriller);

    }

    public void OnRomanceCheck(ActionEvent actionEvent) {
        checkBoxAction(checkRomance);
    }

    public void OnHorrorCheck(ActionEvent actionEvent) {checkBoxAction(checkHorror);
    }

    public void OnHistoryCheck(ActionEvent actionEvent) {checkBoxAction(checkHistory);
    }

    public void OnWarCheck(ActionEvent actionEvent) {checkBoxAction(checkWar);
    }

    public void OnScienceFictionCheck(ActionEvent actionEvent) {checkBoxAction(checkScienceFiction);
    }
}
