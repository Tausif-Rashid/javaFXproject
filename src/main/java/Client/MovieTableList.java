package Client;

import SearchFunctions.Movie;
import SearchFunctions.MovieList;

import java.util.ArrayList;
import java.util.List;

public class MovieTableList {

    List<MovieTableView> list = new ArrayList<>();

    public List<MovieTableView> getList() {
        return list;
    }

    public void setList(List<MovieTableView> list) {
        this.list = list;
    }

    MovieList movieListObj;

    public MovieTableView converter(Movie m) {
        MovieTableView movieTableView = new MovieTableView(m);
        return movieTableView;
    }

    public MovieTableList(List<Movie> movieList) {
        for (Movie m : movieList) {
            list.add(converter(m));
        }
        this.movieListObj = new MovieList(movieList);
    }

    public List<MovieTableView> getRequestList(String cmd) {
        List<MovieTableView> listRequested = new ArrayList<>();
        List<Movie> temp = new ArrayList<>();

        if (cmd.equals("ShowRecent")) {
            temp = ClientMain.company.getRecentMovies();
        } else if (cmd.equals("ShowMaxRev")) {
            temp = ClientMain.company.getHighestRevMovies();
        } else if (cmd.equals("ShowTotalProf")) {
            temp = ClientMain.company.getCompanyMovie();
        } else if (cmd.equals("ShowAll")) {
            return list;

        }


        for (Movie m : temp) {
            listRequested.add(converter(m));
        }

        return listRequested;
    }

    public List<MovieTableView> getListRelYear(int Year) {
        List<MovieTableView> listRequested = new ArrayList<>();
        List<Movie> temp = new ArrayList<>();

        temp = ClientMain.movieList.searchMovieYear(Year);

        for (Movie m : temp) {
            listRequested.add(converter(m));
        }

        return listRequested;
    }

    public List<MovieTableView> getListRuntime(int min, int max) {
        List<MovieTableView> listRequested = new ArrayList<>();
        List<Movie> temp;

        temp = ClientMain.movieList.searchMovieRuntime(min, max);

        for (Movie m : temp) {
            listRequested.add(converter(m));
        }

        return listRequested;
    }

    public List<MovieTableView> getListTitle(String title) {
        List<MovieTableView> listRequested = new ArrayList<>();
        List<Movie> temp = new ArrayList<>();

        Movie m = ClientMain.movieList.searchMovieTitle(title);

        if (m != null)
            temp.add(m);

        if (!temp.isEmpty()) {
            for (Movie n : temp) {
                listRequested.add(converter(n));
            }
        }

        return listRequested;
    }


    public List<MovieTableView> getListGenre(List<String> genre) {
        List<MovieTableView> listRequested = new ArrayList<>();
        List<Movie> temp;

        //System.out.println("found genre " + genre[0]);

        temp = ClientMain.movieList.searchMovieGenre(genre, ClientMain.company.getCompanyMovie());

        for (Movie m : temp) {
            listRequested.add(converter(m));
        }
        //System.out.println(temp.get(0));

        return listRequested;
    }
}
