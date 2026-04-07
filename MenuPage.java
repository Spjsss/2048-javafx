/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_2048;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author peiju
 */
public class MenuPage extends Application {
    Game game;
    Profile profile;
    public MenuPage(Game game, Profile profile){
        this.game = game;
        this.profile = profile;
    }
    
    @Override
    public void start(Stage stage){
        
        final double BTN_WIDTH = 200, BTN_HEIGHT = 50, GAP = 60;
         double pos_y = 10;

        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: #FFF7E6;");
        Scene scene = new Scene(root, 420, 640);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
        
        //Title: Menu
        Label title_menu = new Label();
        title_menu.setText("Menu");
        title_menu.setTextFill(Color.web("#69655C"));
        title_menu.setFont(Font.font("Arial Black", 35));
        title_menu.setPrefWidth(BTN_WIDTH);
        title_menu.setPrefHeight(BTN_HEIGHT);
        title_menu.setAlignment(Pos.TOP_LEFT);
        title_menu.setLayoutX(25);
        title_menu.setLayoutY(pos_y);
        
        //Profile
        AnchorPane profile_pane = new AnchorPane();
        profile_pane.setStyle(
            "-fx-background-color: #B5AEA5;"+
            "-fx-background-radius: 35;"
        );
        Image image_profilePic = new Image(getClass().getResourceAsStream(AvatarImage.getURL(profile.avatarChoice)));
        
        final int R = 60;
        
        ImageView imageView_profilePic = new ImageView(image_profilePic);
        imageView_profilePic.setFitWidth(R * 2);
        imageView_profilePic.setPreserveRatio(true);
        
        Circle clip = new Circle(R ,R ,R - 5);
        imageView_profilePic.setClip(clip);
      
        
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage roundedImage = imageView_profilePic.snapshot(parameters, null);
        imageView_profilePic.setClip(null);
        imageView_profilePic.setImage(roundedImage);
        
        Circle border_profilePic = new Circle(R ,R ,R + 2);
        border_profilePic.setStroke(Color.web("#000000"));
        border_profilePic.setStrokeWidth(5);
        border_profilePic.setFill(Color.TRANSPARENT);
        
        StackPane profilePic = new StackPane();
        profilePic.getChildren().addAll(border_profilePic, imageView_profilePic);
        profilePic.setPrefWidth(clip.getRadius() * 2);
        
        Label text_name = new Label("Hi!" + profile.name);
        text_name.setStyle(//"-fx-background-color: #FFF6E3;" +
                             "-fx-background-radius:  5;");
        text_name.setTextFill(Color.web("#000000"));
        text_name.setFont(Font.font("Comic Sans MS",FontWeight.BOLD, 20));
        text_name.setTextAlignment(TextAlignment.CENTER);
        text_name.setPrefSize(80, 20);
        

        
        
        profile_pane.getChildren().addAll(profilePic, text_name);
        profile_pane.setPrefWidth(BTN_WIDTH + 20);
        profile_pane.setPrefHeight(230);
        AnchorPane.setLeftAnchor(profilePic, profile_pane.getPrefWidth()/2 - border_profilePic.getRadius());
        AnchorPane.setTopAnchor(profilePic, 10.0);
        text_name.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(text_name, 170.0);
        AnchorPane.setLeftAnchor(text_name, profile_pane.getPrefWidth()/2 - text_name.getPrefWidth()/2);

        pos_y += 70;
        profile_pane.setLayoutX(scene.getWidth()/2 - profile_pane.getPrefWidth()/2);
        profile_pane.setLayoutY(pos_y);
                
        //KEEP GOING
        Button button_keep_going = new Button("KEEP GOING");
        button_keep_going.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
        button_keep_going.setStyle(
            "-fx-background-color: #EDCF72;" +   
            "-fx-text-fill: white;" +            
            "-fx-font-family: 'Arial';" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 20px;" +
            "-fx-background-radius: 20;" +       
            "-fx-cursor: hand;"                 
        );
        
        pos_y += 270;
        button_keep_going.setLayoutX(scene.getWidth()/2 - button_keep_going.getPrefWidth()/2);
        button_keep_going.setLayoutY(pos_y);
        
        button_keep_going.setOnAction(event -> {
            stage.setScene(game.scene);
            game.play();
            
        });

        //NEW GAME
        Button button_new_game = new Button("NEW GAME");
        button_new_game.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
        button_new_game.setStyle(
            "-fx-background-color: #F0C16C;" +   
            "-fx-text-fill: white;" +            
            "-fx-font-family: 'Arial';" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 20px;" +
            "-fx-background-radius: 20;" +       
            "-fx-cursor: hand;"                 
        );
        pos_y += GAP;
        button_new_game.setLayoutX(scene.getWidth()/2 - button_new_game.getPrefWidth()/2);
        button_new_game.setLayoutY(pos_y);
        
        button_new_game.setOnAction(event -> {
            game.newGame();
            stage.setScene(game.scene);
        });
        
         //Switch player
        Button button_switch_player = new Button("SWITCH PLAYER");
        button_switch_player.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
        button_switch_player.setStyle(
            "-fx-background-color: #C695DB;" +   
            "-fx-text-fill: white;" +            
            "-fx-font-family: 'Arial';" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 20px;" +
            "-fx-background-radius: 20;" +       
            "-fx-cursor: hand;"                 
        );
        pos_y += GAP;
        button_switch_player.setLayoutX(scene.getWidth()/2 - button_switch_player.getPrefWidth()/2);
        button_switch_player.setLayoutY(pos_y);
        
         //EDIT PROFILE
        Button button_edit_profile = new Button("EDIT PROFILE");
        button_edit_profile.setPrefSize(BTN_WIDTH, BTN_HEIGHT);
        button_edit_profile.setStyle(
            "-fx-background-color: #95DBCC;" +   
            "-fx-text-fill: white;" +            
            "-fx-font-family: 'Arial';" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 20px;" +
            "-fx-background-radius: 20;" +       
            "-fx-cursor: hand;"                 
        );
        pos_y += GAP;
        button_edit_profile.setLayoutX(scene.getWidth()/2 - button_switch_player.getPrefWidth()/2);
        button_edit_profile.setLayoutY(pos_y);
        
        button_edit_profile.setOnAction(event -> {
            EditProfilePage edit = new EditProfilePage(profile, stage, scene, game);
            edit.start(stage);
        });
        
        
        
        
        
        root.getChildren().addAll(title_menu, profile_pane, button_keep_going, button_new_game, button_switch_player, button_edit_profile);
        
        
    }
}
