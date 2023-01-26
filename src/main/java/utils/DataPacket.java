package utils;

import SearchFunctions.Movie;

import java.io.Serializable;
import java.util.List;

public class DataPacket implements Serializable {
    private int id;
    private String command;

    private String userName;
    private String password;
    private List<Movie> movieList;
    private Movie newMovie;
    private String companyNameTo;
    private String companyNameFrom;

    public String getCompanyNameFrom() {
        return companyNameFrom;
    }

    public void setCompanyNameFrom(String companyNameFrom) {
        this.companyNameFrom = companyNameFrom;
    }

    private String title;

    private List<String> companyNames;

    public List<String> getCompanyNames() {
        return companyNames;
    }

    public void setCompanyNames(List<String> companyNames) {
        this.companyNames = companyNames;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public boolean isStatus() {
        return status;
    }

    private boolean status;

    public String getCompanyName() {
        return companyNameTo;
    }

    public void setCompanyName(String companyName) {
        this.companyNameTo = companyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String value) {
        this.command = value;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public Movie getNewMovie() {
        return newMovie;
    }

    public void setNewMovie(Movie newMovie) {
        this.newMovie = newMovie;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}