import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    //9 -> 18 70
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dict = {0, 0, 1, 7, 4, 2, 0, 8};
        long[] dp = new long[101];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[1] = 100;
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;
        dp[7] = 8;


        // MIN
        for(int i = 8; i <= 100; i++){
            for(int j = 2; j <= 7; j++){
                //성냥개비 i개로 만들 수 있는 가장 작은 수 = "(2~7개로 이번 자리수) 끝에 추가 + 이전 자리수(i-(2~7))개의 성냥"으로 만든 최소값
                dp[i] = Math.min(dp[i], dp[i-j] * 10 + dict[j]);
            }
        }

        StringBuilder sb = new StringBuilder();
        StringBuilder msb = new StringBuilder();
        int test = Integer.parseInt(st.nextToken());
        for(int t = 1; t <= test; t++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            // MAX
            msb.setLength(0);
            int maxValue = n;
            while(maxValue > 0){
                if(maxValue == 3){
                    msb.append(7);
                    break;
                }
                maxValue -= 2;
                msb.append(1);
            }
            msb.reverse();
            sb.append(dp[n]).append(" ");
            sb.append(msb).append("\n");

        }
        System.out.println(sb);
//        System.out.println(Arrays.toString(dp));

    }
}