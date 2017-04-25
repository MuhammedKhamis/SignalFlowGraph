package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Stack;

import model.Edge;
import model.Node;
import util.Pair;

public class Graph {

    private static Graph graph;

    private Hashtable<Integer, Node> nodes;

    private ArrayList<Pair> forwardPaths;

    private ArrayList<Pair> loops;

    private int delta;

    private ArrayList<Integer> deltas;

    private double gain;

    private static final int initial = 1;

    private Graph() {
        gain = 0;
        nodes = new Hashtable<>();
        forwardPaths = new ArrayList<>();
        loops = new ArrayList<>();
        delta = 0;
        deltas = new ArrayList<>();
    }

    public boolean addNode(int name) {
        if (nodes.containsKey(name)) {
            return false;
        }
        nodes.put(name, new Node(name));
        return true;

    }

    public boolean removeNode(String name) {
        if (!nodes.containsKey(name)) {
            return false;
        }
        nodes.remove(name);
        return true;
    }

    public boolean addEdge(int from, int to, int cost) {
        if (!nodes.containsKey(from)) {
            return false;
        }
        return nodes.get(from).addEdge(to, cost);
    }

    public boolean removeEdge(int from, int to) {
        if (!nodes.containsKey(from)) {
            return false;
        }
        return nodes.get(from).removeEdge(to);
    }

    public boolean solve(Integer from, Integer to) {
        if (!nodes.containsKey(from) && !nodes.containsKey(to)) {
            return false;
        }
        ArrayList<Integer> tmpy = new ArrayList<>();
        tmpy.add(from);
        findForwardPathes(initial, from, to, tmpy);
        ArrayList<Integer> gains = new ArrayList<>();
        gains.add(initial);
        findLoops(initial, from, new ArrayList<>(), gains);
        delta = findDelta(loops, new ArrayList<>(), 0, 1, initial);
        for (int i = 0; i < forwardPaths.size(); i++) {
            ArrayList<Pair> tmp = filterPath(forwardPaths.get(i));
            Integer val = findDelta(tmp, new ArrayList<>(), 0, 1, initial);
            deltas.add(val);
        }
        gain = 0;
        for (int i = 0; i < forwardPaths.size(); i++) {
            gain = gain + forwardPaths.get(i).getGain() * deltas.get(i);
        }
        gain = gain / delta;
        return true;
    }

    public double getGain() {
        return gain;
    }

    public ArrayList<Pair> getForwardPaths() {
        return forwardPaths;
    }

    public ArrayList<Pair> getLoops() {
        return loops;
    }

    public ArrayList<Integer> getDeltas() {
        return deltas;
    }

    public int getDelta() {
        return delta;
    }

    private void filterLoops() {
        ArrayList<Pair> tmp = new ArrayList<>();
        ArrayList<ArrayList<Integer>> tmp2 = new ArrayList<>();
        boolean flag;
        for(int i = 0 ; i < loops.size() ; i++){
            
        }
        for (int i = 0; i < loops.size(); i++) {
            flag = true;
            for (int j = i + 1; j < loops.size(); j++) {
                
            }
            if(flag){
                tmp.add(loops.get(i));
            }
        }
        loops = tmp;
    }

    private void findForwardPathes(int gain, Integer current, Integer dist, ArrayList<Integer> path) {
        if (current == dist) {
            ArrayList<Integer> tmp = (ArrayList<Integer>) path.clone();
            forwardPaths.add(new Pair(tmp, gain));
            return;
        }
        Node node = nodes.get(current);
        ArrayList<Edge> edges = node.getEdges();
        for (Edge edge : edges) {
            if (!path.contains(edge.getDestination())) {
                path.add(edge.getDestination());
                findForwardPathes(gain * edge.getCost(), edge.getDestination(), dist, path);
                path.remove(path.size() - 1);
            }
        }
    }

    private void findLoops(int gain, Integer current, ArrayList<Integer> loop, ArrayList<Integer> gains) {
        if (loop.contains(current)) {
            loops.add(new Pair(new ArrayList<Integer>(loop.subList(loop.indexOf(current), loop.size())),
                    gain / gains.get(loop.indexOf(current))));
            return;
        }
        loop.add(current);
        Node node = nodes.get(current);
        ArrayList<Edge> edges = node.getEdges();
        for (Edge edge : edges) {
            gains.add(gain * edge.getCost());
            findLoops(gain * edge.getCost(), edge.getDestination(), loop, gains);
            gains.remove(gains.size() - 1);
        }
        loop.remove(loop.size() - 1);
    }

    private ArrayList<Pair> filterPath(Pair pair) {
        ArrayList<Pair> tmp = new ArrayList<>();
        ArrayList<ArrayList<Integer>> cond = new ArrayList<>();
        cond.add(pair.getPath());
        for (int i = 0; i < loops.size(); i++) {
            cond.add(loops.get(i).getPath());
            if (check(cond)) {
                tmp.add(loops.get(i));
            }
            cond.remove(cond.size() - 1);
        }
        return tmp;
    }

    private Integer findDelta(ArrayList<Pair> list, ArrayList<ArrayList<Integer>> listSoFar, int start, int sign,
            int gain) {
        int general = 0;
        if (check(listSoFar)) {
            for (int i = start; i < list.size(); i++) {
                listSoFar.add(list.get(i).getPath());
                general = general + findDelta(list, listSoFar, i, sign * -1, list.get(i).getGain() * gain);
                listSoFar.remove(listSoFar.size() - 1);
            }
            general = general + sign * gain;
        }
        return general;
    }

    private boolean check(ArrayList<ArrayList<Integer>> list) {
        HashMap<Integer, Integer> tmp = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                if (tmp.containsKey(list.get(i).get(j)))
                    return false;
                tmp.put(list.get(i).get(j), 1);
            }
        }
        return true;
    }

    public static Graph getInstance() {
        if (graph == null) {
            graph = new Graph();
        }
        return graph;
    }

}
