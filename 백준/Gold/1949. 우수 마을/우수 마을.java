import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n+1][2]; //포함O, 포함X

        List<Integer>[] graph = new ArrayList[n+1];
        int[] D = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            dp[i][0] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
            D[a]++;
            D[b]++;
        }


        Queue<Integer> queue = new LinkedList<>();

        for(int i = 1; i <= n; i++){
            if(D[i] == 1){
                queue.offer(i);
            }
        }

        int last = 0;
        while(!queue.isEmpty()){
            int current = queue.poll();
            last = current;
            for(int next : graph[current]){
                D[next]--;
                dp[next][0] += dp[current][1];
                dp[next][1] += Math.max(dp[current][0], dp[current][1]);
                if(D[next] == 1){
                    queue.offer(next);
                }
            }
        }

        int max = 0;
        max = Math.max(dp[last][0], dp[last][1]);
        System.out.println(max);

    }
}
