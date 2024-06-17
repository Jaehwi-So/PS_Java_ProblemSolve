import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static String compare(String s1, String s2){
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        if(chs1.length > chs2.length){
            return s1;
        }
        else if(chs1.length < chs2.length){
            return s2;
        }
        else{
            for(int i = 0; i < chs1.length; i++){
                if(Character.getNumericValue(chs1[i]) > Character.getNumericValue(chs2[i])){
                    return s1;
                }
                else if(Character.getNumericValue(chs2[i]) > Character.getNumericValue(chs1[i])){
                    return s2;
                }
            }
        }
        return s1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] numbers = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        String[] dp = new String[m+1];
        Arrays.fill(dp, "0");
        for(int i = 1; i < n; i++){
            if(numbers[i] <= m){
                dp[numbers[i]] = Integer.toString(i);
            }
        }


        for(int i = 1; i <= m; i++){
            dp[i] = dp[i - 1];
            for(int j = 0; j < n; j++){
                if(i >= numbers[j]){
                    if(!dp[i - numbers[j]].equals("0")){
                        dp[i] = compare(dp[i], (dp[i - numbers[j]]) + j);
                    }
                    else{
                        dp[i] = compare(dp[i], Integer.toString(j));
                    }
                }
            }
        }

//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[m]);


    }
}
