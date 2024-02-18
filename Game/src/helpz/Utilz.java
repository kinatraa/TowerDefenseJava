package helpz;

import java.util.ArrayList;

public class Utilz {
    public static int[][] ArrayListTo2Dint(ArrayList<Integer> list, int xSize, int ySize){
        int[][] newArr = new int[xSize][ySize];
        for(int i = 0; i < xSize; i++){
            for(int j = 0; j < ySize; j++){
                int index = i*ySize + j;
                newArr[i][j] = list.get(index);
            }
        }
        return newArr;
    }
    public static int[] _2Dto1DintArr(int[][] twoArr){
        int xSize = twoArr.length;
        int ySize = twoArr[0].length;
        int[] oneArr = new int[xSize * ySize];
        int index = 0;
        for(int i = 0; i < xSize; i++){
            for(int j = 0; j < ySize; j++){
                oneArr[index++] = twoArr[i][j];
            }
        }
        return oneArr;
    }
}
