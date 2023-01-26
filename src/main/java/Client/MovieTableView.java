package Client;

import SearchFunctions.Movie;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class MovieTableView {

    private final SimpleStringProperty titleString;


    private final SimpleStringProperty releaseYearString;

    public String getRevenueString() {
        return revenueString.get();
    }

    public SimpleStringProperty revenueStringProperty() {
        return revenueString;
    }

    public void setRevenueString(String revenueString) {
        this.revenueString.set(revenueString);
    }

    private final SimpleStringProperty revenueString;

    private final SimpleStringProperty runtimeString;

    public String getRuntimeString() {
        return runtimeString.get();
    }

    public SimpleStringProperty runtimeStringProperty() {
        return runtimeString;
    }

    public void setRuntimeString(String runtimeString) {
        this.runtimeString.set(runtimeString);
    }

    public String getProfitString() {
        return profitString.get();
    }

    public SimpleStringProperty profitStringProperty() {
        return profitString;
    }

    public void setProfitString(String profitString) {
        this.profitString.set(profitString);
    }

    private final SimpleStringProperty profitString;
    private final Button button;

    private final Button buttonSelect;

    public Movie getMovie() {
        return movie;
    }

    private final Movie movie;

    public void setMain(TransferViewController main) {
        this.main = main;
    }

    public Button getButtonSelect() {
        return buttonSelect;
    }

    private TransferViewController main;

    public MovieTableView(Movie movie) {
        titleString = new SimpleStringProperty(String.valueOf(movie.getTitle()));
        releaseYearString = new SimpleStringProperty(String.valueOf(movie.getYearOfRelease()));

        revenueString = new SimpleStringProperty(String.valueOf(movie.getRevenue()));
        profitString = new SimpleStringProperty(String.valueOf(movie.getProfit()));

        runtimeString = new SimpleStringProperty(String.valueOf(movie.getRunningTime()));

        this.movie = movie;


        button = new Button("More Info");
        button.setOnAction(e -> {

                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setHeaderText(movie.getTitle());
                    a.setContentText(movie.printAllInfo());
                    a.showAndWait();
                }
        );

        buttonSelect = new Button("Select");
        buttonSelect.setOnAction(e -> {
                    System.out.println("one movie selected");
                    if (main != null) main.TransferMovie(this);
                }
        );
    }


    public String getTitleString() {
        return titleString.get();
    }

    public SimpleStringProperty titleStringProperty() {
        return titleString;
    }

    public void setTitleString(String titleString) {
        this.titleString.set(titleString);
    }

    public String getReleaseYearString() {
        return releaseYearString.get();
    }

    public SimpleStringProperty releaseYearStringProperty() {
        return releaseYearString;
    }

    public void setReleaseYearString(String releaseYearString) {
        this.releaseYearString.set(releaseYearString);
    }

    public Button getButton() {
        return button;
    }


}
