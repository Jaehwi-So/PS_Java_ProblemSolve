import java.util.*;

public class Main {
    static int n;
    static int l;
    static int r;
    static long[][][] dp; //빌딩 갯수, 왼쪽 보이는 건물수, 오른쪽 보이는 건물수
    static final int MOD = 1000000007;

    // dp[5][3][2] =

    // 1 1 1 1 1 1 1 1 1 1
    static long operate(int number, int left, int right){  // 10, 3, 2
        if(dp[number][left][right] == -1){
            dp[number][left][right] = 0;
            if(number == 1){
                if(left == 1 && right == 1) dp[number][left][right] = 1;
            }
            else{
                // number-1 높이의 빌딩을 추가

                // 가장 왼쪽에 추가
                if(left > 1){
                    dp[number][left][right] += operate(number - 1, left - 1, right);
                    dp[number][left][right] %= MOD;
                }

                // 가장 오른쪽에 추가
                if(right > 1){
                    dp[number][left][right] += operate(number - 1, left, right - 1);
                    dp[number][left][right] %= MOD;
                }

                // 사이에 추가
                if(number > 2){
                    dp[number][left][right] += (operate(number - 1, left, right) * (number - 2));
                    dp[number][left][right] %= MOD;
                }
            }
        }
        return dp[number][left][right];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        l = sc.nextInt();
        r = sc.nextInt();

        dp = new long[n+1][l+1][r+1];
        for(long[][] mat : dp){
            for(long[] line : mat){
                Arrays.fill(line, -1);
            }
        }

        System.out.println(operate(n, l, r));


    }
}