import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int c;
    static int[] gems;
    static int[][][] dp; //가방 사용 수, 가방 무게, 방문

    static int convert(boolean[] visited){
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            if(visited[i]) sb.append(1);
            else sb.append(0);
        }
        String s = sb.toString();
        return Integer.parseInt(s, 2);
    }
    static int calc(int index, int weight, boolean[] visited){
        int vbit = convert(visited);

        if(dp[index][weight][vbit] == -1){
            dp[index][weight][vbit] = 0;
            for(int i = 1; i <= n; i++){
                if(visited[i] || gems[i] > c) continue;
                int w = weight + gems[i];
                boolean[] v = Arrays.copyOf(visited, visited.length);
                if(w > c){
                    if(index < m) dp[index][weight][vbit] = Math.max(dp[index][weight][vbit], calc(index + 1, 0, v));
//                    else if(w == c) dp[index][weight][vbit] = Math.max(dp[index][weight][vbit], 1);
                }
                else{
                    v[i] = true;
                    dp[index][weight][vbit] = Math.max(dp[index][weight][vbit], calc(index, w, v) + 1);
                }
            }
//            System.out.println(index + " " + weight + " " + vbit + " " + dp[index][weight][vbit]);
        }

        return dp[index][weight][vbit];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        int vn = (int)Math.pow(2, n);
//        System.out.println(vn);
        dp = new int[m+1][c+1][vn]; //가방 번호, 가방 무게, 방문
        for(int[][] mat : dp){
            for(int[] line : mat){
                Arrays.fill(line, -1);
            }
        }

        gems = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            gems[i] = Integer.parseInt(st.nextToken());
        }

        int result = calc(1, 0, new boolean[n+1]);
        System.out.println(result);

    }

    // 1 0  1 2  1 4  2 1  2 3

}