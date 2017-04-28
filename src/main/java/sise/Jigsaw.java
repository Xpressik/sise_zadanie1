package sise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Jigsaw {

    private int jigsawCurrentState[][];
    private int height;
    private int width;
    private int size;
    private int zeroPositionX;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jigsaw other = (Jigsaw) obj;
        return Arrays.deepEquals(this.jigsawCurrentState, other.jigsawCurrentState);
    }

    public int[][] getJigsawCurrentState() {
        return jigsawCurrentState;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }
}
