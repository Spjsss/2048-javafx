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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author peiju
 */
public class EditProfilePage extends Application  {
    String name;
    Scene previousScene;
    int avatarChoice;
    boolean isEditProfile;
    Profile profile;
    Game game;
    
    public EditProfilePage(){
        isEditProfile = false;
        name = "Please enter your name";
        previousScene = null;
        avatarChoice = -1;
        profile = new Profile();
        
    }
    
    public EditProfilePage(Profile profile, Stage stage, Scene previousScene, Game game){
        isEditProfile = true;
        this.name = profile.name;
        this.avatarChoice = profile.avatarChoice;
        this.previousScene = previousScene;
        this.game = game;
        
    }
    

    @Override
    public void start(Stage stage){
        
        double pos_y = 10;
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: #FFF7E6;");
        Scene scene = new Scene(root, 420, 640);
        stage.setTitle("Profile");
        stage.setScene(scene);
        stage.show();
        
        //Title: Menu
        Label title_profile = new Label();
        title_profile.setText("Profile");
        title_profile.setStyle(
            "-fx-background-color: #EBD859;" +  
            "-fx-text-fill: #FFFFFF;" + 
            "-fx-font-family: 'Arial';" +
            "-fx-font-size: 25px;" +
            "-fx-font-weight: bold;" +          
            "-fx-padding: 10;" +               
            "-fx-background-radius: 8;"         
        );
        title_profile.setPrefWidth(120);
        title_profile.setPrefHeight(40);
        title_profile.setAlignment(Pos.CENTER);
        title_profile.setLayoutX(20);
        title_profile.setLayoutY(pos_y);
        
        //Title: Menu
        Label title = new Label();
        if(isEditProfile){
            title.setText("Edit Profile");
        }else{
            title.setText("Create Account");
        }
        
        title.setStyle(
            "-fx-background-color: #DBD4C1;" +  
            "-fx-text-fill: #FFFAE8;" + 
            "-fx-font-family: 'Arial Rounded MT Bold';" +
            "-fx-font-size: 25px;" +
            "-fx-font-weight: bold;" +          
            "-fx-padding: 10;" +               
            "-fx-background-radius: 8;"         
        );
        title.setPrefWidth(220);
        title.setPrefHeight(20);
        title.setAlignment(Pos.CENTER);
        
        pos_y += 60;
        title.setLayoutX(scene.getWidth()/2 - title.getPrefWidth()/2);
        title.setLayoutY(pos_y);
        
        
        
        //Select your avatar 
        Label text_avatar = new Label();
        text_avatar.setText("Select Your Avatar:");
        text_avatar.setTextFill(Color.web("#69655C"));
        text_avatar.setFont(Font.font("Comic Sans MS",FontWeight.EXTRA_BOLD, 20));
        text_avatar.setPrefWidth(300);
        text_avatar.setPrefHeight(50);
        text_avatar.setAlignment(Pos.CENTER);
        
        pos_y += 60;
        text_avatar.setLayoutX(scene.getWidth()/2 - text_avatar.getPrefWidth()/2);
        text_avatar.setLayoutY(pos_y);
        
        //Profile
        AnchorPane profile_pane = new AnchorPane();
        profile_pane.setStyle(
            "-fx-background-color: #B5AEA5;"+
            "-fx-background-radius: 35;" +
            "-fx-border-color: #DBD4C1;" +
            "-fx-border-width: 10;" +
            "-fx-border-radius: 20"
        );
        Image[] pics = new Image[8];
        pics[0] = new Image(getClass().getResourceAsStream("/images/pic1.jpg"));
        pics[1] = new Image(getClass().getResourceAsStream("/images/pic2.jpg"));
        pics[2] = new Image(getClass().getResourceAsStream("/images/pic3.jpg"));
        pics[3] = new Image(getClass().getResourceAsStream("/images/pic4.jpg"));
        pics[4] = new Image(getClass().getResourceAsStream("/images/pic5.jpg"));
        pics[5] = new Image(getClass().getResourceAsStream("/images/pic6.jpg"));
        pics[6] = new Image(getClass().getResourceAsStream("/images/pic7.jpg"));
        pics[7] = new Image(getClass().getResourceAsStream("/images/pic8.jpg"));
        
        ComboBox<Integer> comboBox = new ComboBox<>();  
        for(int i = 0; i < pics.length; i++){
            comboBox.getItems().add(i);
        }
        
    
        
        final int R = 80;
        comboBox.setCellFactory(param -> new ListCell<>(){
            private final ImageView imageView = new ImageView();
            private final SnapshotParameters parameters = new SnapshotParameters();
            private final Circle clip = new Circle(R ,R ,R - 5);
           
            protected void updateItem(Integer index, boolean empty){
                super.updateItem(index, empty);
                if(empty || index == null){
                    setGraphic(null);
                }else{
                    imageView.setImage(pics[index]);
                    imageView.setFitWidth(R * 2);
                    imageView.setPreserveRatio(true);
                    imageView.setClip(clip);
                    parameters.setFill(Color.TRANSPARENT);
                    WritableImage roundedImage = imageView.snapshot(parameters, null);
                    imageView.setClip(null);
                    imageView.setImage(roundedImage);
                    setGraphic(imageView);
               }
           }
       });
       
        comboBox.setButtonCell(new ListCell<>() {
            private final ImageView imageView = new ImageView();
            private final SnapshotParameters parameters = new SnapshotParameters();
            private final Circle clip = new Circle(R ,R ,R - 5);
           
            protected void updateItem(Integer index, boolean empty){
                super.updateItem(index, empty);
                if(empty || index == null){
                    setGraphic(null);
                }else{
                    imageView.setImage(pics[index]);
                    imageView.setFitWidth(R * 2);
                    imageView.setPreserveRatio(true);
                    imageView.setClip(clip);
                    parameters.setFill(Color.TRANSPARENT);
                    WritableImage roundedImage = imageView.snapshot(parameters, null);
                    imageView.setClip(null);
                    imageView.setImage(roundedImage);
                    setGraphic(imageView);
                }
            } 
        });
       
       
       comboBox.setVisibleRowCount(2);
       comboBox.setStyle(
            "-fx-background-color: #B5AEA5;" 
        );
        comboBox.setPrefWidth(120);
        comboBox.setPrefHeight(50);
        
       
        profile_pane.getChildren().addAll(comboBox);
        profile_pane.setPrefWidth(220);
        profile_pane.setPrefHeight(230);
        
        AnchorPane.setLeftAnchor(comboBox, 15.0);
        AnchorPane.setTopAnchor(comboBox, 18.0);
        
        pos_y += 50;
        profile_pane.setLayoutX(scene.getWidth()/2 - profile_pane.getPrefWidth()/2);
        profile_pane.setLayoutY(pos_y);
        
        //Warning: NullAvatar
        Label warning_avatar = new Label();
        warning_avatar.setText("");
        warning_avatar.setTextFill(Color.web("#ED6F5C"));
        warning_avatar.setFont(Font.font("Comic Sans Ms",FontWeight.BOLD, 13));
        warning_avatar.setPrefWidth(220);
        warning_avatar.setPrefHeight(40);
        warning_avatar.setAlignment(Pos.CENTER);
        
        pos_y += profile_pane.getPrefHeight();
        warning_avatar.setLayoutX(scene.getWidth()/2 - warning_avatar.getPrefWidth()/2);
        warning_avatar.setLayoutY(pos_y);
        
         //Enter your name 
        Label text_name = new Label();
        text_name.setText("Enter Your Name:");
        text_name.setTextFill(Color.web("#6E6760"));
        text_name.setFont(Font.font("Comic Sans MS",FontWeight.EXTRA_BOLD, 20));
        text_name.setPrefWidth(300);
        text_name.setPrefHeight(50);
        text_name.setAlignment(Pos.CENTER);
        
        pos_y += 30;
        text_name.setLayoutX(scene.getWidth()/2 - text_name.getPrefWidth()/2);
        text_name.setLayoutY(pos_y);
        
        TextField nameField = new TextField();
        nameField.setPromptText(name);
        nameField.setStyle(
                            "-fx-background-color: #DBD4C1;" +     
                            "-fx-text-fill: #000000;" +            
                            "-fx-prompt-text-fill: #7A7264;" +     
                            "-fx-background-radius: 10;" +         
                            "-fx-font-size: 18px;" 
                           );
        
        
        nameField.setPrefWidth(300);
        nameField.setPrefHeight(50);
        
        pos_y +=  50;
        nameField.setLayoutX(scene.getWidth()/2 - nameField.getPrefWidth()/2);
        nameField.setLayoutY(pos_y);
        
        String text_button;
        if(isEditProfile){
            text_button = "Edit";
        }else{
            text_button = "Create";
        }
       
        Button button_submit = new Button(text_button);
        button_submit.setStyle(
            "-fx-background-color: #EBD859;" +  
            "-fx-text-fill: white;" + 
            "-fx-font-family: 'Arial';" +
            "-fx-font-size: 25px;" +
            "-fx-font-weight: bold;" +          
            "-fx-padding: 10;" +               
            "-fx-background-radius: 8;"         
        );
        button_submit.setPrefWidth(120);
        button_submit.setPrefHeight(40);
        button_submit.setAlignment(Pos.CENTER);
        button_submit.setLayoutX(280);
        button_submit.setLayoutY(580);
        
        //Warning: InvalidName
        Label warning_name = new Label();
        warning_name.setText("");
        warning_name.setTextFill(Color.web("#ED6F5C"));
        warning_name.setFont(Font.font("Comic Sans Ms",FontWeight.BOLD, 13));
        warning_name.setPrefWidth(300);
        warning_name.setPrefHeight(50);
        warning_name.setAlignment(Pos.CENTER);
        
        pos_y += 40;
        warning_name.setLayoutX(scene.getWidth()/2 - warning_name.getPrefWidth()/2);
        warning_name.setLayoutY(pos_y);
        
        
       root.getChildren().addAll(title_profile,title, text_avatar, profile_pane,warning_avatar, text_name, nameField, button_submit, warning_name);
       
       button_submit.setOnAction(event -> {
            boolean isValid = true;
            String warningText_name = "";
            String warningText_avatar = "";
            Integer selected = comboBox.getValue();
            String name = nameField.getText();
            
            if(selected == null){
                warningText_avatar = "Please select a avatar!";
                isValid = false;
            }
            if(name == null || name.isEmpty() || name.isBlank()){
                isValid = false;
                warningText_name = "Name must not be null!";
            }else if(nameField.getText().length() > 30){
                isValid = false;
                warningText_name = "Name must not exceed 30  characters!";
            }else if(Profile.isNameExist(nameField.getText())){
                warningText_name = "This name is already taken.";
            }
            if(isValid){
                profile.name = nameField.getText();
                profile.avatarChoice = selected;
                if(isEditProfile){
                    Profile.editProfile(profile);
                }else{
                    Profile.WriteFileCreate(profile);
                }
                if(previousScene != null){
                    stage.setScene(previousScene);
                    MenuPage menu = new MenuPage(game, profile);
                }
            }else{
                warning_name.setText(warningText_name);
                warning_avatar.setText(warningText_avatar);
            }
            
            
            
            
            
        });
    }
    
    
}
