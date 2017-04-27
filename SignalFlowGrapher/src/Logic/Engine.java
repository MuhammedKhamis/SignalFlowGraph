package Logic;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

import Controller.Graph;
import Util.Pair;

public class Engine implements IEngine {

    private HashMap<String, Integer> map;

    private HashMap<Integer, String> map1;

    private int counter;

    private Graph graph;

    public Engine() {
        // TODO Auto-generated constructor stub
        map = new HashMap<>();
        map1 = new HashMap<>();
        counter = 0;
        graph = Graph.getInstance();

    }

    @Override
    public void addNode(String nodeName) {
        // TODO Auto-generated method stub
        map.put(nodeName, counter);
        map1.put(counter, nodeName);
        graph.addNode(counter);
        counter++;

    }

    @Override
    public void addEdge(String fromNode, String toNode, double value) {
        // TODO Auto-generated method stub
        if (!map.containsKey(toNode) || !map.containsKey(fromNode)) {
            return;
        }
        graph.addEdge(map.get(fromNode), map.get(toNode), value);

    }

    @Override
    public void modifyNodeName(String oldName, String newName) {
        // TODO Auto-generated method stub
        if (!map.containsKey(oldName)) {
            return;
        }
        Integer val = map.remove(oldName);
        map.put(newName, val);
        map1.put(val, newName);

    }

    @Override
    public void modifyEdgeValue(String startNode, String endNode, double newValue) {
        // TODO Auto-generated method stub
        if (!map.containsKey(startNode) || !map.containsKey(endNode)) {
            return;
        }
        graph.modifyCost(map.get(startNode), map.get(endNode), newValue);

    }

    @Override
    public double calculateTFFunction(String fromNode, String toNode) {
        // TODO Auto-generated method stub

        if (!map.containsKey(toNode) || !map.containsKey(fromNode)) {
            return 0.0;
        }
        graph.solve(map.get(fromNode), map.get(toNode));
        return graph.getGain();
    }

    public String getForwardPaths() {
        ArrayList<Pair> tmp = graph.getForwardPaths();
        ArrayList<ArrayList<String>> forwardPathes = new ArrayList<>();
        for (int i = 0; i < tmp.size(); i++) {
            ArrayList<String> sub = new ArrayList<>();
            for (int j = 0; j < tmp.get(i).getPath().size(); j++) {
                sub.add(map1.get(tmp.get(i).getPath().get(j)));
            }
            forwardPathes.add(sub);
        }
        return turnToString(forwardPathes);
    }

    public String getIndividualLoops() {
        ArrayList<Pair> tmp = graph.getLoops();
        ArrayList<ArrayList<String>> loops = new ArrayList<>();
        for (int i = 0; i < tmp.size(); i++) {
            ArrayList<String> sub = new ArrayList<>();
            for (int j = 0; j < tmp.get(i).getPath().size(); j++) {
                sub.add(map1.get(tmp.get(i).getPath().get(j)));
            }
            loops.add(sub);
        }
        return turnToString(loops);
    }

    public String getNonTouchingLoops() {
        Hashtable<Integer, ArrayList<ArrayList<ArrayList<Integer>>>> combinations = graph.getCombinations();
        Hashtable<Integer, ArrayList<ArrayList<ArrayList<String>>>> res = new Hashtable<>();
        Hashtable<Integer, ArrayList<String>> finalRes = new Hashtable<>();
        Enumeration<Integer> enumKey = combinations.keys();
        while (enumKey.hasMoreElements()) {
            Integer key = enumKey.nextElement();
            ArrayList<ArrayList<ArrayList<String>>> tmp = new ArrayList<>();
            ArrayList<ArrayList<ArrayList<Integer>>> combination = combinations.get(key);
            for (int i = 0; i < combination.size(); i++) {
                ArrayList<ArrayList<String>> tmp1 = new ArrayList<>();
                for (int j = 0; j < combination.get(i).size(); j++) {
                    ArrayList<String> tmp2 = new ArrayList<>();
                    for (int k = 0; k < combination.get(i).get(j).size(); k++) {
                        tmp2.add(map1.get(combination.get(i).get(j).get(k)));
                    }
                    tmp1.add(tmp2);
                }
                tmp.add(tmp1);
            }
            res.put(key, tmp);
        }
        enumKey = res.keys();
        while (enumKey.hasMoreElements()) {
            Integer key = enumKey.nextElement();
            ArrayList<ArrayList<ArrayList<String>>> tmp = res.get(key);
            ArrayList<String> tmp2 = new ArrayList<>();
            for (int i = 0; i < tmp.size()-1; i++) {
                tmp2.add(turnToString(tmp.get(i))+", ");
            }
            tmp2.add(turnToString(tmp.get(tmp.size()-1)));
            finalRes.put(key, tmp2);
        }


        return hashToString(finalRes);
    }

    public String getDeltas() {
    	String result="Main Delta : "+String.valueOf(graph.getDelta());
    	result+="\n";
        ArrayList<Double> tmp = graph.getDeltas();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tmp.size(); i++) {
            builder.append("Delta " + (i + 1) + ": " + tmp.get(i) + "\n");
        }
        result+=builder.toString();
        return result;
    }

    public void clearCanvas() {
    	graph = Graph.clearObject();
        map1 = new HashMap<>();
        map = new HashMap<>();
        counter = 0;
    }

    private String turnToString(ArrayList<ArrayList<String>> input) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < input.size(); i++) {
            builder.append((i + 1) + ": ");
            for (int j = 0; j < input.get(i).size() - 1; j++) {
                builder.append(input.get(i).get(j) + "->");
            }
            builder.append(input.get(i).get(input.get(i).size() - 1));
            builder.append("\n");
        }

        return builder.toString();
    }
    
    
    private String hashToString(Hashtable<Integer, ArrayList<String>> hashtable) {
        StringBuilder builder = new StringBuilder();
        Enumeration<Integer> enumKey = hashtable.keys();
        while (enumKey.hasMoreElements()) {
            Integer key = enumKey.nextElement();
            ArrayList<String> tmp = hashtable.get(key);
            builder.append(key + " Non-Touching Loops\n");
            for (int i = 0; i < tmp.size(); i++) {
                builder.append(tmp.get(i) + "\n");
            }
        }
        return builder.toString();
    }

}
