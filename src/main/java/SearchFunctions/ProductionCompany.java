package SearchFunctions;

import java.util.ArrayList;
import java.util.List;

public class ProductionCompany {

    private String companyName;

    private List<Movie> companyMovie = new ArrayList<>();

    private int movieCount;

    private long totalProfit;

    private int LatestReleaseYear;
    private int MaxRevenue;


    public ProductionCompany(String name) {
        companyName = name;

        movieCount = 0;
        totalProfit = 0;
        LatestReleaseYear = 0;
        MaxRevenue = 0;

    }

    public ProductionCompany(String name, List<Movie> list) {
        companyName = name;

        movieCount = 0;
        totalProfit = 0;
        LatestReleaseYear = 0;
        MaxRevenue = 0;

        companyNewMovieList(list);

    }

    void companyNewMovieList(List<Movie> list) {
        for (Movie mov : list) {
            if (companyName.equalsIgnoreCase(mov.getProductionCompany())) {
                companyMovie.add(mov);
                movieCount++;
                totalProfit += mov.getProfit();
                if (LatestReleaseYear < mov.getYearOfRelease()) LatestReleaseYear = mov.getYearOfRelease();
                if (MaxRevenue < mov.getRevenue()) MaxRevenue = mov.getRevenue();
            }
        }
    }

    public void addMovieComp(Movie movie) {
        companyMovie.add(movie);
        movieCount++;
        totalProfit += movie.getProfit();
        if (LatestReleaseYear < movie.getYearOfRelease()) LatestReleaseYear = movie.getYearOfRelease();
        if (MaxRevenue < movie.getRevenue()) MaxRevenue = movie.getRevenue();
    }

    public List<Movie> getRecentMovies() {
        List<Movie> movList = new ArrayList<>();

        int Latest = getLatestReleaseYear();
        for (Movie mov : getCompanyMovie()) {
            if (mov.getYearOfRelease() == Latest)
                movList.add(mov);
        }
        return movList;
    }

    public List<Movie> getHighestRevMovies() {
        List<Movie> movList = new ArrayList<>();

        int highest = getMaxRevenue();
        for (Movie mov : getCompanyMovie()) {
            if (mov.getRevenue() == highest)
                movList.add(mov);
        }

        return movList;
    }


    public String getCompanyName() {
        return companyName;
    }

    public List<Movie> getCompanyMovie() {
        return companyMovie;
    }

    public int getMovieCount() {
        return movieCount;
    }

    public long getTotalProfit() {
        return totalProfit;
    }

    public int getLatestReleaseYear() {
        return LatestReleaseYear;
    }

    public void setCompanyMovie(List<Movie> companyMovie) {
        this.companyMovie = companyMovie;
    }

    public int getMaxRevenue() {
        return MaxRevenue;
    }
}
