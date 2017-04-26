package sise;

import java.util.ArrayList;
import java.util.Scanner;

public class Jigsaw {

    private int jigsawCurrentState[][];
    private int height;
    private int width;
    private int size;
    private int zeroPositionX;
    private int getZeroPositionY;
    private String directions;


    public Jigsaw(String inputFilename, String directions) {
        this.directions = directions;

        readStateFromFile(inputFilename);
        findZeroPosition();

        size = width * height;
    }

    private void readStateFromFile(String filename) {
        Scanner scanner = new Scanner(filename);

        height = scanner.nextInt();
        width = scanner.nextInt();
        jigsawCurrentState = new int[height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                jigsawCurrentState[i][j] = scanner.nextInt();
            }
        }
    }

    private void findZeroPosition() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (jigsawCurrentState[i][j] == 0) {
                    zeroPositionX = j;
                    getZeroPositionY = i;
                }
            }
        }
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
}
