import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] values = new int[n][n];
        int[][] dp = new int[n][n]; //i~j 사이의 최소값

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            values[i][0] = Integer.parseInt(st.nextToken());
            values[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int l = 1; l < n; l++){ // 간격
            for(int start = 0; start < n - l; start++){ //시작점
                int end = start + l; // 끝점
                dp[start][end] = Integer.MAX_VALUE;
                for(int pivot = start; pivot < end; pivot++){ //시작점~끝점 중간지점

                    int partCalc = dp[start][pivot] + dp[pivot+1][end]; // 앞쪽 부분행렬 연산횟수+ 뒤쪽 부분행렬 연산횟수
                    int operate = values[start][0] * values[pivot][1] * values[end][1]; // 부분행렬 합칠 때의 연산횟수
                    int value = partCalc + operate;
                    dp[start][end] = Math.min(dp[start][end], value);

//                    System.out.println(start + "~" + pivot + " + " + (pivot+1) + "~" + (end)  + " : " + dp[start][end]);
//                    System.out.println(dp[start][pivot] + " + " + dp[pivot+1][end] + " + (" + values[start][0] + " * " +  values[pivot][1] + " * " +  values[end][1] + ")");


                }


            }

        }

        System.out.println(dp[0][n-1]);

    }

}
