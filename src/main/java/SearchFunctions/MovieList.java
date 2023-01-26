package SearchFunctions;

import Client.ClientMain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MovieList implements Serializable {

    private List<Movie> movieList = new ArrayList<>();

    private int MovieCount;

    public MovieList(List<Movie> movieList) {
        MovieCount = movieList.size();
        this.movieList = movieList;

    }

    public int getMovieCount() {
        return MovieCount;
    }

    public Movie getMovie(int index) { //Get a movie at specific index
        return movieList.get(index);
    }

    public Movie searchMovieTitle(String title) { //Search with title. return the movie


        for (Movie movie : movieList) {

            if (movie.getTitle().equalsIgnoreCase(title))
                return movie;

        }
        return null;
    }

    public List<Movie> searchMovieYear(int year) { //Search with year. return the movie list

        List<Movie> moviesYear = new ArrayList<>();
        for (Movie movie : movieList) {

            if (movie.getYearOfRelease() == year)
                moviesYear.add(movie);

        }
        return moviesYear;
    }

    private List<Movie> genreSearch(List<Movie> shortList, String genre) {
        List<Movie> moviesGenre = new ArrayList<>();

        for (Movie movie : shortList) {

            for (String gn : movie.getGenre()) {
                if (gn.equalsIgnoreCase(genre))
                    moviesGenre.add(movie);
                //System.out.println("Found these with genre "+ genre +" " + gn);
            }
        }

       // System.out.println("Found these with genre "+ genre +" " + moviesGenre.size());
        return moviesGenre;
    }

    public List<Movie> searchMovieGenre(List<String> genre , List<Movie> list) { //Search with genre. return the movie list

        List<Movie> moviesGenre = list;

        for (String gen : genre) {
            moviesGenre = genreSearch(moviesGenre, gen);
        }

        return moviesGenre;
    }

    public List<Movie> searchMovieCompany(String company) { //Search with company. return the movie list

        List<Movie> moviesCompany = new ArrayList<>();
        for (Movie movie : movieList) {

            if (movie.getProductionCompany().equalsIgnoreCase(company))
                moviesCompany.add(movie);

        }

        return moviesCompany;
    }

    public List<Movie> searchMovieRuntime(int min, int max) { //Search with runtime. return the movie list

        List<Movie> moviesRun = new ArrayList<>();
        for (Movie movie : movieList) {

            if (movie.getRunningTime() <= max && movie.getRunningTime() >= min)
                moviesRun.add(movie);

        }
        return moviesRun;
    }
   /*for (int i=0; i<movieList.size()-1; i++)
    {
        for (int j=0; j<movieList.size()-i-1; j++)
        {
            if(movieList.get(j).getYearOfRelease()<movieList.get(j+1).getYearOfRelease())
            {
                Movie temp = movieList.get(i);
                movieList.set(i,movieList.get(j));
                movieList.set(j,temp);
            }
        }
    }*/

    public List<Movie> top10Movie() {
        movieList.sort(new Comparator<Movie>() {
            //@Override
            public int compare(Movie o1, Movie o2) {
                return Integer.compare(o2.getProfit(), o1.getProfit());
            }
        });

        List<Movie> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(movieList.get(i));
        }
        return list;
    }



    public int addMovie(String[] info) {//TODO add filter to avoid duplicate
        Movie movie = new Movie(info);


        for (Movie mov : movieList) {
            if (mov.getTitle().equalsIgnoreCase(movie.getTitle())) {

                return -1;
            }
        }
        movieList.add(movie);
        System.out.println("Movie list movie added");
        MovieCount++;

        return 0;
    }
    public void addMovie(Movie m)
    {System.out.println("Movie list movie added by overloaded func");
        movieList.add(m);
    }


    public List<Movie> getMovieList() {
        return movieList;
    }


}
