package _04_Graph_BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CC {
    // BFS求图的连通分量个数
    private Graph G;
    private int[] visited;  // 不同的连通分量visited值不同，从0开始，-1代表还未访问该节点
    private int cccount = 0;

    public CC(Graph G){

        this.G = G;
        visited = new int[G.V()];
        for (int i = 0; i < G.V(); i++)
            visited[i] = -1;

        for (int v = 0; v < G.V(); v++)
            if (visited[v] == -1) {
                bfs(v, cccount);
                cccount++;
            }
    }

    private void bfs(int s, int ccid){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = ccid;
        while (!queue.isEmpty()){
            int v = queue.remove();
            for (int w : G.adj(v))
                if (visited[w] == -1){
                    queue.add(w);
                    visited[w] = ccid;
                }
        }
    }

    // 返回连通分量个数
    public int count(){
        return cccount;
    }

    // 判断两节点是否连通
    public boolean isConnected(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    // 查询各个连通分量
    public ArrayList<Integer>[] components(){
        ArrayList<Integer>[] res = new ArrayList[cccount];
        for (int i = 0; i < res.length; i++)
            res[i] = new ArrayList<>();

        for (int v = 0; v < G.V(); v++)
            res[visited[v]].add(v);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("./src/_04_Graph_BFS/g.txt");
        CC cc = new CC(g);
        System.out.println(cc.count());

        System.out.println(cc.isConnected(0, 6));
        System.out.println(cc.isConnected(5, 6));

        ArrayList<Integer>[] comp = cc.components();
        for (int ccid = 0; ccid < comp.length; ccid++){
            System.out.print(ccid + " : ");
            for (int w : comp[ccid]) {
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }

}
