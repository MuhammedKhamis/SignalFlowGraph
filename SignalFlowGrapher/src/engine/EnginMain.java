package engine;

public class EnginMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        IEngine engine = new Engine();
        engine.addNode("g0");
        engine.addNode("g1");
        engine.addNode("g2");
        engine.addNode("g3");
        engine.addNode("g4");
        engine.addEdge("g0", "g1", 1);
        engine.addEdge("g1", "g2", 1);
        engine.addEdge("g2", "g3", 1);
        engine.addEdge("g3", "g4", 1);
        engine.addEdge("g0", "g2", 1);
        engine.addEdge("g3", "g2", -1);
        engine.addEdge("g1", "g0", -1);
        double result = engine.calculateTFFunction("g0", "g4");
        engine.getCombinations();
        System.out.println(result);
    }

}
