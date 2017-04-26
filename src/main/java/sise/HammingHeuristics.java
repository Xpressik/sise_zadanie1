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
public class HammingHeuristics extends Heuristics{

    @Override
    public int Heuristic(Jigsaw jigsaw) {
        result = 0;
        int value = 1;
        for (int i = 0; i < jigsaw.getHeight(); ++i) {
            for (int j = 0; j < jigsaw.getWidth(); ++j) {
                int size = jigsaw.getJigsawCurrentState()[i][j];
                if (size != 0 && size != value) {
                    ++result;
                }
                ++value;
            }
        }
        return result;
    }
    
}
