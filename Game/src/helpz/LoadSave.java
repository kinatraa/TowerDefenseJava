package helpz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadSave {
    public static BufferedImage getSpriteAtlas() {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("towerDefense_tilesheet.png");
        try {
            if(is != null) img = ImageIO.read(is);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static void CreateFile(){
        File txtFile = new File("res/testTextFile.txt");
        try {
            txtFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void CreateLevel(String name, int[] idArr){
        File newLevel = new File("res/" + name + ".txt");
        if(newLevel.exists()){
            System.out.println("File " + name + " already exists!");
            return;
        }
        try {
            newLevel.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        WriteToFile(newLevel, idArr);
    }
    public static void WriteToFile(File f, int[] idArr){
        try {
            PrintWriter pw = new PrintWriter(f);
            for(Integer i : idArr){
                pw.println(i);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void SaveLevel(String name, int[][] idArr){
        File levelFile = new File("res/" + name + ".txt");
        if(levelFile.exists()){
            WriteToFile(levelFile, Utilz._2Dto1DintArr(idArr));
        }
        else{
            System.out.println("File " + name + " does not exists!");
        }
    }
    private static ArrayList<Integer> ReadFromFile(File f){
        ArrayList<Integer> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                list.add(Integer.parseInt(sc.nextLine()));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static int[][] GetLevelData(String name){
        File lvlFile = new File("res/" + name + ".txt");
        if(lvlFile.exists()){
            ArrayList<Integer> list = ReadFromFile(lvlFile);
//            int check = 0;
//            for(Integer i : list){
//                if(i == 1) ++check;
//            }
//            System.out.println(check);
//            int[][] test = Utilz.ArrayListTo2Dint(list, 32, 24);
//            for(int i = 0; i < 24; i++){
//                for(int j = 0; j < 32; j++){
//                     if(test[i][j] == 1) ++check;
//                }
//            }
//            System.out.println(check);
            return Utilz.ArrayListTo2Dint(list, 24, 32);
        }
        else{
            System.out.println("File " + name + " does not exists!");
            return null;
        }
    }
}
