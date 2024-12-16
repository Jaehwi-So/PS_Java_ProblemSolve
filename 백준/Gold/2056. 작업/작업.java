import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] D = new int[n+1];
        List<Integer>[] graph = new ArrayList[n+1];
        int[] time = new int[n+1];
        int[] dp = new int[n+1];

        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList();
        }

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            dp[i] = time[i];
            int k = Integer.parseInt(st.nextToken());
            for(int j = 0; j < k; j++){
                int node = Integer.parseInt(st.nextToken());
                graph[node].add(i);
                D[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= n; i++){
            if(D[i] == 0) queue.offer(i);
        }

        int max = 0;
        while(!queue.isEmpty()){
            int current = queue.poll();
            max = Math.max(dp[current], max);
            for(int next : graph[current]){
                D[next]--;
                dp[next] = Math.max(dp[next], time[next] + dp[current]);
                if(D[next] == 0) queue.offer(next);
            }
        }
        
        System.out.println(max);
    }
}
