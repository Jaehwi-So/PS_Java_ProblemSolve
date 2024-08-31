import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] scv;
    static int[][][] dp;
    static int[] damege = {9 ,3, 1};
    static final int INF = 10000000;

    static int attack(int h1, int h2, int h3){
        h1 = Math.max(h1, 0);
        h2 = Math.max(h2, 0);
        h3 = Math.max(h3, 0);

        if(dp[h1][h2][h3] == INF){
            if(h1 == 0 && h2 == 0 && h3 == 0){
                dp[h1][h2][h3] = 0;
            }
            else{
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        for(int k = 0; k < 3; k++){
                            if(i != j && j != k && i != k){
                                dp[h1][h2][h3] = Math.min(dp[h1][h2][h3], 1 + attack(h1 - damege[i], h2 - damege[j], h3 - damege[k]));
                            }
                        }
                    }
                }
            }
        }
        return dp[h1][h2][h3];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        scv = new int[3];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            scv[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[61][61][61];

        for(int[][] mat: dp){
            for(int[] line : mat){
                Arrays.fill(line, INF);
            }
        }

        int result = attack(scv[0], scv[1], scv[2]);

        System.out.println(result);
    }
}