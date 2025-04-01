import java.io.*;
import java.util.*;

public class Main {
    static int t;
    static int[] array;
    static int[][] dp;
    static int calc(int k, int start){
        if(dp[k][start] == -1){
            int count = 0;

            if(start == t){
                if(array[start] >= k){
                    count = 1;
                }
            }

            else{
                for(int i = 0; i <= array[start] && i <= k; i++){
                    if(array[start] >= i){
                        count += calc(k-i, start+1);
                        count %= 1000000;
                    }
                }
            }

            dp[k][start] = count;
        }
        return dp[k][start];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken()); //1 ~ 3
        int a = Integer.parseInt(st.nextToken()); //5개
        int s = Integer.parseInt(st.nextToken()); //2
        int b = Integer.parseInt(st.nextToken()); //3
        st = new StringTokenizer(br.readLine());
        array = new int[t+1];
        for(int i = 1; i <= a; i++){
            array[Integer.parseInt(st.nextToken())]++;
        }

        dp = new int[b+1][t+1];
        for(int[] line: dp){
            Arrays.fill(line, -1);
        }

        int result = 0;
        for(int i = s; i <= b; i++){
            result += calc(i, 1);
            result %= 1000000;
        }
        System.out.println(result);

    }
}