package SearchFunctions;

import java.util.ArrayList;
import java.util.List;

public class ProdCompanyList {

    private List<ProductionCompany> listCompany = new ArrayList<>();


    private int companyCount;

    public ProdCompanyList(List<Movie> list) {
        companyCount = 0;


        for (Movie mov : list) //make a new company obj for each movie, pass it to add func for checking,adding and updating the movie list of the company
        {
            ProductionCompany company = new ProductionCompany(mov.getProductionCompany());

            addCompany(company, list);
        }

    }

    void addCompany(ProductionCompany newCompany, List<Movie> listOfMovies) {
        boolean alreadyExist = false;
        for (ProductionCompany comp : listCompany) {
            if (comp.getCompanyName().equalsIgnoreCase(newCompany.getCompanyName())) {
                alreadyExist = true;
                break;
            }
        }
        if (!alreadyExist) {
            newCompany.companyNewMovieList(listOfMovies);
            listCompany.add(newCompany);
            companyCount++;

        }

    }

    void addCompanyNewMovie(ProductionCompany newCompany, List<Movie> listOfMovies, Movie mv) {
        for (ProductionCompany comp : listCompany) { //already exists, just add the new movie
            if (comp.getCompanyName().equalsIgnoreCase(newCompany.getCompanyName())) {

                comp.addMovieComp(mv);
                return;
            }
        }

        // company doesnt exist, create new one
        newCompany.companyNewMovieList(listOfMovies);
        listCompany.add(newCompany);
        companyCount++;

    }

//TODO not needed
    public List<Movie> getRecentMovies(String name) {
        List<Movie> movList = new ArrayList<>();
        for (ProductionCompany cmp : listCompany) {
            if (name.equalsIgnoreCase(cmp.getCompanyName())) {
                int Latest = cmp.getLatestReleaseYear();
                for (Movie mov : cmp.getCompanyMovie()) {
                    if (mov.getYearOfRelease() == Latest)
                        movList.add(mov);
                }
            }
        }
        return movList;

    }
    //TODO not needed
    public List<Movie> getHighestRevMovies(String name) {
        List<Movie> movList = new ArrayList<>();
        for (ProductionCompany cmp : listCompany) {
            if (name.equalsIgnoreCase(cmp.getCompanyName())) {
                int highest = cmp.getMaxRevenue();
                for (Movie mov : cmp.getCompanyMovie()) {
                    if (mov.getRevenue() == highest)
                        movList.add(mov);
                }
            }
        }
        return movList;

    }


    public List<ProductionCompany> getListCompany() {
        return listCompany;
    }

    public int getCompanyCount() {
        return companyCount;
    }
}
