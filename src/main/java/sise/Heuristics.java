/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sise;

import java.util.Comparator;

/**
 *
 * @author macki
 */
public abstract class Heuristics implements Comparator<Jigsaw> {

    public int result;
    
    public int compare(Jigsaw o1, Jigsaw o2){
        return Integer.compare(f(o1), f(o2));
    }
    public abstract int Heuristic(Jigsaw jigsaw);
    
    private int f(Jigsaw jigsaw) {
    int g = jigsaw.getSolution().length();
    int h = Heuristic(jigsaw);
    int result = g + h;
    return result;
    }

    
}
