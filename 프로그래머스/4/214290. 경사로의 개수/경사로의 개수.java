import java.util.*;

class Solution {
    static int[][] matrix;
    static int[][][] trace;
    static int n;
    static int m;
    static int len;
    static int[] d; //5 -> 0 1 2 3 4
    static int k; //1000000000  10억
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static final int MOD = (int)Math.pow(10, 9) + 7;
    static int[][][][] dp;
    static final int po = 2;
    static StringBuilder sb = new StringBuilder();
    
    static int calcLog(int x){
        int res = (int)(Math.log(x) / Math.log(po));
        return res;
    }
    
    static int operate(int sidx, int eidx, int step){

        int log = calcLog(step);
        int seq = (int)Math.pow(po, log);

        if(seq == step && dp[sidx][eidx][log][0] != -1){
            return dp[sidx][eidx][log][0];
        }
        else if(seq != step && dp[sidx][eidx][log][1] != -1){
            return dp[sidx][eidx][log][1];
        }
        
        // if(sb.length() < 100000) sb.append(step).append(" ");

        long result = 0L;
        for(int i = 0; i < len; i++){
            long path = 0;
            if(seq == step){
                path = (1L * operate(sidx, i, step / 2) * operate(i, eidx, step / 2)) % MOD;
            }
            else{
                path = (1L * operate(sidx, i, seq) * operate(i, eidx, step - (seq))) % MOD;
            }
            result = (result + path) % MOD;
        }

        if(seq == step) dp[sidx][eidx][log][0] = (int)result;
        else dp[sidx][eidx][log][1] = (int)result;

        return (int)result;
    }
    
    
    static int getTrace(int sidx, int eidx, int sequence){
        if(sequence == d.length){
            if(sidx == eidx){
                return 1;
            } 
            else return 0;
        } 
        
        if(trace[sidx][eidx][sequence] == -1){
            int result = 0;
            int srow = sidx / m;
            int scol = sidx % m;
            for(int i = 0; i < 4; i++){
                int row = srow + dy[i];
                int col = scol + dx[i];
                if(row < 0 || col < 0 || row >= n || col >= m) continue;
                int nidx = (row * m) + col;
                if(matrix[row][col] - matrix[srow][scol] == d[sequence]){
                    result += getTrace(nidx, eidx, sequence + 1);
                    result %= MOD;
                }
            }
            trace[sidx][eidx][sequence] = result;
        }
        return trace[sidx][eidx][sequence];
    }
    
    public int solution(int[][] grid, int[] d, int k) {
        this.matrix = grid;
        this.n = matrix.length;
        this.m = matrix[0].length;
        this.len = n * m;
        this.d = d;
        this.k = k;
        this.trace = new int[len][len][d.length];
        
        int log = calcLog(k);
        this.dp = new int[len][len][log+1][2];
        // int rev = (int)Math.pow(2, log);
        // System.out.println(log + " " + rev);
        
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                Arrays.fill(trace[i][j], -1);
                for(int l = 0; l <= log; l++){
                     Arrays.fill(dp[i][j][l], -1);
                }
            }            
        }
        

        
        
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                dp[i][j][0][0] = getTrace(i, j, 0);
            }
        }
        
        int answer = 0;
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                answer = (answer + operate(i, j, k)) % MOD; 
            }
        }
        
        return answer;
    }
}