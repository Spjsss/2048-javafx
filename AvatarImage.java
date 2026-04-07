/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_2048;

import javafx.scene.image.Image;

/**
 *
 * @author peiju
 */
public class AvatarImage {
    
     static String[] URLs = new String[8];
     static{
        URLs[0] = "/images/pic1.jpg";
        URLs[1] = "/images/pic2.jpg";
        URLs[2] = "/images/pic3.jpg";
        URLs[3] = "/images/pic4.jpg";
        URLs[4] = "/images/pic5.jpg";
        URLs[5] = "/images/pic6.jpg";
        URLs[6] = "/images/pic7.jpg";
        URLs[7] = "/images/pic8.jpg";
     }
     
    public AvatarImage(){
        
    }
    
    
    public static String getURL(int avatarChoice){
        return URLs[avatarChoice];
    }
}
