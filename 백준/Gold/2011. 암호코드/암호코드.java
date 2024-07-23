import java.util.Scanner;

public class Main {
    static boolean isMulti(char first, char second){
        int f = Character.getNumericValue(first) * 10;
        int s = Character.getNumericValue(second);
        int result = f + s;
        if(first > 0 && result <= 26){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] chs = s.toCharArray();
        int[][] dp = new int[chs.length][2];

        dp[0][0] = 1;
        dp[0][1] = 0;
        if(chs[0] == '0') {
            System.out.println(0);
            return;
        }


        for(int i = 1; i < chs.length; i++){
            if(isMulti(chs[i-1], chs[i])){
                dp[i][1] = dp[i-1][0];
                if(chs[i] == '0'){
                    dp[i][0] = 0;
                }
                else{
                    dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % 1000000;
                }
            }
            else{
                if(chs[i] == '0'){
                    System.out.println(0);
                    return;
                }
                dp[i][1] = 0;
                dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % 1000000;
            }

        }

        int result = (dp[chs.length - 1][0] + dp[chs.length - 1][1]) % 1000000;
        System.out.println(result);

//        for(int[] line : dp){
//            System.out.println(Arrays.toString(line));
//        }

    }
}

