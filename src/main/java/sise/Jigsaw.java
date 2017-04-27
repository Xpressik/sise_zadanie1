package sise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Jigsaw {

    private int jigsawCurrentState[][];
    public int[][] getJigsawCurrentState() {
        return jigsawCurrentState;
    }
    
    private int height;
    public int getHeight() {
        return height;
    }
    
    private int size;
    public int getSize() {
        return size;
    }
    
    private int width;
    public int getWidth() {
        return width;
    }

    private int zeroPositionX;

    public void setZeroPositionX(int zeroPositionX) {
        this.zeroPositionX = zeroPositionX;
    }
    public int getZeroPositionX() {
        return zeroPositionX;
    }
    
    private int zeroPositionY;
    public void setZeroPositionY(int zeroPositionY) {
        this.zeroPositionY = zeroPositionY;
    }
    public int getZeroPositionY() {
        return zeroPositionY;
    }
    
    private String directions;
    public String getDirections() {
        return directions;
    }

    private String solution;   
    public void setSolution(String solution) {
        this.solution = solution;
    }
    String getSolution() {
        return this.solution;
    }

    private int zeroPositionY;
    private String directions;
    private String solution;

    public Jigsaw(Jigsaw j) {


        this.jigsawCurrentState = new int[j.height][];
        for (int i = 0; i < j.jigsawCurrentState.length; ++i) {
            this.jigsawCurrentState[i] = Arrays.copyOf(j.jigsawCurrentState[i], j.jigsawCurrentState.length);
        }

        this.height = j.height;
        this.width = j.width;
        this.size = j.size;
        this.zeroPositionX = j.zeroPositionX;
        this.zeroPositionY = j.zeroPositionY;
        this.directions = j.directions;
        this.solution = j.solution;
    }

    public Jigsaw(String inputFilename, String directions) throws FileNotFoundException {
        solution = new String();
        this.directions = directions;

        readStateFromFile(inputFilename);
        findZeroPosition();

        size = width * height;

    }
    
    public Jigsaw(Jigsaw jigsaw) {
        zeroPositionX = jigsaw.getZeroPositionX();
        zeroPositionY = jigsaw.getZeroPositionY();

        int height = jigsaw.getHeight();
        jigsawCurrentState = new int[height][];
        for (int i = 0; i < height; ++i) {
            jigsawCurrentState[i] = jigsaw.getJigsawCurrentState()[i].clone();
        }

        solution = jigsaw.getSolution();
    }

    public String getSolution() {
        return solution;
    }

    private void readStateFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));

        height = scanner.nextInt();
        width = scanner.nextInt();
        jigsawCurrentState = new int[height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                jigsawCurrentState[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
    }

    private void findZeroPosition() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (jigsawCurrentState[i][j] == 0) {
                    zeroPositionX = j;
                    zeroPositionY = i;
                }
            }
        }
    }

    List<Jigsaw> getNeighbours(){
        List<Jigsaw> neighbours = new ArrayList<>();
        for (int i = 0; i < directions.length(); ++i){
            int tmpX = zeroPositionX;
            int tmpY = zeroPositionY;
            Jigsaw jigsaw = new Jigsaw(this);
            switch (directions.charAt(i)){
                case 'L':
                    if (tmpX > 0) {
                        --tmpX;
                    } else {
                        continue;
                    }
                    break;
                case 'R':
                    if (tmpX + 1 < width) {
                        ++tmpX;
                    } else {
                        continue;
                    }
                    break;
                case 'U':
                    if (tmpY > 0) {
                        --tmpY;
                    } else {
                        continue;
                    }
                    break;
                case 'D':
                    if(tmpY + 1 < height) {
                        tmpY++;
                    } else {
                        continue;
                    }
                    break;
            }
            jigsaw.jigsawCurrentState[zeroPositionY][zeroPositionX] = jigsaw.jigsawCurrentState[tmpY][tmpX];
            jigsaw.jigsawCurrentState[tmpY][tmpX] = 0;
            jigsaw.zeroPositionX = tmpX;
            jigsaw.zeroPositionY = tmpY;
            jigsaw.solution += directions.charAt(i);
            neighbours.add(jigsaw);
        }
        return neighbours;
    }

    boolean isSolution() {
        for (int i = 0; i < width * height - 1; ++i) {
            int x = i % width;
            int y = i / width;
            int value = jigsawCurrentState[y][x];
            if (value != i + 1) {
                return false;
            }
        }
        return true;
    } 
    
        public Jigsaw getNeighbours(char direction) {
        Jigsaw jigsaw = new Jigsaw(this);
        int puzzleEmptyX = zeroPositionX;
        int puzzleEmptyY = zeroPositionY;
        switch (direction) {
            case 'U':
                --puzzleEmptyY;
                if (puzzleEmptyY < 0) {
                    return null;
                }
                break;
            case 'D':
                ++puzzleEmptyY;
                if (puzzleEmptyY >= jigsaw.getHeight()) {
                    return null;
                }
                break;
            case 'L':
                --puzzleEmptyX;
                if (puzzleEmptyX < 0) {
                    return null;
                }
                break;
            case 'R':
                ++puzzleEmptyX;
                if (puzzleEmptyX >= jigsaw.getWidth()) {
                    return null;
                }
                break;
            default:
                return null;
        }
        jigsaw.getJigsawCurrentState()[zeroPositionY][zeroPositionX] = jigsawCurrentState[puzzleEmptyY][puzzleEmptyX];
        jigsaw.getJigsawCurrentState()[puzzleEmptyY][puzzleEmptyX] = 0;
        jigsaw.setZeroPositionX(puzzleEmptyX);
        jigsaw.setZeroPositionY(puzzleEmptyY);
        jigsaw.setSolution(jigsaw.getSolution() + direction);
        return jigsaw;
    }
}
