import java.util.*;

public class Main {
    static int n;
    static int k;

    static long[][][][] dp;


    static long operate(int index, int lb, int fb, int available){
        if(lb > n || fb > n || lb < 0 || fb < 0){
            return 0;
        }
        if(index == n + 1) {
            if(available == 0) return 1;
            else return 0;

        }

        if(dp[index][lb][fb][available] == -1){
            long result = 0;

            if(lb == index - 2){
                if(fb != index - 1) result += operate(index + 1, index - 1, lb, available);
                result += operate(index + 1, index , lb, available);
                result += operate(index + 1, index + 1, lb, available);
            }
            else if(lb == index - 1){
                result += operate(index + 1, index, lb, available);
                result += operate(index + 1, index + 1, lb, available);
            }
            else if(lb == index){
                if(fb != index - 1) result += operate(index + 1, index - 1, lb, available);
                result += operate(index + 1, index + 1, lb, available);
            }

            if(available == 1){
                result += operate(index + 1, index - 1, lb, 0);
            }


            dp[index][lb][fb][available] = result;
//            System.out.println(dp[index][lb][fb][available]);
        }

        return dp[index][lb][fb][available];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        k = sc.nextInt();

//        n = k - 1;
        n = k - 1;
        dp = new long[n+1][n+1][n+1][2];
        for(long[][][] m : dp){
            for(long[][] mat : m){
                for(long[] line : mat){
                    Arrays.fill(line, -1);
                }
            }
        }

        long a1 = operate(1, 0, 0,1);
        long a2 = operate(1, 0, 0,0);

//        System.out.println(a1 + " " + a2);


//        // (6 ~ 8) 6 7 8  6 ~ 8
        n = len - k;
        dp = new long[n+1][n+1][n+1][2];
        for(long[][][] m : dp){
            for(long[][] mat : m){
                for(long[] line : mat){
                    Arrays.fill(line, -1);
                }
            }
        }

        long b1 = operate(1, 0, 0,1);
        long b2 = operate(1, 0, 0,0);

//        System.out.println(b1 + " " + b2);
        long result = ((a1 * b2) + (b1 * a2) + (a2 * b2));
        System.out.println(result);


    }
}