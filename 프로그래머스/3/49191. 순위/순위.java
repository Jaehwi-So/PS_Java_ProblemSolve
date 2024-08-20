import java.util.*;

class Solution {
    static int INF = 1000;
    public int solution(int n, int[][] results) {
        
        int[][] matrix = new int[n+1][n+1];
        

        for(int[] line : matrix){
            Arrays.fill(line, INF);
        }
        for(int i = 1; i <= n; i++){
            matrix[i][i] = 0;
        }
        for(int i = 0; i < results.length; i++){
            matrix[results[i][0]][results[i][1]] = 1;
        }
        
        
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
        
        for(int[] line : matrix){
            System.out.println(Arrays.toString(line));
        }
        
        int result = n;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(matrix[j][i] == INF && matrix[i][j] == INF){
                    result--;
                    break;
                }
            }
        }


        return result;
    }
}