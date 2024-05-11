import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int binarySearch(int number, int size, int[] array){
        int left = 0;
        int right = size;
        while(left < right){
            int mid = (left + right) / 2;
            if(array[mid] == number){
                return mid - 1;
            }
            else if(array[mid] > number){
                right = mid;
            }
            else{
                left = mid + 1;
            }
        }
        return left - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        st = new StringTokenizer(br.readLine());
        int max = 0;
        for(int i = 1; i <= n; i++){
            int number = Integer.parseInt(st.nextToken());
            int beforeIdx = binarySearch(number, max + 1, dp);
            int index = beforeIdx + 1;
            dp[index] = Math.min(dp[index], number);
            max = Math.max(index, max);
//            System.out.println(Arrays.toString(dp));
        }


        System.out.println(max);

    }
}