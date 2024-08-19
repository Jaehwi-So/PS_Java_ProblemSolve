import java.util.*;

class Point{
    int row;
    int col;
    int count;
    public Point(int row, int col, int count){
        this.row = row;
        this.col = col;
        this.count = count;
    }
}

class Solution {
    static int n;
    static int m;
    static char[][] matrix;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    
    static int bfs(Point start){
        Queue<Point> queue = new LinkedList();
        queue.offer(start);
        visited[start.row][start.col] = true;
        
        while(!queue.isEmpty()){
            Point current = queue.poll();
            if(matrix[current.row][current.col] == 'G'){
                return current.count;
            }
            for(int i = 0; i < 4; i++){
                Point next = new Point(current.row, current.col, current.count + 1);
                while(next.row >= 0 && next.col >= 0 && next.row < n && next.col < m && matrix[next.row][next.col] != 'D'){
                    next.row += dy[i];
                    next.col += dx[i];
                }
                next.row -= dy[i];
                next.col -= dx[i];
                if(!visited[next.row][next.col]){
                    queue.offer(next);
                    visited[next.row][next.col] = true;
                }
            }            
        }
        
        return -1;
    }
    public int solution(String[] board) {
        n = board.length;
        m = board[0].length();
        matrix = new char[n][m];
        Point start = new Point(0, 0, 0);
        for(int i = 0; i < n; i++){
            matrix[i] = board[i].toCharArray();
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 'R'){
                    start.row = i;
                    start.col = j;
                }
            }
        }
        
        visited = new boolean[n][m];
    
        
        int answer = bfs(start);
        return answer;
    }
}