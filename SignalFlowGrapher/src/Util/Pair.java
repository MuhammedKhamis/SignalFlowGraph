package Util;


import java.util.ArrayList;

public class Pair {

    private ArrayList<Integer> path;

    private double gain;

    public Pair(ArrayList<Integer> path, double gain) {
        this.gain = gain;
        this.path = path;
    }

    public ArrayList<Integer> getPath() {
        return path;
    }

    public double getGain() {
        return gain;
    }

}
