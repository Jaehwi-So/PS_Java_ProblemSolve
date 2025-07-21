import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] nodes;
    static int[][][] dp; //dp[left][right][pos]  left~right까지 범위를 방문했을 때 L/R에 위치했을 경우
    static final int MAX = 1000000000;

    static int operate(int left, int right, int pos){
        if(dp[left][right][pos] == -1){
            int remain = n - (right - left);
            int current = left;
            if(pos == 1) current = right;
            dp[left][right][pos] = MAX;

            if(left > 0){
                dp[left][right][pos] = Math.min(dp[left][right][pos],
                        operate(left-1, right, 0) + (remain * Math.abs(nodes[current] - nodes[left-1])));
            }

            if(right < n){
                dp[left][right][pos] = Math.min(dp[left][right][pos],
                        operate(left, right+1, 1) + (remain * Math.abs(nodes[current] - nodes[right+1])));
            }

            dp[left][right][pos] = Math.min(MAX, dp[left][right][pos]);

        }

        return dp[left][right][pos];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        nodes = new int[n+1];
        int l = Integer.parseInt(st.nextToken());
        nodes[0] = l;

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            nodes[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nodes);

        int start = -1;
        for(int i = 0; i <= n; i++){
            if(nodes[i] == l) start = i;
        }
        dp = new int[n+1][n+1][2];

        for(int[][] mat : dp){
            for(int[] line : mat){
                Arrays.fill(line, -1);
            }
        }
        dp[0][n][0] = 0;
        dp[0][n][1] = 0;

        System.out.println(operate(start, start, 0));


    }
}