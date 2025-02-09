import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //노드 수
        int p = Integer.parseInt(st.nextToken()); //케이블 개수
        int k = Integer.parseInt(st.nextToken()); //공짜 케이블 개수

        List<int[]>[] graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < p; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, w});
            graph[b].add(new int[]{a, w});
        }

        int[][] dp = new int[n+1][k+1];
        for(int[] line : dp){
            Arrays.fill(line, Integer.MAX_VALUE);
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0, 0});
        dp[1][0] = 0;

        while(!queue.isEmpty()){
            int[] current = queue.poll();

            if(dp[current[0]][current[2]] < current[1]) continue;

            for(int[] next : graph[current[0]]){
                int node = next[0];
                int weight = next[1];
                if(dp[node][current[2]] > Math.max(current[1], weight)){
                    dp[node][current[2]] = Math.max(current[1], weight);
                    queue.offer(new int[]{node, dp[node][current[2]], current[2]});
                }
                if(current[2] + 1 <= k && dp[node][current[2] + 1] > current[1]){
                    dp[node][current[2]+1] = current[1];
                    queue.offer(new int[]{node, dp[node][current[2]+1], current[2]+1});
                }
            }
        }

        int result = Integer.MAX_VALUE;

//        for(int[] line : dp){
//            System.out.println(Arrays.toString(line));
//        }
        for(int i = 0; i <= k; i++){
            result = Math.min(result, dp[n][i]);
        }
        if(result == Integer.MAX_VALUE) result = -1;
        System.out.println(result);
    }
}