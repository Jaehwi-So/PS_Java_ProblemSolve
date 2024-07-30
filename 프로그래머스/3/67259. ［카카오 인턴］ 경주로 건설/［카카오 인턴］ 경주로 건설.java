import java.util.*;

class Node{
    int row;
    int col;
    int direct;
    int cost;
    public Node(int row, int col, int direct, int cost){
        this.row = row;
        this.col = col;
        this.direct = direct;
        this.cost = cost;
    }
}
class Solution {
    static int n;
    static int m;
    static int[][] matrix;
    static int[][][] dp;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static final int MAX = 2000000000;

    
    static int bfs(){

        Queue<Node> queue = new LinkedList();
        
        for(int i = 0; i < 4; i++){
            Node start = new Node(0, 0, i, 0);
            queue.offer(start);
        }
        
        
        while(!queue.isEmpty()){
            Node current = queue.poll();
            int crow = current.row;
            int ccol = current.col;
            int ccost = current.cost;
            int cdirect = current.direct;            
            if(ccost < dp[cdirect][crow][ccol]){
                dp[cdirect][crow][ccol] = ccost;
                if(crow != n-1 || ccol != m-1){
                    for(int i = 0; i < 4; i++){
                        int r = crow + dy[i];
                        int c = ccol + dx[i];
                        if(r >= 0 && r < n && c >= 0 && c < m && matrix[r][c] != 1){
                            if((cdirect + i) % 2 == 0){
                                queue.offer(new Node(r, c, i, ccost + 100));
                            }
                            else {
                                queue.offer(new Node(r, c, i, ccost + 600));
                            }
                        }
                    }
                }              
            }         
        }
        
        int result = MAX;
        for(int i = 0; i < 4; i++){
            result = Math.min(dp[i][n-1][m-1], result);
        }
        return result;
    }
    
    
    public int solution(int[][] board) {
    
        n = board.length;
        m = board.length;
        matrix = board;
        dp = new int[4][n][m];
        
        for(int[][] mat : dp){
            for(int[] line : mat){
                Arrays.fill(line, MAX);
            }
        }
        
        
        int result = bfs();

        return result;
    }
}



/**

static int dfs(int row, int col, int direct){
    if(dp[direct][row][col] == -1){
        dp[direct][row][col] = MAX;
        int r = row + dy[direct];
        int c = col + dx[direct];
        if(r >= 0 && r < n && c >= 0 && c < m){
            if(matrix[r][c] != 1){
                for(int i = 0; i < 4; i++){
                    if((direct + i) % 2 == 0){
                        dp[direct][row][col] = Math.min(dp[direct][row][col], dfs(r, c, i) + 100);
                    }
                    else {
                        dp[direct][row][col] = Math.min(dp[direct][row][col], dfs(r, c, i) + 600);
                    }
                }
            }
        }
    }
    return dp[direct][row][col];
}
    
**/