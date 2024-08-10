import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int test = Integer.parseInt(st.nextToken());

        for(int t = 0; t < test; t++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[] cost = new int[n+1];
            int[] D = new int[n+1];

            List<Integer>[] graph = new LinkedList[n+1];
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++){
                cost[i] = Integer.parseInt(st.nextToken());
                graph[i] = new LinkedList<>();
            }

            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                D[b]++;
            }

            long[] dp = new long[n+1];
            Arrays.fill(dp, Long.MIN_VALUE);
            Queue<Integer> queue = new LinkedList<>();
            for(int i = 1; i <= n; i++){
                if(D[i] == 0){
                    queue.offer(i);
                    dp[i] = cost[i];
                }
            }

            st = new StringTokenizer(br.readLine());
            int result = Integer.parseInt(st.nextToken());

            while(!queue.isEmpty()){
                int current = queue.poll();
                if(current == result){
                    break;
                }
                for(int next : graph[current]){
                    D[next]--;
                    dp[next] = Math.max(dp[next], dp[current] + cost[next]);
                    if(D[next] == 0){
                        queue.offer(next);
                    }
                }
            }

            sb.append(dp[result]).append("\n");
//            System.out.println(Arrays.toString(dp));
        }

        System.out.println(sb);
    }
}