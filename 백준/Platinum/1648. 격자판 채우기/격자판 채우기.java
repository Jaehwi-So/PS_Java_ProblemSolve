import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] dp;

    static int calc(int index, int bit){
        if((index / m) == n) return 1;
        if(dp[index][bit] == -1){
            if(((bit & 1) == 0)){
                // 2x1
                int horizontal = 0;
                if((bit & (1 << 1)) == 0 && (index % m) < m - 1){
                    horizontal = calc(index + 2, (bit >> 2));
                }
                // 1x2
                int vertical = 0;
                if((index / m) < n - 1){
                    vertical = calc(index + 1, (bit >> 1) | (1 << m - 1));
                }

                dp[index][bit] = (horizontal + vertical) % 9901;
//                System.out.println(index + " : " + horizontal + " " + vertical + " " + bit);
            }
            else{
                dp[index][bit] = calc(index + 1, (bit >> 1));
            }

        }
        return dp[index][bit];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        dp = new int[n*m][(int)Math.pow(2, m)];
        for(int[] line : dp){
            Arrays.fill(line, -1);
        }

        System.out.println(calc(0, 0));

    }
}