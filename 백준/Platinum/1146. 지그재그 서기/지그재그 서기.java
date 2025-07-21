import java.util.*;

public class Main {
    static int n;
    static int[][][] dp; //dp[a][b][c] : a개의 숫자 중 & b번째로 큰 수가 첫자리에 들어갈 때 & 다음 수가 증가/감소하는 경우
    static final int MOD = 1000000;

    static int operate(int len, int seq, int d){
        if(dp[len][seq][d] == -1){
            dp[len][seq][d] = 0;
            if(len > 2){
                if(d == 0){  //다음 수 감소
                    for(int i = 1; i < seq; i++){ //len개중에 seq번보다 작은 범위 -> len-1개중에 1번째~seq-1번째로 큰수
                        dp[len][seq][d] += operate(len - 1, i, 1);
                        dp[len][seq][d] %= MOD;
                    }
                }
                else{ // 다음 수 증가
                    for(int i = seq; i < len; i++){ //len개중에 seq번보다 작은 범위 -> len-1개중에 seq번째~len-1번째로 큰수
                        dp[len][seq][d] += operate(len - 1, i, 0); //다다음 수 감소
                        dp[len][seq][d] %= MOD;
                    }
                }
            }
        }
        return dp[len][seq][d];
    }


    // 1 3 2
    // 2 1 3
    // 2 3 1
    // 3 1 2

    // operate(2, 1, 1)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dp = new int[n+1][n+1][2];

        for(int[][] mat : dp){
            for(int[] line : mat){
                Arrays.fill(line, -1);
            }
        }

        if(n == 1){ //1인 경우 증가와 감소가 무의미 = 1
            System.out.println(1);
            return;
        }

        //2개까지의 범위 직접 지정
        for(int i = 1; i <= 2; i++){
            for(int[] line : dp[i]){
                Arrays.fill(line, 0);
            }
        }
        dp[1][1][0] = 1;
        dp[1][1][1] = 1;
        dp[2][1][1] = 1;
        dp[2][2][0] = 1;

        int result = 0;

        for(int i = 1; i <= n; i++){
            for(int j = 0; j < 2; j++){
                result += operate(n, i, j);
                result %= MOD;
            }
        }


        System.out.println(result);
    }
}