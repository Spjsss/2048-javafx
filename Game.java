/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_2048;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.UP;
import javafx.util.Duration;

/**
 *
 * @author peiju
 */
public class Game {
    Scene scene;
    GridPane grid;
    Text valueScore;
    int[][] arr;
    int blankCount, score;
    private boolean isKeyLocked;
    int GAP;
    final int N = 4;
    Profile profile;
    
    
    public Game(Scene scene, GridPane grid, final int GAP, Text value_score, Profile profile){
        this.scene = scene;
        this.grid = grid;
        this.GAP = GAP;
        this.profile = profile;
        valueScore = value_score;
        blankCount = 16;
        score = 0;
        isKeyLocked = false;
        
        
   
    }
    
    public void start(){
        blankCount = 16;
        score = 0;
        arr = new int[N][N];
        
        for(int i = 0; i < 2; i++){
            placeNewTile(new boolean[N][N], 0);
        }
        
        play();
        
    }
    
    public void newGame(){
        grid.getChildren().clear();
        start();

    }
  
    
    public void play(){
        scene.setOnKeyPressed(event -> {
            if(isKeyLocked) {
                return;
            }
            
            isKeyLocked = true;
            CompletableFuture<Void> moveFuture = null;
            switch (event.getCode()) {
                case UP -> {
                    System.out.println("Up key pressed!");
                    moveFuture = moveUp();
                    }
                case DOWN -> {
                    System.out.println("Down key pressed!");
                    moveFuture = moveDown();
                    }
                case LEFT -> {
                    System.out.println("Left key pressed!");
                    moveFuture = moveLeft();
                    }
                case RIGHT -> {
                    System.out.println("Right key pressed!");
                    moveFuture = moveRight();
                    }
                default -> {
                    isKeyLocked = false;
                    }
            }
            if(moveFuture != null){
                moveFuture.thenRun(() -> {
                    Platform.runLater(() -> isKeyLocked = false);
                });
            }

        });
    }
    
