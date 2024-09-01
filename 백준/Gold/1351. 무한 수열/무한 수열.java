import java.io.*;
import java.util.*;

public class Main {
    static long p;
    static long q;
    static long[] dp;
    static int max = 10000000;

    static long get(long n){
        if(n >= max){
            return get(n/p) + get(n/q);
        }
        else{
            int idx = (int)n;
            if(dp[idx] == -1){
                dp[idx] = get(n/p) + get(n/q);
            }
            return dp[idx];
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        p = Long.parseLong(st.nextToken());
        q = Long.parseLong(st.nextToken());

        long length = Math.min(n + 1, max);
        dp = new long[(int)length];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        System.out.println(get(n));
    }
}