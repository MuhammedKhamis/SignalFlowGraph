package engine;

public class EnginMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        IEngine engine = new Engine();
        engine.addNode("g0");
        engine.addNode("g1");
        engine.addNode("g2");
        engine.addEdge("g0", "g1", 113);
        engine.addEdge("g1", "g2", 60);
        engine.modifyEdgeValue("g0", "g1", 60);
        double result = engine.calculateTFFunction("g0", "g2");
        System.out.println(result);
        engine.modifyEdgeValue("g0", "g1", 80);
        result = engine.calculateTFFunction("g0", "g2");
        System.out.println(result);
    }

}
