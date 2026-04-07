/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_2048;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author peiju
 */
public class GamePage extends Application {
    Profile profile;
    public GamePage(Profile profile){
        this.profile = profile;
    }
        
    @Override
    public void start (Stage stage){
        
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: #FFF7E6;");
        Scene scene = new Scene(root, 420, 640);
        stage.setTitle("2048");
        
        stage.setScene(scene);
        stage.show();
        
     
        
        //2048 logo
        Rectangle rect_2048 = new Rectangle(120, 120); // width, height
        rect_2048.setArcWidth(20);   
        rect_2048.setArcHeight(20);
        rect_2048.setFill(Color.web("#EBD859")); 

     
        Text text_2048 = new Text("2048");
        text_2048.setFill(Color.web("#FFFFFF")); 
        text_2048.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        

        StackPane stack_2048 = new StackPane(rect_2048, text_2048);
        stack_2048.setLayoutX(20);
        stack_2048.setLayoutY(20);
        
        
         // Current Score
        Rectangle rect_score = new Rectangle(100, 100); 
        rect_score.setArcWidth(15);   
        rect_score.setArcHeight(15);
        rect_score.setFill(Color.web("#A39B8E")); 
        
        Text text_score = new Text("SCORE");
        text_score.setFill(Color.web("#F0DCCC")); 
        text_score.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        
        VBox vbox_text_score = new VBox(text_score);
        vbox_text_score.setAlignment(Pos.TOP_CENTER);
        vbox_text_score.setPadding(new Insets(15, 0, 0, 0));
        
        
        Text value_score = new Text("0");
        value_score.setFill(Color.web("#FFFF")); 
        value_score.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        
        VBox vbox_value_score = new VBox(value_score);
        vbox_value_score.setAlignment(Pos.CENTER);
        vbox_value_score.setPadding(new Insets(5, 0, 0, 0));
        
        
        VBox vbox_score = new VBox();
        vbox_score.getChildren().addAll(vbox_text_score, vbox_value_score);
         
        StackPane stack_score = new StackPane(rect_score, vbox_score);
        stack_score.setLayoutX(175);
        stack_score.setLayoutY(20);
        
         // Best Score
        Rectangle rect_best_score = new Rectangle(100, 100); 
        rect_best_score.setArcWidth(15);   
        rect_best_score.setArcHeight(15);
        rect_best_score.setFill(Color.web("#A39B8E")); 

        Text text_best_score = new Text("BEST");
        text_best_score.setFill(Color.web("#F0DCCC")); 
        text_best_score.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        
        VBox vbox_text_best_score = new VBox(text_best_score);
        vbox_text_best_score.setAlignment(Pos.TOP_CENTER);
        vbox_text_best_score.setPadding(new Insets(15, 0, 0, 0));
        
        
        Text value_best_score = new Text("0");
        value_best_score.setFill(Color.web("#FFFF")); 
        value_best_score.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        
        VBox vbox_value_best_score = new VBox(value_best_score);
        vbox_value_best_score.setAlignment(Pos.CENTER);
        vbox_value_best_score.setPadding(new Insets(5, 0, 0, 0));
        
        
        VBox vbox_best_score = new VBox();
        vbox_best_score.getChildren().addAll(vbox_text_best_score, vbox_value_best_score);
        
        StackPane stack_best_score = new StackPane(rect_best_score, vbox_best_score);
        stack_best_score.setLayoutX(290);
        stack_best_score.setLayoutY(20);
        
        //Menu
        Button button_menu = new Button("MENU");
        button_menu.setPrefSize(100, 30);
        button_menu.setStyle(
            "-fx-background-color: #EB9B59;" +   
            "-fx-text-fill: white;" +            
            "-fx-font-family: 'Arial';" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 10px;" +
            "-fx-background-radius: 5;" +       
            "-fx-cursor: hand;"                  
        );
        button_menu.setLayoutX(175);
        button_menu.setLayoutY(130);
        
        

        
        //leaderboard
        Button button_leaderboard = new Button("LEADERBOARD");
        button_leaderboard.setPrefSize(100, 30);
        button_leaderboard.setStyle(
            "-fx-background-color: #EB9B59;" +   
            "-fx-text-fill: white;" +            
            "-fx-font-family: 'Arial';" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 10px;" +
            "-fx-background-radius: 5;" +       
            "-fx-cursor: hand;"                 
        );
        
        

        button_leaderboard.setLayoutX(290);
        button_leaderboard.setLayoutY(130);
        
        int nextGoal = 2048;
        
        Text text_nextGoal = new Text("Your next goal is to get to the " + nextGoal + " tile!");
        text_nextGoal.setFill(Color.web("#6E5D54")); 
        text_nextGoal.setFont(Font.font("Arial Rounded MT Bold", 18));
        
        VBox vbox_nextGoal = new VBox(text_nextGoal);
        vbox_nextGoal.setAlignment(Pos.TOP_CENTER);
        vbox_nextGoal.setLayoutX(20);
        vbox_nextGoal.setLayoutY(180);
        
        //Grid
        StackPane board = new StackPane();
       
    
        final int GAP = 9, N = 4, RADIUS = 5, PADDING = 11, BOARD_SIZE = 370, BOARD_POS_X = 20, BOARD_POS_Y = 220;
        GridPane tileGrid = new GridPane();
        tileGrid.setHgap(GAP);
        tileGrid.setVgap(GAP);
        tileGrid.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));



        for (int i = 0; i < N; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / N);
            tileGrid.getColumnConstraints().add(cc);

            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / N);
            tileGrid.getRowConstraints().add(rc);
        }
        
        tileGrid.setLayoutX(BOARD_POS_X);
        tileGrid.setLayoutY(BOARD_POS_Y);
        tileGrid.setMaxHeight(BOARD_SIZE);
        tileGrid.setMinHeight(BOARD_SIZE);
        tileGrid.setMinWidth(BOARD_SIZE);
        tileGrid.setMaxWidth(BOARD_SIZE);
        
        
 
        GridPane backgroundGrid = new GridPane();
        backgroundGrid.setHgap(GAP);
        backgroundGrid.setVgap(GAP);
        backgroundGrid.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        backgroundGrid.setStyle("-fx-background-color: #bbada0;" +
                      "-fx-background-radius: " + RADIUS + ";");


        for (int i = 0; i < N; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / N);
            backgroundGrid.getColumnConstraints().add(cc);

            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / N);
            backgroundGrid.getRowConstraints().add(rc);
        }


        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                StackPane cell = makeEmptyTile();
                cell.maxWidthProperty().bind(backgroundGrid.widthProperty()
                        .subtract((N - 1) * GAP + backgroundGrid.getPadding().getLeft() + backgroundGrid.getPadding().getRight())
                        .divide(N));
                cell.maxHeightProperty().bind(cell.maxWidthProperty());
                cell.prefWidthProperty().bind(cell.maxWidthProperty());
                cell.prefHeightProperty().bind(cell.maxHeightProperty());
                backgroundGrid.add(cell, c, r);
            }
        }

     
        backgroundGrid.setLayoutX(BOARD_POS_X);
        backgroundGrid.setLayoutY(BOARD_POS_Y);
        backgroundGrid.setMaxHeight(BOARD_SIZE);
        backgroundGrid.setMinHeight(BOARD_SIZE);
        backgroundGrid.setMinWidth(BOARD_SIZE);
        backgroundGrid.setMaxWidth(BOARD_SIZE);
        
        board.getChildren().addAll(backgroundGrid, tileGrid);
        board.setLayoutX(BOARD_POS_X);
        board.setLayoutY(BOARD_POS_Y);
        board.setMaxHeight(BOARD_SIZE);
        board.setMinHeight(BOARD_SIZE);
        board.setMinWidth(BOARD_SIZE);
        board.setMaxWidth(BOARD_SIZE);

      
        
        
        root.getChildren().addAll(stack_2048, stack_score, stack_best_score,button_menu, button_leaderboard, vbox_nextGoal, board);
        
        Game game = new Game(scene, tileGrid, GAP, value_score, profile);
        game.start();
        
        button_menu.setOnAction(event -> {
            new MenuPage(game, profile).start(stage);
            root.requestFocus();
            game.play();
            
        });
        
        
        root.requestFocus();
    }
    
    private StackPane makeEmptyTile() {
        StackPane tile = new StackPane();
        tile.setAlignment(Pos.CENTER);

        Rectangle rect = new Rectangle();
        rect.setArcWidth(5);
        rect.setArcHeight(5);
        rect.setFill(Color.web("#cdc1b4")); 

        rect.widthProperty().bind(tile.widthProperty());
        rect.heightProperty().bind(tile.heightProperty());

        Text label = new Text("");
        label.setFill(Color.web("#776e65"));
        label.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        tile.getChildren().addAll(rect, label);
        return tile;
        
        
    }
}
