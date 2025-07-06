import java.util.*;

public class Main {
    static final long MOD = 1000000007L;
    static long[][][] dp;
    static boolean[] visited;

    static long[] calc(long[] mat1, long[] mat2){
        long[] result = new long[4];
        result[0] = (((mat1[0] * mat2[0]) + MOD) % MOD) + (((mat1[1] * mat2[2]) + MOD) % MOD);
        result[1] = (((mat1[0] * mat2[1]) + MOD) % MOD) + (((mat1[1] * mat2[3]) + MOD) % MOD);
        result[2] = (((mat1[2] * mat2[0]) + MOD) % MOD) + (((mat1[3] * mat2[2]) + MOD) % MOD);
        result[3] = (((mat1[2] * mat2[1]) + MOD) % MOD) + (((mat1[3] * mat2[3]) + MOD) % MOD);
        result[0] %= MOD;
        result[1] %= MOD;
        result[2] %= MOD;
        result[3] %= MOD;
        return result;
    }
    static long[] operate(long n){
        if(n == 0){
            return new long[]{1, 0, 0, 1};
        }
        if(n == 1){
            return new long[]{4, -1, 1, 0};
        }

        int len = Long.toString(n).length() - 1;
        if(len == 0){
            return dp[0][(int)n];
        }
        else{
            long k = (long)Math.pow(10, len);
            long current = n / k;
            long last = n % k;
            long[] mat1 = dp[len][(int)current];
            long[] mat2 = operate(last);
            long[] result = calc(mat1, mat2);
//            System.out.println(n + " len : " + len);
//            System.out.println(current + " , " + last);
//            System.out.println(Arrays.toString(mat1));
//            System.out.println(Arrays.toString(mat2));
            return result;
        }





/**
 1 -1   1 -1
 1 -1   1 -1
 */

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        visited = new boolean[1000000];
        dp = new long[20][10][4];
        if(n % 2 == 1) System.out.println(0);
        else if(n == 2) System.out.println(3);
        else{
            dp[0][1] = new long[]{4, -1, 1, 0};
            for(int i = 0; i < 20; i++){
                for(int j = 2; j <= 9; j++){
                    dp[i][j] = calc(dp[i][j-1], dp[i][1]);
//                    System.out.println(i + " : " + j + " = " + Arrays.toString(dp[i][j]));

                }
                if(i + 1 < 20){
                    dp[i+1][1] = calc(dp[i][9], dp[i][1]);
//                    System.out.println(i+1 + " : " + 1 + " = " + Arrays.toString(dp[i+1][1]));
                }
            }

            long[] matrix = operate(n/2 - 1);
            long result = ((matrix[0] * 3) + matrix[1] + MOD) % MOD;
            System.out.println(result);
        }

        /**
         4 -1
         1  0 행렬을 N/2번만큼 행렬곱셈.
         1000000000000000000
         */

        /**
         a(x+2)  =  [4 -1] [ a(x)   ]    [56, -15] [3]
         a(x)       [1  0] [ a(x-2) ]


         */

    }
}