import java.util.*;


class Solution {
    static int n;
    static int m;
    static int[] cols;
    static int[][] dp;
    static int[][] co;
    static final int MOD = (int)Math.pow(10, 7) + 19;
    
    static int comb(int n, int k){
        if(co[n][k] == -1){
            if(k == 0 || k == n) co[n][k] = 1;
            else co[n][k] = (comb(n-1, k-1) + comb(n-1, k)) % MOD;
        }
        return co[n][k];
    }
    
    static int operate(int col, int row){ //col번째에서 짝수의 개수가 row개가 존재하는 배치의 경우의 수
        if(dp[col][row] == -1){
            
            int amount = cols[col]; //총 변경 개수
            dp[col][row] = 0;
            

            for (int even = 0; even <= n; even++) {   // 이전 짝수 행의 개수
                int odd = n - even; // 이전 홀수 행의 개수
                
                for (int i = 0; i <= amount; i++) {   // 변경 계수 i
                    int e = i; //변경할 짝수행의 개수(-> 홀수)
                    int o = amount - i; //변경할 홀수행의 개수(-> 짝수)
                    if (e > even || o > odd) continue;
                    
                    if (o - e == row - even) {
                        long pick = (1L * comb(odd, o) * comb(even, e)) % MOD;
                        long res = (1L * operate(col - 1, even) * pick) % MOD;
                        dp[col][row] = (int)(dp[col][row] + res) % MOD;
                    }
                }
                
            }
            
            
        }
        return dp[col][row];
    }
    
    public int solution(int[][] a) {
        this.n = a.length;
        this.m = a[0].length;
        this.cols = new int[m];
        this.dp = new int[m][n+1];
        this.co = new int[301][301];
        
        for(int[] line : co){
            Arrays.fill(line, -1);
        }
        
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(a[i][j] == 1) cols[j]++;
            }
        }
        
        for(int[] line : dp) Arrays.fill(line, -1);
        
        //짝수의 개수가 맨 처음 "행의개수-1의개수"개가 존재하는 배치의 경우의 수
        Arrays.fill(dp[0], 0);
        int zeroCnt = n-cols[0];
        dp[0][zeroCnt] = comb(n, zeroCnt); 
        
        int answer = operate(m-1, n);
        return answer;
    }
}



/**

[
[0,1,0],
[1,1,1],
[1,1,0],
[0,1,1]
]

dp[0] = 6
dp[1] = 6
dp[2] = 6


[
[1,0,0,1,1],
[0,0,0,0,0],
[1,1,0,0,0],
[0,0,0,0,1]
]

dp[0][2] = 6
dp[1][1] = 12
dp[1][3] = 12
dp[2][1] = 12
dp[2][3] = 12
dp[3][2] = 36 + 36
dp[3][4] = 12
dp[4][4] = 2개를 추가하거나 빼야 함.

**/