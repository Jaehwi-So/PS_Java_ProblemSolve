import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Integer>[] graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        int[] D = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            int parent = Integer.parseInt(st.nextToken());
            if(parent != -1){
                graph[parent].add(i);
                D[i]++;
            }
        }

        int[] dp = new int[n+1];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dp[node] += cost;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        while(!queue.isEmpty()){
            int current = queue.poll();
            for(int next : graph[current]){
                D[next]--;
                dp[next] += dp[current];
                if(D[next] == 0){
                    queue.offer(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            sb.append(dp[i]).append(" ");
        }
        System.out.println(sb);

    }
}