import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static char[] chs1;
    static char[] chs2;
    static char[] chs3;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        chs1 = st.nextToken().toCharArray();
        st = new StringTokenizer(br.readLine());
        chs2 = st.nextToken().toCharArray();
        st = new StringTokenizer(br.readLine());
        chs3 = st.nextToken().toCharArray();
        dp = new int[chs1.length+1][chs2.length+1][chs3.length+1];


        for(int i = 0; i < chs1.length; i++){
            for(int j = 0; j < chs2.length; j++){
                for(int k = 0; k < chs3.length; k++){
                    if(chs1[i] == chs2[j] && chs2[j] == chs3[k]){
                        dp[i+1][j+1][k+1] = dp[i][j][k] + 1;
                    }
                    else{
                        dp[i+1][j+1][k+1] = Math.max(Math.max(dp[i+1][j+1][k], dp[i][j+1][k+1]), dp[i+1][j][k+1]);
                    }
                }

            }
        }

//        for(int[][] mat : dp){
//            for(int[] line: mat){
//                System.out.println(Arrays.toString(line));
//            }
//        }

        int length = dp[chs1.length][chs2.length][chs3.length];
        System.out.println(length);



    }
}