import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] dp = new int[n+1];
        int[] array = new int[n+1];
        Arrays.fill(dp, 0);
        dp[0] = 0;



        st = new StringTokenizer(br.readLine());
        int index = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++){
            array[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
            for(int j = i - 1; j >= 1; j--){
                if(array[j] < array[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            index = Math.max(index, dp[i]);
        }


        int size = index;
        int[] result = new int[index + 1];

        for(int i = n; i > 0 && index > 0; i--){
            if(dp[i] == index){
                result[index] = array[i];
                index--;
            }
        }


        System.out.println(size);
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < result.length; i++){
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
//        System.out.println(Arrays.toString(dp));

    }
}