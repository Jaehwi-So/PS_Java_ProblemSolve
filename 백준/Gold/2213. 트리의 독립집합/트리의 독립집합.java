import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] W;
    static List<Integer>[] graph;
    static boolean[] visited;
    static int[][] dp;
    static List<Integer> traces = new ArrayList<>();

    static void calc(int node){
        dp[node][1] = W[node];
        for(int next : graph[node]){
            if(!visited[next]){
                visited[next] = true;
                calc(next);
                dp[node][0] += Math.max(dp[next][0], dp[next][1]);
                dp[node][1] += dp[next][0];
            }
        }
    }

    static void trace(int node, int type){
        if(type == 1){
            traces.add(node);
        }
        for(int next : graph[node]){
            if(!visited[next]){
                visited[next] = true;
                if(type == 1){
                    trace(next, 0);
                }
                else{
                    if(dp[next][0] < dp[next][1]){
                        trace(next, 1);
                    }
                    else{
                        trace(next, 0);
                    }

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        W = new int[n+1];
        graph = new ArrayList[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            W[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList();
        }

        String s = "";
        while((s = br.readLine()) != null && !s.isEmpty()){
            st = new StringTokenizer(s);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        visited = new boolean[n+1];
        dp = new int[n+1][2];

        visited[1] = true;
        calc(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));

        visited = new boolean[n+1];
        visited[1] = true;
        if(dp[1][0] < dp[1][1]){
            trace(1, 1);
        }
        else{
            trace(1, 0);
        }

        StringBuilder sb = new StringBuilder();
        Collections.sort(traces);
        for(int i : traces){
            sb.append(i).append(" ");
        }
        System.out.println(sb);

    }
}