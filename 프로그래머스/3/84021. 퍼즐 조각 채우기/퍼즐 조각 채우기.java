import java.util.*;

class Solution {
    static int n;
    static int[][] matrix;
    static int[][] board;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static boolean[][] visited;
    static int result = 0;
    
    static void rotate(){
        int[][] temp = new int[n][n];
        int index = 0;
        for(int j = 0; j < n; j++){
            for(int i = n - 1; i >= 0; i--){
                int row = index / n;
                int col = index % n;
                temp[row][col] = matrix[i][j];
                index++;
            }
        }
        matrix = temp;
    }
    
    static boolean find(int brow, int bcol, int color){
        visited = new boolean[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0 && !visited[i][j]){
                    boolean success = bfs(i, j, brow, bcol, color);
                    if(success) return true;
                }
            }
        }
        return false;
    }
    
    static boolean bfs(int mrow, int mcol, int brow, int bcol, int color){
        Queue<int[]> queue = new LinkedList();
        visited[mrow][mcol] = true;
        queue.offer(new int[]{mrow, mcol, brow, bcol});
        
        boolean success = true;
        Stack<int[]> stack = new Stack();
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            stack.push(current);
            for(int i = 0; i < 4; i++){
                int n_mrow = current[0] + dy[i];
                int n_mcol = current[1] + dx[i];
                int n_brow = current[2] + dy[i];
                int n_bcol = current[3] + dx[i];
                if(n_mrow < 0 || n_mcol < 0 || n_mrow >= n || n_mcol >= n || matrix[n_mrow][n_mcol] != 0){
                    if(!(n_brow < 0 || n_bcol < 0 || n_brow >= n || n_bcol >= n || board[n_brow][n_bcol] != color)){
                        success = false;
                    }
                    continue;
                } 
                if(visited[n_mrow][n_mcol]) continue;
                if(matrix[n_mrow][n_mcol] == 0){
                    if((n_brow < 0 || n_bcol < 0 || n_brow >= n || n_bcol >= n || board[n_brow][n_bcol] != color)){
                        success = false;
                    }
                }
                visited[n_mrow][n_mcol] = true;
                queue.offer(new int[]{n_mrow, n_mcol, n_brow, n_bcol});
            }
        }
        if(success){
            while(!stack.isEmpty()){
                int[] current = stack.pop();
                matrix[current[0]][current[1]] = color;
                result++;
            }
        }
        
        return success;
    
    }
    
    
    static void coloring(int srow, int scol, int color){
        Queue<int[]> queue = new LinkedList();
        board[srow][scol] = color;
        queue.offer(new int[]{srow, scol});
        
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || col < 0 || row >= n || col >= n) continue;
                if(board[row][col] != 1) continue;
                board[row][col] = color;
                queue.offer(new int[]{row, col});
            }
        }
        
    }
    
    public int solution(int[][] game_board, int[][] table) {
        this.n = Math.max(game_board.length, game_board[0].length);
        this.matrix = new int[n][n];
        this.board = new int[n][n];
        
        for(int i = 0; i < game_board.length; i++){
            for(int j = 0; j < game_board[i].length; j++){
                this.matrix[i][j] = game_board[i][j]; 
                this.board[i][j] = table[i][j];      
            }
        }
        

        int cidx = 2;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 1){
                    coloring(i, j, cidx);
                    cidx++;
                } 
            }
        }
        
        boolean[] success = new boolean[cidx];
        
        for(int r = 0; r < 4; r++){
            
            boolean[] colors = new boolean[cidx];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(board[i][j] > 1 && !colors[board[i][j]] && !success[board[i][j]]){
                        colors[board[i][j]] = true;
                        if(find(i, j, board[i][j])) success[board[i][j]] = true;
                    }
                }
            }
            rotate();
                        
        }
        
        
        
//         for(int[] line : board){
//             System.out.println(Arrays.toString(line));
//         }
//         System.out.println();
        
//         for(int[] line : matrix){
//             System.out.println(Arrays.toString(line));
//         }
//         System.out.println(Arrays.toString(success));
        
        
        int answer = result;
        return answer;
    }
}