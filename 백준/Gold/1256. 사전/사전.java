import java.util.Scanner;

public class Main {
    static long[][] dp;
    static final long INF = 1000000001;
    static StringBuilder sb = new StringBuilder();
    static void get(int a, int z, long k){
        if(k > dp[a][z]){
            sb.setLength(0);
            sb.append(-1);
            return;
        }

        if(a == 0){
            for(int i = 1; i <= z; i++) {
                sb.append("z");
            }
        }
        else if(z == 0){
            for(int i = 1; i <= a; i++) {
                sb.append("a");
            }
        }
        else{
            if(dp[a-1][z] >= k){
                sb.append("a");
                get(a-1, z, k);
            }
            else{
                sb.append("z");
                get(a, z-1, k-dp[a-1][z]);
            }
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long k = sc.nextLong();

        int s = n + m;

        dp = new long[s+1][s+1];

        for(int i = 0; i <= s; i++){
            for(int j = 0; j <= s; j++){
                if(i == 0 || j == 0) dp[i][j] = 1;
                else dp[i][j] = Math.min(INF, dp[i-1][j] + dp[i][j-1]);
            }
        }

        get(n, m, k);
        System.out.println(sb);

    }
}