package helpz;

import objects.PathPoint;

import java.util.ArrayList;

import static helpz.Constants.Direction.*;
import static helpz.Constants.Tiles.DIRT_TILE;

public class Utilz {
    public static int[][] GetRoadDirArr(int[][] lvlTypeArr, PathPoint start, PathPoint end) {
        int[][] roadDirArr = new int[lvlTypeArr.length][lvlTypeArr[0].length];

        PathPoint currTile = start;
        int lastDir = -1;

        while (!IsCurrSameAsEnd(currTile, end)) {
            PathPoint prevTile = currTile;
            currTile = GetNextRoadTile(prevTile, lastDir, lvlTypeArr);
            lastDir = GetDirFromPrevToCurr(prevTile, currTile);
            roadDirArr[prevTile.getyCord()][prevTile.getxCord()] = lastDir;
        }
        roadDirArr[end.getyCord()][end.getxCord()] = lastDir;
        return roadDirArr;
    }

    private static int GetDirFromPrevToCurr(PathPoint prevTile, PathPoint currTile) {
        // Up or down
        if (prevTile.getxCord() == currTile.getxCord()) {
            if (prevTile.getyCord() > currTile.getyCord())
                return UP;
            else
                return DOWN;
        } else {
            // Right or left
            if (prevTile.getxCord() > currTile.getxCord())
                return LEFT;
            else
                return RIGHT;
        }

    }

    private static PathPoint GetNextRoadTile(PathPoint prevTile, int lastDir, int[][] lvlTypeArr) {

        int testDir = lastDir;
        PathPoint testTile = GetTileInDir(prevTile, testDir, lastDir);

        while (!IsTileRoad(testTile, lvlTypeArr)) {
            testDir++;
            testDir %= 4;
            testTile = GetTileInDir(prevTile, testDir, lastDir);
        }

        return testTile;
    }

    private static boolean IsTileRoad(PathPoint testTile, int[][] lvlTypeArr) {
        if (testTile != null)
            if (testTile.getyCord() >= 0)
                if (testTile.getyCord() < lvlTypeArr.length)
                    if (testTile.getxCord() >= 0)
                        if (testTile.getxCord() < lvlTypeArr[0].length)
                            if (lvlTypeArr[testTile.getyCord()][testTile.getxCord()] == DIRT_TILE)
                                return true;

        return false;
    }

    private static PathPoint GetTileInDir(PathPoint prevTile, int testDir, int lastDir) {

        switch (testDir) {
            case LEFT:
                if (lastDir != RIGHT)
                    return new PathPoint(prevTile.getxCord() - 1, prevTile.getyCord());
            case UP:
                if (lastDir != DOWN)
                    return new PathPoint(prevTile.getxCord(), prevTile.getyCord() - 1);
            case RIGHT:
                if (lastDir != LEFT)
                    return new PathPoint(prevTile.getxCord() + 1, prevTile.getyCord());
            case DOWN:
                if (lastDir != UP)
                    return new PathPoint(prevTile.getxCord(), prevTile.getyCord() + 1);
        }

        return null;
    }

    private static boolean IsCurrSameAsEnd(PathPoint currTile, PathPoint end) {
        if (currTile.getxCord() == end.getxCord())
            if (currTile.getyCord() == end.getyCord())
                return true;
        return false;
    }

    public static int[][] ArrayListTo2Dint(ArrayList<Integer> list, int xSize, int ySize) {
        int[][] newArr = new int[xSize][ySize];
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                int index = i * ySize + j;
                newArr[i][j] = list.get(index);
            }
        }
        return newArr;
    }

    public static int[] _2Dto1DintArr(int[][] twoArr) {
        int xSize = twoArr.length;
        int ySize = twoArr[0].length;
        int[] oneArr = new int[xSize * ySize];
        int index = 0;
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                oneArr[index++] = twoArr[i][j];
            }
        }
        return oneArr;
    }

    public static int GetHypoDistance(float x1, float y1, float x2, float y2) {
        float xDiff = Math.abs(x1 - x2);
        float yDiff = Math.abs(y1 - y2);
        return (int) Math.hypot(xDiff, yDiff);
    }
}
