import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Integer[] dp;
    static Integer calc(int k){
        if(dp[k] == null){
            if(k == 1){
                dp[k] = 1;
            }
            else if(k == 2){
                dp[k] = 2;
            }
            else if(k == 3){
                dp[k] = 4;
            }
            else{
                dp[k] = calc(k-1) + calc(k-2) + calc(k-3);
            }
        }
        return dp[k];

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        dp = new Integer[11];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            System.out.println(calc(Integer.parseInt(st.nextToken())));
        }
    }
}
