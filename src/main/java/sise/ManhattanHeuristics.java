/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sise;

/**
 *
 * @author macki
 */
public class ManhattanHeuristics extends Heuristics {

    @Override
    public int Heuristic(Jigsaw jigsaw) {
        result = 0;
        for (int i = 0; i < jigsaw.getHeight(); i++) {
            for (int j = 0; j < jigsaw.getWidth(); j++) {
                int value = jigsaw.getJigsawCurrentState()[i][j];
                if (value > 0) {
                    --value;
                    int x = value % jigsaw.getWidth();
                    int y = value / jigsaw.getWidth();
                    int dx = Math.abs(j - x);
                    int dy = Math.abs(i - y);
                    int d = dx + dy;
                    result += d;
                }
            }
        }
        return result;
    }
    
}
