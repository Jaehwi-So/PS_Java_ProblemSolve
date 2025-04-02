import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] dp;
    static int[] factorial;

    // 6! = (3!)!
    static int operate(int number){
        if(dp[number] == -1){
            dp[number] = number;
            if(factorial[number] != -1){
                dp[number] = Math.min(dp[number], operate(factorial[number]));
            }
            for(int i = 1; i <= number / 2; i++){
                dp[number] = Math.min(dp[number], operate(i) + operate(number - i));
            }
            for(int i = 2; i <= number / 2; i++){
                if(number % i == 0){
                    dp[number] = Math.min(dp[number], operate(i) + operate(number / i));
                }
            }
        }
        return dp[number];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        dp = new int[n+1];
        factorial = new int[n+1];
        Arrays.fill(dp, -1);
        Arrays.fill(factorial, -1);

        int number = 1;
        int multiply = 1;
        while(true){
            number *= multiply;
            if(number > n) break;
            factorial[number] = multiply;
            multiply++;
        }

        System.out.println(operate(n));
//        System.out.println(Arrays.toString(dp));

    }
}