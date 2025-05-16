import java.util.*;

class Solution {
    static int n;
    static int m;
    static int[][] matrix;
    static int[][] target;
    
    static int result = Integer.MAX_VALUE;
    
    static boolean match(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] != target[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    
    static void dfs(int count, int step){
        if(match()){
            result = Math.min(result, count);
        }
        
        else if(step < n){
            int row = step;
            for(int i = 0; i < m; i++){
                matrix[row][i] = matrix[row][i] == 0 ? 1 : 0;
            }
            dfs(count + 1, step + 1);
            for(int i = 0; i < m; i++){
                matrix[row][i] = matrix[row][i] == 0 ? 1 : 0;
            }
            dfs(count, step + 1);
        }
        
        
        else if(step >= n && step < n + m){
            int col = step - n;
            for(int i = 0; i < n; i++){
                matrix[i][col] = matrix[i][col] == 0 ? 1 : 0;
            }
            dfs(count + 1, step + 1);
            for(int i = 0; i < n; i++){
                matrix[i][col] = matrix[i][col] == 0 ? 1 : 0;
            }
            dfs(count, step + 1);
        }
    }
    
    public int solution(int[][] beginning, int[][] target) {
        this.matrix = beginning;
        this.target = target;
        n = beginning.length;
        m = beginning[0].length;
        
        dfs(0, 0);
        
        int answer = result == Integer.MAX_VALUE ? -1 : result;
        return answer;
    }
}

// 5 5 4 4 3 3 2 2 1 1
// 25 24 23 22 21