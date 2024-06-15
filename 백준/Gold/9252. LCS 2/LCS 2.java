import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static char[] chs1;
    static char[] chs2;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        chs1 = st.nextToken().toCharArray();
        st = new StringTokenizer(br.readLine());
        chs2 = st.nextToken().toCharArray();
        dp = new int[chs1.length+1][chs2.length+1];


        for(int i = 0; i < chs1.length; i++){
            for(int j = 0; j < chs2.length; j++){
                if(chs1[i] == chs2[j]){
                    dp[i+1][j+1] = dp[i][j] + 1;
                }
                else{
                    dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }

        int length = dp[chs1.length][chs2.length];
        System.out.println(length);

        if(length != 0){
            StringBuilder sb = new StringBuilder();
            int i = chs1.length;
            int j = chs2.length;
            while(i > 0 && j > 0) {
                if (dp[i][j] == dp[i-1][j]) {
                    i--;
                }
                else if (dp[i][j] == dp[i][j-1]) {
                    j--;
                }
                else{
                    sb.insert(0, chs1[i-1]);
                    i--;
                    j--;
                }
            }
            System.out.println(sb);
        }

    }
}
