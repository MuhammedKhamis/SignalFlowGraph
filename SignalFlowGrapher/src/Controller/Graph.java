package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

import Model.Edge;
import Model.Node;
import Util.Pair;

public class Graph {

    private static Graph graph;

    private Hashtable<Integer, Node> nodes;

    private ArrayList<Pair> forwardPaths;

    private ArrayList<Pair> loops;

    private Hashtable<Integer, ArrayList<ArrayList<ArrayList<Integer>>>> combinations;

    private double delta;

    private ArrayList<Double> deltas;

    private double gain;

    private static final double initial = 1;

    private Graph() {
        gain = 0;
        nodes = new Hashtable<>();
        forwardPaths = new ArrayList<>();
        loops = new ArrayList<>();
        delta = 0;
        deltas = new ArrayList<>();
        combinations = new Hashtable<>();
    }

    public boolean addNode(int name) {
        if (nodes.containsKey(name)) {
            return false;
        }
        nodes.put(name, new Node(name));
        return true;

    }

    public boolean removeNode(Integer name) {
        if (!nodes.containsKey(name)) {
            return false;
        }
        Enumeration<Integer> enumKey = nodes.keys();
        while (enumKey.hasMoreElements()) {
            Integer key = enumKey.nextElement();
            Node node = nodes.get(key);
            node.removeEdge(name);
        }
        nodes.remove(name);
        return true;
    }

    public boolean addEdge(Integer from, Integer to, Double cost) {
        if (!nodes.containsKey(from)) {
            return false;
        }
        return nodes.get(from).addEdge(to, cost);
    }

    public boolean modifyCost(Integer from, Integer to, double cost) {
        if (!nodes.containsKey(from) || !nodes.containsKey(to)) {
            return false;
        }
        return nodes.get(from).modifyCost(to, cost);
    }

    public boolean removeEdge(int from, int to) {
        if (!nodes.containsKey(from)) {
            return false;
        }
        return nodes.get(from).removeEdge(to);
    }

    public boolean solve(Integer from, Integer to) {
        if (!nodes.containsKey(from) || !nodes.containsKey(to)) {
            return false;
        }
        reset();
        ArrayList<Integer> tmpy = new ArrayList<>();
        tmpy.add(from);
        findForwardPathes(initial, from, to, tmpy);
        ArrayList<Double> gains = new ArrayList<>();
        gains.add(initial);
        findLoops(initial, from, new ArrayList<>(), gains);
        filterLoops();
        delta = findDelta(loops, new ArrayList<>(), 0, 1, initial, true);
        for (int i = 0; i < forwardPaths.size(); i++) {
            ArrayList<Pair> tmp = filterPath(forwardPaths.get(i));
            Double val = findDelta(tmp, new ArrayList<>(), 0, 1, initial, false);
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

    public Hashtable<Integer, ArrayList<ArrayList<ArrayList<Integer>>>> getCombinations() {
        return combinations;
    }

    public ArrayList<Double> getDeltas() {
        return deltas;
    }

    public double getDelta() {
        return delta;
    }

    private void filterLoops() {
        ArrayList<Pair> tmp = new ArrayList<>();
        boolean flag;
        for (int i = 0; i < loops.size(); i++) {
            Collections.sort(loops.get(i).getPath());
        }
        for (int i = 0; i < loops.size(); i++) {
            flag = true;
            for (int j = i + 1; j < loops.size(); j++) {
                if (areEqual(loops.get(i).getPath(), loops.get(j).getPath())) {
                    flag = false;
                }
            }
            if (flag) {
                tmp.add(loops.get(i));
            }
        }
        loops = tmp;
    }

    private boolean areEqual(ArrayList<Integer> l1, ArrayList<Integer> l2) {
        if (l2.size() != l1.size()) {
            return false;
        }
        for (int i = 0; i < l1.size(); i++) {
            if (l1.get(i) != l2.get(i)) {
                return false;
            }
        }
        return true;
    }

    private void findForwardPathes(double gain, Integer current, Integer dist, ArrayList<Integer> path) {
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

    private void findLoops(double gain, Integer current, ArrayList<Integer> loop, ArrayList<Double> gains) {
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

    private Double findDelta(ArrayList<Pair> list, ArrayList<ArrayList<Integer>> listSoFar, int start, int sign,
            double gain, boolean combination) {
        double general = 0;
        if (check(listSoFar)) {
            if (combination && listSoFar.size() >= 2) {
                if (combinations.containsKey(listSoFar.size())) {
                    combinations.get(listSoFar.size()).add((ArrayList<ArrayList<Integer>>) listSoFar.clone());
                } else {
                    combinations.put(listSoFar.size(), new ArrayList<>());
                    combinations.get(listSoFar.size()).add((ArrayList<ArrayList<Integer>>) listSoFar.clone());
                }
            }
            for (int i = start; i < list.size(); i++) {
                listSoFar.add(list.get(i).getPath());
                general = general + findDelta(list, listSoFar, i, sign * -1, list.get(i).getGain() * gain, combination);
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

    public static Graph clearObject() {
        return graph = new Graph();
    }

    private void reset() {
        forwardPaths = new ArrayList<>();
        loops = new ArrayList<>();
        delta = 0;
        deltas = new ArrayList<>();
        gain = 0;
        combinations = new Hashtable<>();

    }

}
