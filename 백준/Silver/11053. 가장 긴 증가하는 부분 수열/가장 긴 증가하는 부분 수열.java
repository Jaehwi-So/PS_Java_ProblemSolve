import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    static Integer[] numbers;
    static Integer[] dp;
    static Integer[] dist;



    // 해당 원소가 오른쪽에 삽입되기 전 가질 수 있는 이전 수열의 LIS 최대 길이를 반환
    static int binarySearch(int value, int left, int right){
        if(left == right){
            return left - 1;    //탐색 성공 위치의 이전 값이 이전 최대 LIS
        }
        else{
            int mid = (left + right) / 2;
            if(dp[mid] == value){
                return mid - 1;  //탐색 성공 위치의 이전 값이 이전 최대 LIS
            }
            else if(dp[mid] > value){
                return binarySearch(value, left, mid);
            }
            else{
                return binarySearch(value, mid + 1, right);
            }
        }
    }

    // 해당 원소가 오른쪽에 삽입되기 전 가질 수 있는 기존 LIS의 길이를 반환. 탐색 범위는 1부터 지금까지 LIS 중 최대 길이까지.
    static int binarySearch(int value, int size){
        return binarySearch(value, 1, size + 1);
    }



    static int lis(int n){
        int max = 0;
        for(int i = 1; i <= n; i++){
            int num = numbers[i];
            int d = binarySearch(num, max);
            int index = d + 1;
            dist[i] = index;
            dp[index] = Math.min(dp[index], num);
            max = Math.max(max, index);
        }
        return max;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        numbers = new Integer[n + 1];
        dist = new Integer[n + 1];
        dp = new Integer[n + 1];


        dist[0] = 0;
        numbers[0] = 0;
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(lis(n));

    }
}