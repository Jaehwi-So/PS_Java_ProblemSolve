import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    static Integer[] numbers;
    static Integer[] dp;
    static Integer[] dist;


    static int binarySearch(int value, int left, int right){
        if(left == right){
            return left - 1;
        }
        else{
            int mid = (left + right) / 2;
            if(dist[mid] == value){
                return mid - 1;
            }
            else if(dist[mid] > value){
                return binarySearch(value, left, mid);
            }
            else{
                return binarySearch(value, mid + 1, right);
            }
        }
    }

    static int binarySearch(int value, int size){
        return binarySearch(value, 1, size + 1);
    }



    static int calc(int n){
        int max = 0;
        for(int i = 1; i <= n; i++){
            int num = numbers[i];
            int d = binarySearch(num, max);
            int index = d + 1;
            dp[i] = index;
            dist[index] = Math.min(dist[index], num);
            max = Math.max(max, index);
        }
        return max;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        numbers = new Integer[n + 1];
        dp = new Integer[n + 1];
        dist = new Integer[n + 1];



        dp[0] = 0;
        numbers[0] = 0;
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }


        System.out.println(calc(n));
//        System.out.println(Arrays.toString(dp));
//        System.out.println(Arrays.toString(dist));

    }
}