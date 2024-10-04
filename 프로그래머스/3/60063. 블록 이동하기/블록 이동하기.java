import java.util.*;

class Point{
    int row;
    int col;
    int cnt;
    int direct;
    public Point(int row, int col, int cnt, int direct){
        this.row = row;
        this.col = col;
        this.cnt = cnt;
        this.direct = direct;
    }
}


class Solution {
    static int n;
    static int[][] board;
    static int[][][] visited;
    static int[] dx = {-1 ,0, 1, 0}; // 좌 상 우 하
    static int[] dy = {0, -1, 0, 1};
    static int[] ndx = {1, 0, -1, 0};
    static int[] ndy = {0, 1, 0, -1};
    static void bfs(){
        Queue<Point> queue = new LinkedList();
        Point start = new Point(0, 0, 0, 0);

        queue.offer(start);
        visited[start.row][start.col][start.direct] = 0;
        
        while(!queue.isEmpty()){
            Point current = queue.poll();
            int crow = current.row;
            int ccol = current.col;
            int cdirect = current.direct;
            if(crow == n-1 && ccol == n-1){
                
            }
            else{
                for(int i = 0; i < 4; i++){
                    int row = crow + dy[i];
                    int col = ccol + dx[i];
                    if(row < 0 || row >= n || col < 0 || col >= n || board[row][col] == 1){
                        continue;
                    }

                    int count = current.cnt + 1;

                    if(i != cdirect){
                        // cdirect == 0 (좌측 방향)
                        // i == 0 : 그대로
                        // i == 1 : -> 위
                        // i == 2 : 역전
                        // i == 3 : -> 아래
                        if(Math.abs(i - cdirect) != 2){
                            int nrow = row + ndy[cdirect];
                            int ncol = col + ndx[cdirect];

                            if(nrow < 0 || nrow >= n || ncol < 0 || ncol >= n || board[nrow][ncol] == 1){
                                continue;
                            }
                        }
                        else{
                            count--;
                        }

                    }

                    if(visited[row][col][i] > count){
                        visited[row][col][i] = count;
                        queue.offer(new Point(row, col, count, i));
                    }
                }
                
                
                for(int i = 0; i < 4; i++){
                    if(i == cdirect || Math.abs(i - cdirect) == 2) continue;
                    int row = crow + dy[i];
                    int col = ccol + dx[i];
                    if(row < 0 || row >= n || col < 0 || col >= n || board[row][col] == 1){
                        continue;
                    }
                    
                    int brow = row + ndy[cdirect];
                    int bcol = col + ndx[cdirect];
                    if(brow < 0 || brow >= n || bcol < 0 || bcol >= n || board[brow][bcol] == 1){
                        continue;
                    }

                    int count = current.cnt + 1;

                    if(visited[row][col][cdirect] > count){
                        visited[row][col][cdirect] = count;
                        queue.offer(new Point(row, col, count, cdirect));
                    }
                }
                
                
            }
            
        }
        
    }
    
    public int solution(int[][] board) {
        this.n = board.length;
        this.visited = new int[n][n][4];
        for(int[][] mat : visited){
            for(int[] line : mat){
                Arrays.fill(line, Integer.MAX_VALUE);
            }
        }
        this.board = board;     
        bfs();
        int answer = Integer.MAX_VALUE;
        
        int[][] result = new int[n][n];
        for(int[] line : result){
            Arrays.fill(line, Integer.MAX_VALUE);
        }
        
        for(int[] line : board){
            System.out.println(Arrays.toString(line));
        }
        System.out.println(); 
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < 4; k++){
                    result[i][j] = Math.min(result[i][j], visited[i][j][k]);
                }    
                if(result[i][j] == Integer.MAX_VALUE){
                    result[i][j] = -1;
                }
            }
        }
        for(int[] line : result){
            System.out.println(Arrays.toString(line));
        }
        
        for(int i = 0; i < 4; i++){
            answer = Math.min(answer, visited[n-1][n-1][i]);
        }
        return answer;
        
        

    }
}