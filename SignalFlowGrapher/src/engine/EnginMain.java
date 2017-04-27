package engine;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class EnginMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        IEngine engine = new Engine();
        engine.addNode("g0");
        engine.addNode("g1");
        engine.addNode("g2");
        engine.addNode("g3");
        engine.addNode("g4");
        engine.addNode("g5");
        //////////////////////////////
        engine.addEdge("g0", "g1", 1);
        engine.addEdge("g1", "g2", 1);
        engine.addEdge("g2", "g3", 1);
        engine.addEdge("g3", "g4", 1);
        engine.addEdge("g4", "g5", 1);
        ////////////////////////////////
        engine.addEdge("g1", "g3", 1);
        engine.addEdge("g1", "g4", 1);
        ///////////////////////////////
        engine.addEdge("g3", "g2", -1);
        engine.addEdge("g2", "g1", -1);
        engine.addEdge("g3", "g3", -1);
        double res = engine.calculateTFFunction("g0", "g5");
        
        System.out.println(res);
    }

    public static String hashToString(Hashtable<Integer, ArrayList<String>> hashtable) {
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
