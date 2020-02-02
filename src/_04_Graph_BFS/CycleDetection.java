package _04_Graph_BFS;

import java.util.LinkedList;
import java.util.Queue;

public class CycleDetection {
    // BFS环检测算法
    private Graph G;
    private boolean[] visited;  // 记录是否访问过
    private int[] pre;  // 记录当前节点来自于哪个节点
    private boolean hasCycle = false;

    public CycleDetection(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];

        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                if (bfs(v)){
                    hasCycle = true;
                    break;
                }
    }

    private boolean bfs(int s){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        while (!queue.isEmpty()){
            int v = queue.remove();
            for (int w : G.adj(v))
                if (!visited[w]){
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                }
                else if (w != pre[v])  // w已经访问过，并且w不是v的父节点，则说明有环
                    return true;
        }
        return false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args){

        Graph g = new Graph("./src/_04_Graph_BFS/g2.txt");
        CycleDetection cycleDetection = new CycleDetection(g);
        System.out.println(cycleDetection.hasCycle());

        Graph g2 = new Graph("./src/_04_Graph_BFS/g3.txt");
        CycleDetection cycleDetection2 = new CycleDetection(g2);
        System.out.println(cycleDetection2.hasCycle());
    }
}
