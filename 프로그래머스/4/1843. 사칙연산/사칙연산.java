import java.util.*;

class Solution {
    static int INF = 100000000;
    public int solution(String arr[]) {
        int length = (arr.length / 2) + 1;
        int[][] numbers = new int[2][length];
        int index = 0;
        
        for(int i = 0; i < arr.length; i++){
            if(i % 2 == 0){
                numbers[0][index] = Integer.parseInt(arr[i]);
                if(i == 0) numbers[1][index] = 1;
                index++;
            }
            else{
                numbers[1][index] = arr[i].equals("-") ? -1 : 1;
                
            }
        }
        

        int[][] max_dp = new int[length][length];
        int[][] min_dp = new int[length][length];
        
        for(int i = 0; i < length; i++){
            Arrays.fill(max_dp[i], INF * -1);
            Arrays.fill(min_dp[i], INF);
            max_dp[i][i] = numbers[0][i];
            min_dp[i][i] = numbers[0][i];
        }
        

        
        for(int l = 1; l < length; l++){    //구간의 길이
            for(int i = 0; i < length - l; i++){    //구간 시작
                int j = i + l;  //구간 끝
                for(int pivot = i; pivot < j; pivot++){ //
                    // -
                    if(numbers[1][pivot+1] == -1){
                        max_dp[i][j] = Math.max(max_dp[i][pivot] - min_dp[pivot+1][j], max_dp[i][j]);
                        min_dp[i][j] = Math.min(min_dp[i][pivot] - max_dp[pivot+1][j], min_dp[i][j]);
                    }
                    // +
                    else{
                        max_dp[i][j] = Math.max(max_dp[i][pivot] + max_dp[pivot+1][j], max_dp[i][j]);
                        min_dp[i][j] = Math.min(min_dp[i][pivot] + min_dp[pivot+1][j], min_dp[i][j]);
                    }
                   
                }
            }
        }
        
//         for(int[] line : max_dp){
//             System.out.println(Arrays.toString(line));
//         }
        
//         for(int[] line : min_dp){
//             System.out.println(Arrays.toString(line));
//         }
        
        
        int answer = max_dp[0][length-1];
        return answer;
    }
}
