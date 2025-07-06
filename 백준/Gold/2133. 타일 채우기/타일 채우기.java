import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][][][] dp;

    static int reverse(int k){
        return k == 0 ? 1 : 0;
    }
    static int operate(int index, int f, int s, int t){
        if(index == n+1){
            if(f + s + t == 0) return 1;
            else return 0;
        }
        if(dp[index][f][s][t] == -1){
            dp[index][f][s][t] = 0;
            if(f + s + t == 0){
                dp[index][f][s][t] += operate(index + 1, 1, 0, 0);
                dp[index][f][s][t] += operate(index + 1, 0, 1, 0);
                dp[index][f][s][t] += operate(index + 1, 0, 0, 1);
                dp[index][f][s][t] += operate(index + 1, 1, 1, 1);
            }
            else if(f + s + t == 1){
                if(s == 1){
                    dp[index][f][s][t] = operate(index + 1, 1, 0, 1);
                }
                else if(f == 1){
                    dp[index][f][s][t] += operate(index + 1, 0, 0, 0);
                    dp[index][f][s][t] += operate(index + 1, 0, 1, 1);
                }
                else if(t == 1){
                    dp[index][f][s][t] += operate(index + 1, 0, 0, 0);
                    dp[index][f][s][t] += operate(index + 1, 1, 1, 0);
                }
            }
            else if(f + s + t == 2){
                dp[index][f][s][t] = operate(index + 1, reverse(f), reverse(s), reverse(t));
            }
            else if(f + s + t == 3){
                dp[index][f][s][t] = operate(index + 1, 0, 0, 0);
            }
        }
        return dp[index][f][s][t];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        dp = new int[n+1][2][2][2];
        for(int[][][] a : dp){
            for(int[][] b : a){
                for(int[] line : b){
                    Arrays.fill(line, -1);
                }
            }
        }

        System.out.println(operate(1, 0, 0, 0));

    }
}