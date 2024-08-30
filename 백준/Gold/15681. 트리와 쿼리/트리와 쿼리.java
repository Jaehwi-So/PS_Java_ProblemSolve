import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node{
    int index;
    List<Node> childs;
    int count;
    public Node(int index){
        this.index = index;
        this.childs = new ArrayList<>();
        this.count = 1;
    }

    public int preorder(){
        for(Node n : childs){
            count += n.preorder();
        }
        return count;
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[n+1];
        Node[] nodes = new Node[n+1];
        List<Integer>[] graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            nodes[i] = new Node(i);
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < n - 1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        Queue<Integer> queue = new LinkedList();
        queue.offer(r);
        while(!queue.isEmpty()){
            int current = queue.poll();
            if(!visited[current]){
                visited[current] = true;
                for(int next : graph[current]){
                    if(!visited[next]){
                        nodes[current].childs.add(nodes[next]);
                        queue.offer(next);
                    }
                }
            }
        }

        nodes[r].preorder();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            sb.append(nodes[query].count).append("\n");
        }
        System.out.println(sb);
    }
}
