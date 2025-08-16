import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] numbers;
    static int[][][] dp; //N번째, 앞/뒤 차이, 짝/홀차이
    static final int MOD = 999_983;


    static int operate(int sequence, int fb, int oe){
        if(sequence == (n*2) + 1){
            if(fb == 0 || oe == 500) return 1;
            else return 0;
        }

        if(dp[sequence][fb][oe] == -1){
            boolean isEven = sequence % 2 == 0;
            boolean isFirst = sequence <= n;
            dp[sequence][fb][oe] = 0;

            for(int i = 0; i < m; i++){

                int nextFb = fb;
                if(isFirst) nextFb += numbers[i];
                else nextFb -= numbers[i];

                int nextOe = oe;
                if(isEven) nextOe += numbers[i];
                else nextOe -= numbers[i];

                if(nextFb < 0) nextFb = 500;

                dp[sequence][fb][oe] += operate(sequence + 1, nextFb, nextOe);
                dp[sequence][fb][oe] %= MOD;
            }
        }
        return dp[sequence][fb][oe];
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        char[] chs = st.nextToken().toCharArray();
        m = chs.length;
        numbers = new int[m];

        for(int i = 0; i < m; i++){
            numbers[i] = Character.getNumericValue(chs[i]);
        }

        dp = new int[(n*2)+1][501][1001];  // 1000000
        for(int[][] mat : dp){
            for(int[] line : mat){
                Arrays.fill(line, -1);
            }
        }

        System.out.println(operate(1, 0, 500));

    }
}