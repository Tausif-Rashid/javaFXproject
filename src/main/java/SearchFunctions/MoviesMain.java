package SearchFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoviesMain { //TODO THIS ENTIRE CLASS IS TO BE DELETED
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        MovieList moviesList = null; //need to change codes so that new definiton of movielist fits
        ProdCompanyList companyList = new ProdCompanyList(moviesList.getMovieList());

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1) Search Movies");
            System.out.println("2) Search Production Companies");
            System.out.println("3) Add Movie");
            System.out.println("4) Exit System");

            System.out.print("Enter a number 1-4: ");

            String Input = scanner.nextLine();
            int input;
            try {
                input = Integer.parseInt(Input);
            } catch (NumberFormatException e) {
                System.out.println("Enter e valid input");
                continue;
            }

            if (input == 1) {
                System.out.println("\nMovie Searching Options:\n" +
                        "1) By Movie Title\n" +
                        "2) By Release Year\n" +
                        "3) By Genre\n" +
                        "4) By Production Company\n" +
                        "5) By Running Time\n" +
                        "6) Top 10 Movies\n" +
                        "7) Back to Main Menu");
                System.out.print("Enter a number 1-7: ");
                Input = scanner.nextLine();
                try {
                    input = Integer.parseInt(Input);
                } catch (NumberFormatException e) {
                    System.out.println("Enter e valid input");
                    continue;
                }

                if (input == 1) {
                    System.out.print("Enter Movie title: ");

                    String title = scanner.nextLine();
                    Movie mv = moviesList.searchMovieTitle(title);

                    if (mv == null)
                        System.out.println("No such movie with this name");
                    else
                        System.out.println(mv.printAllInfo());
                } else if (input == 2) {

                    System.out.print("Enter year of release: ");
                    String Year = scanner.nextLine();
                    int year = Integer.parseInt(Year);

                    List<Movie> moviesYear = moviesList.searchMovieYear(year);

                    if (moviesYear.isEmpty())
                        System.out.println("No such movie with this release year");
                    else {
                        for (Movie mv : moviesYear) {
                            System.out.println(mv.printAllInfo());
                        }
                    }
                } else if (input == 3) {
                    System.out.print("How many genre you want to match? : ");
                    String Count = scanner.nextLine();

                    int count = 1;
                    try {
                        count = Integer.parseInt(Count);
                    } catch (NumberFormatException e) {
                        System.out.print("Enter an integer: ");
                        Count = scanner.nextLine();
                        count = Integer.parseInt(Count);
                    }


                    List<String> genre = new ArrayList<>();


                    for (int i = 0; i < count; i++) {
                        System.out.print("Enter genre " + (i + 1) + ": ");
                        String inp = scanner.nextLine();
                        genre.add(inp);
                    }
                    List<Movie> moviesGenre = moviesList.searchMovieGenre(genre, moviesList.getMovieList());

                    if (moviesGenre.isEmpty())
                        System.out.println("No such movie with this release genre");
                    else {
                        for (Movie mv : moviesGenre) {
                            System.out.println(mv.printAllInfo());
                        }
                    }
                } else if (input == 4) {
                    System.out.print("Enter Movie Production Company: ");

                    String company = scanner.nextLine();
                    List<Movie> moviesCompany = moviesList.searchMovieCompany(company);

                    if (moviesCompany.isEmpty())
                        System.out.println("No such movie with this release genre");
                    else {
                        for (Movie mv : moviesCompany) {
                            System.out.println(mv.printAllInfo());
                        }
                    }
                } else if (input == 5) {
                    System.out.println("Enter movie runtime range");
                    System.out.print("Minimum runtime: ");
                    String Minutes = scanner.nextLine();
                    int minutesMin = Integer.parseInt(Minutes);
                    System.out.print("Maximum runtime: ");
                    Minutes = scanner.nextLine();
                    int minutesMax = Integer.parseInt(Minutes);

                    List<Movie> moviesRuntime = moviesList.searchMovieRuntime(minutesMin, minutesMax);

                    if (moviesRuntime.isEmpty())
                        System.out.println("No such movie with this release genre");
                    else {
                        for (Movie mv : moviesRuntime) {
                            System.out.println(mv.printAllInfo());
                        }
                    }

                } else if (input == 6) {
                    List<Movie> Topten = moviesList.top10Movie();

                    for (Movie mv : Topten) {
                        System.out.println(mv.printAllInfo());
                    }

                } else if (input == 7) {
                    continue;
                } else
                    System.out.println("Please enter a valid input");


            } else if (input == 2) {
                System.out.println("\nProduction Company Searching Options:\n" +
                        "1) Most Recent Movies\n" +
                        "2) Movies with the Maximum Revenue\n" +
                        "3) Total Profit\n" +
                        "4) List of Production Companies and the Count of their Produced Movies\n" +
                        "5) Back to Main Menu");
                System.out.print("Enter a number 1-5: ");
                Input = scanner.nextLine();
                try {
                    input = Integer.parseInt(Input);
                } catch (NumberFormatException e) {
                    System.out.println("Enter e valid input");
                    continue;
                }
                if (input == 1) {
                    System.out.print("Enter production company name: ");
                    String company = scanner.nextLine();

                    List<Movie> mvlist = companyList.getRecentMovies(company);

                    if (mvlist.isEmpty())
                        System.out.println("No such production company with this name");
                    else {
                        for (Movie Mov : mvlist)
                            System.out.println(Mov.printAllInfo());
                    }


                } else if (input == 2) {
                    System.out.print("Enter production company name: ");
                    String company = scanner.nextLine();

                    List<Movie> mvlist = companyList.getHighestRevMovies(company);

                    if (mvlist.isEmpty())
                        System.out.println("No such production company with this name");
                    else {
                        for (Movie Mov : mvlist)
                            System.out.println(Mov.printAllInfo());
                    }
                } else if (input == 3) {
                    System.out.print("Enter production company name: ");
                    String company = scanner.nextLine();

                    boolean found = false;
                    for (ProductionCompany cmp : companyList.getListCompany())
                        if (cmp.getCompanyName().equalsIgnoreCase(company)) {
                            found = true;
                            for (Movie mv : cmp.getCompanyMovie())
                                System.out.println("Title: " + mv.getTitle() + " ,Profit: " + mv.getProfit());
                            System.out.println("The total Profit is " + cmp.getTotalProfit());
                        }
                    if (!found)
                        System.out.println("Could not find company");

                } else if (input == 4) {
                    for (ProductionCompany cmp : companyList.getListCompany()) {
                        System.out.println("Company Name: " + cmp.getCompanyName() + " ,Movies produced: " + cmp.getMovieCount());
                    }
                } else if (input == 5) {
                    continue;
                } else
                    System.out.println("Please enter a valid command");


            } else if (input == 3) {


                String[] info = new String[9];

                System.out.print("Enter title: ");
                info[0] = scanner.nextLine();

                boolean duplicate = false;
                for (Movie mv : moviesList.getMovieList())
                    if (info[0].equalsIgnoreCase(mv.getTitle())) {
                        System.out.println("This Movie already exists");
                        duplicate = true;
                        break;
                    }
                if (duplicate)
                    continue;

                System.out.print("Enter Year of release: ");
                info[1] = scanner.nextLine();

                info[2] = info[3] = info[4] = null;
                System.out.print("Enter Number of genre: ");

                String Count = scanner.nextLine();

                int n = 1;
                try {
                    n = Integer.parseInt(Count);
                } catch (NumberFormatException e) {
                    System.out.print("Enter an integer: ");
                    Count = scanner.nextLine();
                    n = Integer.parseInt(Count);
                }
                //int n = Integer.parseInt(scanner.nextLine());

                for (int i = 0; i < n; i++) {
                    System.out.print("Enter Genre " + (i + 1) + ": ");
                    info[2 + i] = scanner.nextLine();
                }

                System.out.print("Enter running time: ");
                info[5] = scanner.nextLine();

                System.out.print("Enter company name: ");
                info[6] = scanner.nextLine();

                System.out.print("Enter budget: ");
                info[7] = scanner.nextLine();

                System.out.print("Enter revenue: ");
                info[8] = scanner.nextLine();

                if (moviesList.addMovie(info) == -1)
                    System.out.println("Movie with this title already exists");
                else {
                    System.out.println("Movie has been added.");
                    ProductionCompany newCompany = new ProductionCompany(info[6]);
                    Movie newMovie = new Movie(info);
                    companyList.addCompanyNewMovie(newCompany, moviesList.getMovieList(), newMovie);
                }


            } else if (input == 4) {
                System.out.println("Now exiting program");
                break;
            } else {
                System.out.println("Please enter a valid command from 1-4");
            }

        }


    }
}
