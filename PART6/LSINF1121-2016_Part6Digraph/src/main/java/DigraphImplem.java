import java.util.LinkedList;

public class DigraphImplem implements Digraph {
    int V;
    int E;
    LinkedList<Integer>[] adjs;

    /**
     * @param V is the number of vertices
     */
    public DigraphImplem(int V) {
        this.V=V;
        E=0;
        adjs = new LinkedList[V];
        for (int v = 0; v < V; v++) {
            adjs[v]=new LinkedList<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adjs[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adjs[v];
    }

    public Digraph reverse() {
        Digraph reversed = new DigraphImplem(V);
        for (int v = 0; v < V; v++) {
            for (Integer w:adj(v)) {
                reversed.addEdge(w,v);
            }
        }
        return reversed;
    }
}