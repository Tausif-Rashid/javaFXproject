package SearchFunctions;

import java.io.Serializable;

public class Movie implements Serializable {
    /*Title
o Year of Release
o Genre (A movie can have up to three different genres)
o Running Time (in minutes)
o Production Company
o Budget
o Revenue*/

    private String title;


    private int yearOfRelease;


    private String[] genre = new String[3];
    private int runningTime;

    public String getPrintGenre() {
        return printGenre;
    }

    private String productionCompany;
    private int budget;
    private int revenue;
    private int profit;

    private String printGenre;

    //private Button button;

    Movie() {
        this.title = null;
    }

    public Movie(String[] infos) {
        this.title = infos[0];

        this.genre[0] = infos[2];
        this.genre[1] = infos[3];
        this.genre[2] = infos[4];

        this.productionCompany = infos[6];

        try {
            this.yearOfRelease = Integer.parseInt(infos[1]);
            this.runningTime = Integer.parseInt(infos[5]);
            this.budget = Integer.parseInt(infos[7]);
            this.revenue = Integer.parseInt(infos[8]);
        } catch (NumberFormatException e) {
            System.out.println("String entered instead of integer when creating movie object");
        }
        this.profit = revenue - budget;

        for (String gen : genre) {
            if (printGenre == null)
                printGenre = gen + ",";
            else
                printGenre = printGenre + gen + ",";
        }

        /*int genreCount;
        if(genre[1]==null)
        {
            genreCount =1;
        } else if (genre[2]==null) {
            genreCount =2;
        }
        else
        {
            genreCount =3;
        }*/


    }

    public String printAllInfo() {




        String output = "Title: " + title + "\nRelease Year: " + yearOfRelease + "\nGenre: " + printGenre + "\nRunningTime : " + runningTime +
                "\nProduction Company: " + productionCompany + "\nBudget: " + budget + "\nRevenue: " + revenue + "\nProfit: "+profit;

        return output;

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getProfit() {
        return profit;
    }

    public String toString() {
        return title + ", " + yearOfRelease ;
    }
}
