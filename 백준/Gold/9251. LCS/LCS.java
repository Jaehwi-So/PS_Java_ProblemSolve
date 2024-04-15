import java.util.Scanner;

public class Main {
    static Integer[][] dp;
    static char[] chs;
    static char[] chs2;

    static int lcs(int y, int x){
        if(y < 0 || x < 0){
            return 0;
        }
        if(dp[y][x] == null){
            dp[y][x] = 1;
            if(chs[y] == chs2[x]){
                dp[y][x] = lcs(y-1, x-1) + 1;
            }
            else {
                dp[y][x] = Math.max(lcs(y, x-1), lcs(y-1, x));
            }
        }
        return dp[y][x];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        chs = s.toCharArray();
        s = sc.nextLine();
        chs2 = s.toCharArray();
        dp = new Integer[chs.length][chs2.length];
        int result = lcs(chs.length-1, chs2.length-1);
        System.out.println(result);


//        for(char ch : chs){
//            System.out.print(ch + " ");
//        }
//        System.out.println();
//
//        for(char ch : chs2){
//            System.out.print(ch + " ");
//        }
//
//        System.out.println();
//        for(Integer[] line : dp){
//            for(Integer i : line){
//                System.out.print(i + " ");
//            }
//            System.out.println();
//        }


    }
}
