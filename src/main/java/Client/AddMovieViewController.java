package Client;

import SearchFunctions.Movie;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import utils.DataPacket;

import java.io.IOException;

public class AddMovieViewController {

    public TextField titleInput;
    public TextField genreInput;
    public TextField relYrInput;
    public TextField runtimeInput;
    public TextField budgetInput;
    public TextField revenueInput;

    public static int addStatus;
    ClientMain main;

    public void setMain(ClientMain main) {
        this.main = main;
        addStatus =0;
    }

    public void OnBackClick(ActionEvent actionEvent) throws Exception {
        main.showHomePage(ClientMain.company.getCompanyName());
    }

    public void OnAddMovieClick(ActionEvent actionEvent) throws IOException {

        String [] info =new String[9];

        addStatus=-1;
        int status =1;

        try
        {
            Integer.parseInt(relYrInput.getText());
            Integer.parseInt(runtimeInput.getText());
            Integer.parseInt(revenueInput.getText());
            Integer.parseInt(budgetInput.getText());

        }catch (NumberFormatException e)
        {
            status=0;
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Please Enter Integer in number fields");
            a.showAndWait();

        }

        if(status==1) //check if title already exists
         status = OnCheckClick(actionEvent);


        if(status==1){ //all ok
            info[0] = titleInput.getText();
            info[1] = relYrInput.getText();


            String[] tokens = genreInput.getText().split(",");


            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i].length() >= 1)
                    info[2 + i] = tokens[i];
            }


            info[5] = runtimeInput.getText();
            info[6] = ClientMain.company.getCompanyName();
            info[7] = budgetInput.getText();
            info[8] = revenueInput.getText();

            Movie m = new Movie(info);

            DataPacket dp =new DataPacket();

            dp.setCommand("AddMovie");
            dp.setNewMovie(m);
            dp.setTitle(titleInput.getText());
            main.getSocketWrapper().write(dp);
            System.out.println("Movie sent to server");

            System.out.println(ClientMain.movieList.getMovieList());

            //ClientMain.movieList.addMovie(m); //calls the overloaded func of movielist class
            ClientMain.moviesOfCompany.add(m);
            ClientMain.company.addMovieComp(m);

            System.out.println(ClientMain.movieList.getMovieList());

            OnResetClick(actionEvent);
        }

        //System.out.println(m.printAllInfo());

    }

    public void OnResetClick(ActionEvent actionEvent)
    {
        titleInput.setText("");
        relYrInput.setText("");
        genreInput.setText("");
        revenueInput.setText("");
        budgetInput.setText("");
        runtimeInput.setText("");

    }


    public int OnCheckClick(ActionEvent actionEvent) throws IOException {

        if(titleInput.getText().length()==0)
        {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please Enter a proper title");
            a.showAndWait();
        }

        DataPacket dp =new DataPacket();

        dp.setCommand("CheckMovie");
        dp.setTitle(titleInput.getText());

        main.getSocketWrapper().write(dp);

        System.out.println("check sent to server");

        if(addStatus==1)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("A movie with this title already exists");
            a.showAndWait();

            return -1;
        }
        else if(addStatus==0)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("This movie does not exist");
            a.showAndWait();
            return 1;
        } else if (addStatus ==-1) {

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Movie successfully added");
            a.showAndWait();
            return 1;
        }
        return 1;
    }
}
