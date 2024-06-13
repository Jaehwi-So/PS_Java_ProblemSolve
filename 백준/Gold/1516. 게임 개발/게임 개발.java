import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[][] adjMat;
    static int[] dp;
    static boolean[] visited;
    static int dfs(int index){
        if(dp[index] == 0){
            for(int i = 1; i <= n; i++){
                if(visited[i] == false && adjMat[index][i] != 0){
                    dp[index] = Math.max(dp[index], dfs(i) + adjMat[index][i]);
                }
            }
        }

        return dp[index];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        adjMat = new int[n+1][n+1];
        dp = new int[n+1];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            String s;
            boolean flag = false;
            while(!(s = st.nextToken()).equals("-1")){
                int k = Integer.parseInt(s);
                adjMat[i][k] = t;
                flag = true;
            }
            if(flag == false){
               dp[i] = t;
            }
        }

//        for(int[] line : adjMat){
//            System.out.println(Arrays.toString(line));
//        }

        visited = new boolean[n+1];

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            Arrays.fill(visited, false);
            sb.append(dfs(i)).append("\n");
        }

        System.out.println(sb);

    }
}