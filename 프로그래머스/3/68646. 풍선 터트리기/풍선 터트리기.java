import java.util.*;

class Solution {
    
    
    public int solution(int[] a) {
        int n = a.length;
        
//         int[][] dp = new int[n][n];
//         for(int i = 0; i < n; i++){
//             dp[i][i] = a[i];
//         }
        
//         for(int k = 1; k < n; k++){
//             for(int i = 0; i < n - k; i++){
//                 dp[i][i+k] = Math.min(dp[i][i], dp[i+1][i+k]);
//             }
//         }
        
        
        int answer = 0;
        
        boolean[] left = new boolean[n]; // 왼쪽보다 큼
        boolean[] right = new boolean[n]; // 오른쪽보다 큼
        
        int l = a[0];
        for(int i = 1; i < n; i++){
            if(a[i] > l) left[i] = true;
            else{
                l = a[i];
            }
        }
        
        int r = a[n-1];
        for(int i = n-2; i >= 0; i--){
            if(a[i] > r) right[i] = true;
            else{
                r = a[i];
            }
        }
        
        for(int i = 0; i < n; i++){
            if(!left[i] || !right[i]){
                answer++;
            }
        }
        
        return answer;
    }
}
/**
내 양쪽에 있는 것들의 최소값들 중 한곳보다만 작으면 됨
dp[0][1] = dp[0][0] dp[1][1]
dp[1][2] = dp[1][1] dp[2][2

dp[0][2] = dp[0][0] dp[1][2]

100만 * 25만

nlogn   
2500000000000
]
**/