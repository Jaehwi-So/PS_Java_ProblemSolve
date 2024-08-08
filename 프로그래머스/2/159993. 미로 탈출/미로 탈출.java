import java.util.*;

class Point{
    int row;
    int col;
    int time;
    public Point(int row, int col, int time){
        this.row = row;
        this.col = col;
        this.time = time;
    }
}

class Solution {
    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    
    static int bfs(Point start, Point end){
        Queue<Point> queue = new LinkedList();
        queue.offer(start);
        visited[start.row][start.col] = true;
        
        while(!queue.isEmpty()){
            Point current = queue.poll();
            if(current.row == end.row && current.col == end.col){
                return current.time;
            }
            
            for(int i = 0; i < 4; i++){
                int nrow = current.row + dy[i];
                int ncol = current.col + dx[i];
                if(nrow < 0 || nrow >= n || ncol < 0 || ncol >= m || map[nrow][ncol] == 1){
                    continue;
                }
                else{
                    if(!visited[nrow][ncol]){
                         queue.offer(new Point(nrow, ncol, current.time + 1));
                         visited[nrow][ncol] = true;
                    }
                }
            }
        }
        
        return -1;
    }
    public int solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        map = new int[n][m];
        Point start = new Point(0, 0, 0);
        Point lebb = new Point(0, 0, 0);
        Point end = new Point(0, 0, 0);
        
        for(int i = 0; i < n; i++){
            char[] chs = maps[i].toCharArray();
            for(int j = 0; j < m; j++){
                if(chs[j] == 'X'){
                    map[i][j] = 1;
                }
                else{
                    map[i][j] = 0;
                    if(chs[j] == 'S'){
                        start = new Point(i, j, 0);
                    }
                    else if(chs[j] == 'L'){
                        lebb = new Point(i, j, 0);
                    }
                    else if(chs[j] == 'E'){
                        end = new Point(i, j, 0);
                    }
                }
            }
        }
        
        visited = new boolean[n][m];
        int t1 = -1;
        int t2 = -1;
        int answer = -1;
        
        t1 = bfs(start, lebb);
        if(t1 != -1){
            visited = new boolean[n][m];
            t2 = bfs(lebb, end);
        }
        
        if(t2 != -1){
            answer = t1 + t2;
        }
        
        
        return answer;
    }
}