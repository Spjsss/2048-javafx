/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package game_2048;


import javafx.application.Application;
import javafx.stage.Stage;



public class Main extends Application {
    
    @Override
    public void start(Stage stage){
        
        if(Profile.getProfile().isEmpty()){
            EditProfilePage editProfilePage = new EditProfilePage();
            editProfilePage.start(stage);
        }else{
            GamePage gamePage = new GamePage(Profile.getLastUser());
            gamePage.start(stage);
        }
        
        
    }

    public static void main(String[] args) {
        launch(args);
    }

  
    
}