    public Node getTile(int row, int column){
        Node tile = null;
        for(Node node: grid.getChildren()){
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column){
                tile = node;
                break;
            }
        
        }
        return tile;
    }
    
    
    
    public CompletableFuture<Void> moveUp(){
        boolean[][] isMerged = new boolean[N][N];
        boolean[][] isMoved = new boolean[N][N];
        int movedCount = 0;
        CompletableFuture<Void> moveDone = new CompletableFuture<>();
        List<CompletableFuture<Void>> moves = new ArrayList<>();
       
        int num;
        for(int j = 0; j < N; j++){
            for(int i = 1; i < N; i++){
                if(arr[i][j] != 0){
                   
                    int pos_i = i;
                    if(arr[i - 1][j] == 0 ){
                        pos_i = i - 1;
                    
                        for(; pos_i >= 0; pos_i--){
                            if(arr[pos_i][j] != 0){
                                break;
                            }
                        }
                    pos_i++;
                    }
                    if(pos_i > 0){
                        if(arr[i][j] == arr[pos_i - 1][j] && pos_i - 1 != i && !isMerged[pos_i - 1][j]){
                            isMerged[pos_i - 1][j] = true;
                            num = arr[i][j] * 2;
                            moves.add(moveTile(new int[] {i,j}, new int[] {pos_i - 1,j}, num,  true));
                            continue;
                        }
                    }
                  
                    if(pos_i != i && arr[pos_i][j] == 0){
                       isMoved[pos_i][j] = true;
                       num = arr[i][j];
                       moves.add(moveTile(new int[] {i,j}, new int[] {pos_i,j}, num, false));
                    }
                  
                } 
               
            }
        }
        placeNewTileAfterMove(moves, moveDone, isMoved, movedCount);
        return moveDone;
    }
    
    public CompletableFuture<Void> moveLeft(){
        CompletableFuture<Void> moveDone = new CompletableFuture<>();
        List<CompletableFuture<Void>> moves = new ArrayList<>();
        boolean isMerged[][] = new boolean[N][N];
        boolean[][] isMoved = new boolean[N][N];
        int movedCount = 0;
       
        int num;
        for(int i = 0; i < N; i++){
            for(int j = 1; j < N; j++){
                if(arr[i][j] != 0){
                   
                    int pos_j = j;
                    if(arr[i][j - 1] == 0 ){
                        pos_j = j - 1;
                        for(; pos_j >= 0; pos_j--){
                            if(arr[i][pos_j] != 0){
                                break;
                            }
                        }
                        pos_j++;
                    }
                    if(pos_j > 0){
                        if(arr[i][j] == arr[i][pos_j - 1] && pos_j - 1 != j && !isMerged[i][pos_j - 1]){
                            isMerged[i][pos_j - 1] = true;
                            num = arr[i][j] * 2;
                            moves.add(moveTile(new int[] {i,j}, new int[] {i,pos_j - 1}, num,  true));
                            continue;
                        }
                    }

                    if(pos_j != j && arr[i][pos_j] == 0){
                        isMoved[i][pos_j] = true;
                        num = arr[i][j];
                        moves.add(moveTile(new int[] {i,j}, new int[] {i, pos_j}, num, false));
                    }
                }
               
            }
        }
       
       
        placeNewTileAfterMove(moves, moveDone, isMoved, movedCount);
        return moveDone;
    }
    
     public CompletableFuture<Void> moveRight(){
        CompletableFuture<Void> moveDone = new CompletableFuture<>();
        List<CompletableFuture<Void>> moves = new ArrayList<>();
        boolean isMerged[][] = new boolean[N][N];
        boolean[][] isMoved = new boolean[N][N];
        int movedCount = 0;
       
        int num;
        for(int i = 0; i < N; i++){
            for(int j = N - 2; j >= 0; j--){
                if(arr[i][j] != 0){
                   
                    int pos_j = j;
                    if(arr[i][j + 1] == 0 ){
                        pos_j = j + 1;
                        for(; pos_j < N; pos_j++){
                            if(arr[i][pos_j] != 0){
                                break;
                            }
                        }
                        pos_j--;
                    }
                    if(pos_j < N - 1){
                        if(arr[i][j] == arr[i][pos_j + 1] && pos_j + 1 != j && !isMerged[i][pos_j + 1]){
                           isMerged[i][pos_j + 1] = true;
                           num = arr[i][j] * 2;
                           moves.add(moveTile(new int[] {i,j}, new int[] {i,pos_j + 1}, num,  true));
                           continue;
                        }
                    }
                  
                    if(pos_j != j && arr[i][pos_j] == 0){
                        isMoved[i][pos_j] = true;
                        num = arr[i][j];
                        moves.add(moveTile(new int[] {i,j}, new int[] {i, pos_j}, num, false));
                    } 
                }  
            }
        }
        placeNewTileAfterMove(moves, moveDone, isMoved, movedCount);
        return moveDone;
    }
    
    public CompletableFuture<Void> moveDown(){
        CompletableFuture<Void> moveDone = new CompletableFuture<>();
        List<CompletableFuture<Void>> moves = new ArrayList<>();
        boolean isMerged[][] = new boolean[N][N];
        boolean[][] isMoved = new boolean[N][N];
        int movedCount = 0;
        
        int num;
        for(int j = 0; j < N; j++){
            for(int i = N - 2; i >= 0; i--){
                if(arr[i][j] != 0){
                   
                    int pos_i = i;
                    if(arr[i + 1][j] == 0 ){
                        pos_i = i + 1;
                        for(; pos_i < N; pos_i++){
                            if(arr[pos_i][j] != 0){
                                break;
                            }
                        }
                    pos_i--;
                    }
                    if(pos_i < N - 1){
                        if(arr[i][j] == arr[pos_i + 1][j] && pos_i + 1 != i && !isMerged[pos_i + 1][j]){
                            isMerged[pos_i + 1][j] = true;
                            num = arr[i][j] * 2;
                            moves.add(moveTile(new int[] {i,j}, new int[] {pos_i + 1,j}, num,  true));
                            continue;
                        }
                    }
                  
                    if(pos_i != i && arr[pos_i][j] == 0){
                       isMoved[pos_i][j] = true;
                       num = arr[i][j];
                       moves.add(moveTile(new int[] {i,j}, new int[] {pos_i,j}, num, false));
                    }     
                }  
            }
        }
       placeNewTileAfterMove(moves, moveDone, isMoved, movedCount);
       return moveDone;
    }
    

    public CompletableFuture<Void> moveTile(int[] src, int[] dst, int num, boolean isMerged){
        CompletableFuture<Void> future = new CompletableFuture<>();
       
        int y = dst[0] - src[0];
        int x = dst[1] - src[1];
        arr[dst[0]][dst[1]] = num;
        arr[src[0]][src[1]] = 0;
        
        StackPane newTile = makeTile(num);

        Node tile = getTile(src[0], src[1]);
        TranslateTransition move = new TranslateTransition(Duration.millis(150), tile);
        move.setByX(x * 90);
        move.setByY(y * 90);
        move.setAutoReverse(false);
        move.setCycleCount(1);
   
        if(isMerged){
            
            move.setOnFinished(e -> {
                score += num;
                valueScore.setText(Integer.toString(score));
                System.out.println(num);
                grid.getChildren().remove(getTile(dst[0], dst[1]));
                grid.getChildren().remove(tile);
                blankCount++;

                grid.add(newTile, dst[1], dst[0]);
                pop(newTile);
                arr[dst[0]][dst[1]] = num;
                arr[src[0]][src[1]] = 0;
                future.complete(null);
            });
        }else{
            
            move.setOnFinished(e -> {
                System.out.println(num);
                grid.getChildren().remove(tile);
                grid.add(newTile, dst[1], dst[0]);
                arr[dst[0]][dst[1]] = num;
                arr[src[0]][src[1]] = 0;
                future.complete(null);
        });
            
        }
        
        move.play();
        return future;
     
    }
    
    public void placeNewTileAfterMove(List<CompletableFuture<Void>> moves, CompletableFuture<Void> moveDone, boolean[][] isMoved, int movedCount){
        CompletableFuture.allOf(moves.toArray(new CompletableFuture[0])).thenRun(() -> {
            
        PauseTransition pause = new PauseTransition(Duration.millis(20));
        pause.setOnFinished(e -> {
            if(!moves.isEmpty()){
                placeNewTile(isMoved, movedCount);
            }
            moveDone.complete(null);
            
        });
        pause.play();
        });
    }
    
    
    public void pop(StackPane tile){
        ScaleTransition pop = new ScaleTransition(Duration.millis(150), tile);
        pop.setFromX(1.0);
        pop.setFromY(1.0);
        pop.setToX(1.2);
        pop.setToY(1.2);
        pop.setAutoReverse(true);
        pop.setCycleCount(2);
        pop.play();
    }

    
    public String getTileColour(int num){
        String tileColour;
        switch (num) {
            case 2 -> tileColour = "#EEE4DA";
            case 4 -> tileColour = "#EDE0C8";
            case 8 -> tileColour = "#F2B179";
            case 16 -> tileColour = "#F59563";
            case 32 -> tileColour = "#F67C5F";
            case 64 -> tileColour = "#F65E3B";
            case 128 -> tileColour = "#EDCF72";
            case 256 -> tileColour = "#EDCC61";
            case 512 -> tileColour = "#EDC850";
            case 1024 -> tileColour = "#EDC53F";
            case 2048 -> tileColour = "#EDC22E";
            case 4096 -> tileColour = "#27F5CC";
            case 8192 -> tileColour = "#02CC71";
            default -> {
                tileColour = "#140404";
            }
        }
        return tileColour;
    }
    
    public String getTextColour(int num){
        if(num == 2 || num == 4){
            return "#776E65";
        }else{
            return "#F9F6F2";
        }
    }
    
    public int getTextSize(int num){
        int digitCount = 0;
        while(num != 0){
            num /= 10;
            digitCount++;
        }
        return 45 - digitCount * 5;
    }
    
    public StackPane makeTile(int num){
        StackPane tile = new StackPane();
        tile.setAlignment(Pos.CENTER);

        Rectangle rect = new Rectangle();
        rect.setArcWidth(5);
        rect.setArcHeight(5);
        rect.setFill(Color.web(getTileColour(num))); 

        rect.widthProperty().bind(tile.widthProperty());
        rect.heightProperty().bind(tile.heightProperty());

        Text label = new Text(Integer.toString(num));
        label.setFill(Color.web(getTextColour(num)));
        label.setFont(Font.font("Arial", FontWeight.BOLD, getTextSize(num)));

        tile.getChildren().addAll(rect, label);
        
        tile.maxWidthProperty().bind(grid.widthProperty().subtract((N - 1) * GAP + grid.getPadding().getLeft() + grid.getPadding().getRight()).divide(N));
        tile.maxHeightProperty().bind(tile.maxWidthProperty());
        tile.prefWidthProperty().bind(tile.maxWidthProperty());
        tile.prefHeightProperty().bind(tile.maxHeightProperty());
        return tile;
    }
    
    public void placeNewTile(boolean[][] isMoved, int movedCount){
        if(blankCount == 0){
            return;
        }
        Random r = new Random();
        boolean nonOverlap = false;
        if(blankCount - movedCount > 5 ){
            if(r.nextInt(10) == 0){
                nonOverlap = true;
                blankCount -= movedCount;
            }
            
        }
        
        int pos = r.nextInt(blankCount);
        int pos_count = 0;
        
        int num;

        loop:
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                if(arr[i][j] == 0){
                    if(nonOverlap){
                        if(isMoved[i][j]){
                            continue;
                        }
                    }
                    
                    if(pos_count == pos){
                        if(r.nextInt(5) == 0){
                            num = 4;
                        }else{
                            num = 2;
                        }
                        StackPane cell = makeTile(num);
                        grid.add(cell, j, i);
                        arr[i][j] = num;
                        pop(cell);
                        blankCount--;
                        break loop;
                    }
                    pos_count++;
                }
            }
        }
        
    }
}
