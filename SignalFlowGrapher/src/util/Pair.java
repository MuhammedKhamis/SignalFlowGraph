package util;

import java.util.ArrayList;

public class Pair {

    private ArrayList<String> path;

    private int gain;

    public Pair(ArrayList<String> path, int gain) {
        this.gain = gain;
        this.path = path;
    }

    public ArrayList<String> getPath() {
        return path;
    }

    public int getGain() {
        return gain;
    }

}
