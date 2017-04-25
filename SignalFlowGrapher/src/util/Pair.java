package util;

import java.util.ArrayList;

public class Pair {

    private ArrayList<Integer> path;

    private int gain;

    public Pair(ArrayList<Integer> path, int gain) {
        this.gain = gain;
        this.path = path;
    }

    public ArrayList<Integer> getPath() {
        return path;
    }

    public int getGain() {
        return gain;
    }

}
