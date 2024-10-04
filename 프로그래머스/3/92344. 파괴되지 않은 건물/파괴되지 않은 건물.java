import java.util.*;
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        long[][] matrix = new long[n][m];
        for(int i = 0; i < skill.length; i++){
            int type = skill[i][0] == 2 ? 1 : -1;
            boolean isMaxR = skill[i][3] + 1 >= n;
            boolean isMaxC = skill[i][4] + 1 >= m;
            matrix[skill[i][1]][skill[i][2]] += skill[i][5] * type;
            if(!isMaxR){
                matrix[skill[i][3] + 1][skill[i][2]] += skill[i][5] * (type * -1);
            }
            if(!isMaxC){
                matrix[skill[i][1]][skill[i][4] + 1] += skill[i][5] * (type * -1);
            }
            if(!isMaxR && !isMaxC){
                matrix[skill[i][3] + 1][skill[i][4] + 1] += skill[i][5] * (type);
            }
        }
        
        int answer = board[0][0] + matrix[0][0] > 0 ? 1 : 0;
        for(int i = 1; i < n; i++){
            matrix[i][0] += matrix[i-1][0];
            if(board[i][0] + matrix[i][0] > 0) answer++;
        }
        for(int i = 1; i < m; i++){
            matrix[0][i] += matrix[0][i-1];
            if(board[0][i] + matrix[0][i] > 0) answer++;
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                matrix[i][j] = matrix[i][j] + matrix[i-1][j] + matrix[i][j-1] - matrix[i-1][j-1];
                if(board[i][j] + matrix[i][j] > 0) answer++;
            }
        }
    
        return answer;
    }
}


/**
1 1 1     -1 0 0     0 0 0    -1 0 0
1 2 2      0 0 0     0 1 0     0 1 0
1 2 2      0 0 -1    0 0 1     0 0 0

1 2 3
2 5 8
3 8 13

-4 -5


**/