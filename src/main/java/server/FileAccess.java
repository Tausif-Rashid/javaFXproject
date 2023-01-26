package server;

import SearchFunctions.Movie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileAccess {

    private static final String INPUT_FILE_NAME = "movies.txt";
    private static final String OUTPUT_FILE_NAME = "movies.txt";
    private List<Movie> movieList = new ArrayList<>();
    private int MovieCount;

    public FileAccess() {
        MovieCount = 0;


        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(INPUT_FILE_NAME));
            while (true) {
                String line = br.readLine();
                if (line == null) break;

                String[] tokens = line.split(",");
                if (tokens.length < 9) System.out.println("File format not correct. Please provide valid file.");
                else
                    addMovie(tokens);


            }
            br.close();
        } catch (IOException e) {
            System.out.println("Could not find movies.txt");
        }


    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public int getMovieCount() {
        return MovieCount;
    }

    public void addMovie(String[] info) {
        Movie movie = new Movie(info);

        movieList.add(movie);
        MovieCount++;

    }

    public void writeToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
            String text;
            for (Movie mv : movieList) {

                String printGenre = null;

                for (String gen : mv.getGenre()) {
                    if (printGenre == null)
                        printGenre = gen + ",";
                    else
                        printGenre = printGenre + gen + ",";
                }

                text = mv.getTitle() + "," + mv.getYearOfRelease() + "," + printGenre + mv.getRunningTime()
                        + "," + mv.getProductionCompany() + "," + mv.getBudget() + "," + mv.getRevenue();

                bw.write(text);
                bw.write(System.lineSeparator());
            }


            bw.close();
        } catch (IOException e) {
            System.out.println("Could not write to file");
        }
    }


}
