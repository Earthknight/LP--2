import java.util.*;

class BFSDFSGraph{
    int nodes;
    ArrayList<ArrayList<Integer>> graph;

    BFSDFSGraph(int V){
        nodes = V;
        graph = new ArrayList<ArrayList<Integer>>();
        for(int i = 0 ; i < V; i++){
            graph.add(new ArrayList<Integer>());
        }
    }

    void addEdge(int u,int v){
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    void recursionBfs(Queue<Integer> q,boolean[] visited,ArrayList<ArrayList<Integer>> graph){
        if(q.isEmpty()){
            return;
        }
        int node = q.poll();
        System.out.println(node);
        for(int x: graph.get(node)){
            if(!visited[x]){
                visited[x] = true;
                q.add(x);
            }
        }
        recursionBfs(q,visited,graph);
    }

    void recursionDfs(int node, boolean visited[]){
        visited[node] = true;
        System.out.println(node);
        for(int x:graph.get(node)){
            if(!visited[x]){
                recursionDfs(x,visited);
            }
        }
    }

}


public class BFSDFS{
   public static void main(String[] args){
       BFSDFSGraph g = new BFSDFSGraph(5);
       g.addEdge(0,1);
       g.addEdge(3,2);
       g.addEdge(2,4);
       g.addEdge(1,4);
       g.addEdge(3,1);
       g.addEdge(2,0);

       Queue<Integer> q = new LinkedList<Integer>();
       boolean[] visitedBFS = new boolean[g.nodes];
       boolean[] visitedDFS = new boolean[g.nodes];
       int start = 1;
       q.add(start);
       visitedBFS[start] = true;
       System.out.println("BFS Traversal");
       g.recursionBfs(q,visitedBFS,g.graph);
       System.out.println("DFS Traversal");
       g.recursionDfs(start,visitedDFS);
   }
}