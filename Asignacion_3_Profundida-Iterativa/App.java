public class App {
    static private String EstadoInicial = "248735160";
    static private String EstadoMeta = "234105876";

    public static void main(String[] args) {
        String rootState = EstadoInicial;
        SearchTree search = new SearchTree(new Node(rootState), EstadoMeta);
        search.iterativeDeepening(1000);
    }
}
