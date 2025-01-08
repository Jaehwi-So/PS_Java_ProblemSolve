import java.io.*;
import java.util.*;

public class Main {
    static int dist(int x1, int y1, int x2, int y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    static int n;
    static int w;
    static int[][] works;
    static int[][] dp;
    static int[][] trace;

    // 남은 거리 계산하기
    static int calc(int p1, int p2){
        if(dp[p1][p2] == -1){
            int next = Math.max(p1, p2) + 1;
            if(next == w + 2){
                dp[p1][p2] = 0;
            }
            else{
                // 1번이 이동
                int moveA = calc(next, p2) + dist(works[p1][0], works[p1][1], works[next][0], works[next][1]);
                // 2번이 이동
                int moveB = calc(p1, next) + dist(works[p2][0], works[p2][1], works[next][0], works[next][1]);
                if(moveA < moveB){
                    dp[p1][p2] = moveA;
                    trace[p1][p2] = 1;
                }
                else{
                    dp[p1][p2] = moveB;
                    trace[p1][p2] = 2;
                }

            }
        }
        return dp[p1][p2];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        works = new int[w+2][2];
        dp = new int[w+2][w+2];
        trace = new int[w+2][w+2];
        for(int[] line : dp){
            Arrays.fill(line, -1);
        }

        works[0][0] = 1;
        works[0][1] = 1;
        works[1][0] = n;
        works[1][1] = n;

        for (int i = 2; i < w + 2; i++) {
            st = new StringTokenizer(br.readLine());
            works[i][0] = Integer.parseInt(st.nextToken());
            works[i][1] = Integer.parseInt(st.nextToken());
        }


//        for(int[] line : dp){
//            System.out.println(Arrays.toString(line));
//        }

        StringBuilder sb = new StringBuilder();
        sb.append(calc(0, 1)).append("\n");

        int w1 = 0;
        int w2 = 1;
        for(int i = 2; i < w + 2; i++){
            sb.append(trace[w1][w2]).append("\n");
            if(trace[w1][w2] == 1) w1 = i;
            else w2 = i;
        }
        System.out.println(sb);
    }
}