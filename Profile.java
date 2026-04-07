/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game_2048;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peiju
 */
public class Profile {
    String name;
    public static String filePath_profile = "profile.csv", filePath_latest = "last_profile.csv";
    int avatarChoice, best;
    static List<Profile> profiles = getProfile();
    static int index = getLastIndex();
    static String[] header = {"Index", "Name", "AvatarChoice", "Best"};
    
    
    public Profile(String name, int avatarChoice, int best){
        this.name = name;
        this.avatarChoice = avatarChoice;
        this.best = best;

    }
    
    
    public Profile(){
        name = null;
        avatarChoice = 0;
        best = 0;
        System.out.println(index);
    }
    
    public static List<Profile> getProfile(){
        List<Profile> get_profiles = new ArrayList<Profile>();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath_profile))) {
            String line;
            br.readLine(); //read header
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                int index_read = Integer.parseInt(values[0]);
                String name_read = values[1];
                int choice_read = Integer.parseInt(values[2]);
                int best_read = Integer.parseInt(values[3]);
                get_profiles.add(index_read - 1, new Profile(name_read, choice_read, best_read));
                
            }
            br.close();
        }catch(FileNotFoundException e) {
            System.out.println("File not found:" + e.getMessage());
        }catch(IOException e){
            System.out.println("Error:" + e.getMessage());
        }
        return get_profiles;
    }
    
    public static int getLastIndex(){
        return profiles.size();
    }
    
    public static Profile getLastUser(){
         try(BufferedReader br = new BufferedReader(new FileReader(filePath_latest))) {
            String line;
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                String name_read = values[1];
                int choice_read = Integer.parseInt(values[2]);
                int best_read = Integer.parseInt(values[3]);
                return new Profile(name_read, choice_read, best_read);
                
            }
            br.close();
        }catch(FileNotFoundException e) {
            System.out.println("File not found:" + e.getMessage());
        }catch(IOException e){
            System.out.println("Error:" + e.getMessage());
        }
         return null;
    }
    public static void WriteFileCreate(Profile profile){
        index++;
        boolean isAppend = true;
        if(index == 1){
            isAppend = false;
        }
        
        String[] input = {Integer.toString(index), profile.name, Integer.toString(profile.avatarChoice), Integer.toString(profile.best)};
        try(FileWriter writer = new FileWriter(filePath_latest, isAppend)){
            if(!isAppend){
                writer.append(String.join(",", header));
            }
            writer.append("\n");
            writer.append(String.join(",", input)); 
            writer.close();
        }catch(IOException  e){
            System.out.println("Error:" + e.getMessage());
        }
        
        writeLatestUser(profile);
        
        
    }
    
    public static int getProfileIndex(Profile profile){
        for(Profile entry: profiles){
            if(entry.name.equals(profile.name)){
                return entry.index;
            }
        }
        
        return -1;
    }
    
    public static void writeLatestUser(Profile profile){
         String[] input = {Integer.toString(index), profile.name, Integer.toString(profile.avatarChoice), Integer.toString(profile.best)};
        try(FileWriter writer = new FileWriter(filePath_latest, false)){
            writer.append(String.join(",", input)); 
            writer.close();
        }catch(IOException  e){
            System.out.println("Error:" + e.getMessage());
        }
    }
    
    public static void editProfile(Profile profile){
        int index = getProfileIndex(profile);
        profiles.remove(index);
        profiles.add(index, profile);
        
        try(FileWriter writer = new FileWriter(filePath_profile)){
            writer.append(String.join(",", header));
            writer.append("\n");
            for(int i = 0; i < profiles.size(); i++){
                Profile entry = profiles.get(i);
                String[] input = {Integer.toString(i + 1), entry.name, Integer.toString(entry.avatarChoice), Integer.toString(entry.best)};
                writer.append(String.join(",", input)); 
                writer.append("\n");
            }
            writer.close();
        }catch(IOException  e){
            System.out.println("Error:" + e.getMessage());
        }
        
        writeLatestUser(profile);
    }
    
    public static boolean isNameExist(String name){
        for(Profile entry: profiles){
            if(entry.name.equals(name)){
                return true;
            }
        }
        return false;
        
    }
}
