import java.io.*;
import java.util.*;

class Node{
    int index;
    int amount;
    public Node(int index, int amount){
        this.index = index;
        this.amount = amount;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int[] D = new int[n+1];
        List<Node>[] graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());
            graph[parent].add(new Node(child, amount));
            D[child]++;
        }

        int[] dp = new int[n+1];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(n, 1));
        dp[n] = 1;


        boolean[] isStart = new boolean[n+1];
        while(!queue.isEmpty()){
            Node current = queue.poll();

            if(graph[current.index].size() == 0){
                isStart[current.index] = true;
            }
            else{
                for(Node next : graph[current.index]){
                    D[next.index]--;
                    dp[next.index] += dp[current.index] * next.amount;
                    if(D[next.index] == 0){
                        queue.offer(next);
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            if(isStart[i]){
                sb.append(i + " " + dp[i]).append("\n");
            }
        }

        System.out.print(sb);

    }
}