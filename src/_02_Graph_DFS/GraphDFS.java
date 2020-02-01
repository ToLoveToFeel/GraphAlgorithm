package _02_Graph_DFS;

import java.util.ArrayList;

public class GraphDFS {

    private Graph G;
    private boolean[] visited;

    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();

    public GraphDFS(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                dfs(v);
    }

    public void dfs(int v){
        visited[v] = true;
        pre.add(v);
        for (int w : G.adj(v))
            if (!visited[w])
                dfs(w);
        post.add(v);
    }

    // 图的前序遍历结果
    public Iterable<Integer> pre(){
        return pre;
    }

    // 图的后序遍历结果
    public Iterable<Integer> post(){
        return post;
    }

    public static void main(String[] args) {
        Graph g = new Graph("./src/_02_Graph_DFS/g.txt");
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.println(graphDFS.pre());
        System.out.println(graphDFS.post());
    }
}
