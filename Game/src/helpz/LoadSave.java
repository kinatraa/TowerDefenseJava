package helpz;

import objects.PathPoint;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
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
        WriteToFile(newLevel, idArr, new PathPoint(0, 0), new PathPoint(0, 0));
    }
    public static void WriteToFile(File f, int[] idArr, PathPoint start, PathPoint end){
        try {
            PrintWriter pw = new PrintWriter(f);
            for(Integer i : idArr){
                pw.println(i);
            }
            pw.println(start.getxCord());
            pw.println(start.getyCord());
            pw.println(end.getxCord());
            pw.println(end.getyCord());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void SaveLevel(String name, int[][] idArr, PathPoint start, PathPoint end){
        File levelFile = new File("res/" + name + ".txt");
        if(levelFile.exists()){
            WriteToFile(levelFile, Utilz._2Dto1DintArr(idArr), start, end);
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
    public static ArrayList<PathPoint> GetLevelPathPoints(String name){
        File lvlFile = new File("res/" + name + ".txt");
        if(lvlFile.exists()){
            ArrayList<Integer> list = ReadFromFile(lvlFile);
            ArrayList<PathPoint> points = new ArrayList<>();
            points.add(new PathPoint(list.get(768), list.get(769)));
            points.add(new PathPoint(list.get(770), list.get(771)));
            return points;
        }
        else{
            System.out.println("File " + name + " does not exists!");
            return null;
        }
    }
    public static int[][] GetLevelData(String name){
        File lvlFile = new File("res/" + name + ".txt");
        if(lvlFile.exists()){
            ArrayList<Integer> list = ReadFromFile(lvlFile);
            return Utilz.ArrayListTo2Dint(list, 24, 32);
        }
        else{
            System.out.println("File " + name + " does not exists!");
            return null;
        }
    }
}
