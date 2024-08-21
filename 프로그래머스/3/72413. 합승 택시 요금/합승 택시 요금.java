import java.util.*;

class Solution {
    static int INF = 100000000;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] matrix = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                matrix[i][j] = INF;
            }
            matrix[i][i] = 0;
        }
        for(int[] fare : fares){
            matrix[fare[0]][fare[1]] = fare[2];
            matrix[fare[1]][fare[0]] = fare[2];
        }
        
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
        
        int min = INF;
        
        for(int i = 1; i <= n; i++){
            min = Math.min(min, matrix[s][i] + matrix[i][a] + matrix[i][b]);
        }
        
        // for(int[] line : matrix){
        //     System.out.println(Arrays.toString(line));
        // }
        

        int answer = min;
        return answer;
    }
}