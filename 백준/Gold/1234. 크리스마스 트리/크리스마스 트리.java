import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static long[][][][] dp;
    static int[] fac;

    static int factorial(int k){
        if(fac[k] == -1){
            if(k <= 1) fac[k] = 1;
            else fac[k] = k * factorial(k-1);
        }
        return fac[k];
    }

    static long operate(int level, int[] color){
        if(level > n) return 1L;

        if(dp[level][color[0]][color[1]][color[2]] == -1){
            long result = 0;
            for(int i = 0; i < 3; i++){
                if(color[i] >= level){
                    int[] next = Arrays.copyOf(color, 3);
                    next[i] -= level;
                    result += operate(level + 1, next);
                }
            }

            int q = level / 2;
            int sequence = factorial(level) / (factorial(q) * factorial(q));
            if(level % 2 == 0){
                for(int i = 0; i < 2; i++){
                    for(int j = i+1; j < 3; j++) {
                        if(color[i] < q || color[j] < q) continue;
                        int[] next = Arrays.copyOf(color, 3);
                        next[i] -= q;
                        next[j] -= q;
                        result += (operate(level + 1, next) * sequence);
                    }
                }
            }

            q = level / 3;
            sequence = factorial(level) / (factorial(q) * factorial(q) * factorial(q));
            if(level % 3 == 0){
                if(color[0] >= q && color[1] >= q && color[2] >= q){
                    int[] next = Arrays.copyOf(color, 3);
                    next[0] -= q;
                    next[1] -= q;
                    next[2] -= q;
                    result += (operate(level + 1, next) * sequence);
                }
            }


            dp[level][color[0]][color[1]][color[2]] = result;
        }
        return dp[level][color[0]][color[1]][color[2]];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int[] colors = new int[3];
        for(int i = 0; i < 3; i++){
            colors[i] = Integer.parseInt(st.nextToken());
        }


        dp = new long[n+1][101][101][101];
        fac = new int[n+1];

        Arrays.fill(fac, -1);

        for(long[][][] m : dp){
            for(long[][] mat : m){
                for(long[] line : mat){
                    Arrays.fill(line, -1L);
                }
            }
        }

        System.out.println(operate(1, colors));

    }
}