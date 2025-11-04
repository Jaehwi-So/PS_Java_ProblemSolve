import java.util.*;

class Solution {
    static int n, m;
    static int[][] matrix;
    static int[] dy = {0, 0, -1, 1}; //좌우상하
    static int[] dx = {-1, 1, 0, 0};
    static int[][][] tdir = { {{1, 2}, {0, 3}}, {{0, 2}, {1, 3}} };
    static int[][] ttype = {{0, 1, 1, 0}, {1, 0, 1, 0}};
    static int[][][] visited;
    static int max;
    
    public void bfs(int srow, int scol, int type, int step){

        int depth = 0;
        Queue<int[]> queue = new LinkedList();
        queue.offer(new int[]{srow, scol, type});
        
        visited[srow][scol][type] = step;
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int cbase = matrix[current[0]][current[1]];
            depth++;
            
            for(int i = 0; i < 2; i++){
                int ndir = tdir[cbase][current[2]][i];
                int row = current[0] + dy[ndir];
                int col = current[1] + dx[ndir];
                if(row < 0 || col < 0 || row >= n || col >= m) continue;
                if(visited[row][col][0] == step || visited[row][col][1] == step) continue;
                
                int nbase = matrix[row][col];
                int ntype = ttype[nbase][ndir];
                
                queue.offer(new int[]{row, col, ntype});
                visited[row][col][ntype] = step;
            }
        }
        
        this.max = Math.max(depth, max);
    
        
    }
    
    public int solution(int[][] grid) {
        this.n = grid.length;
        this.m = grid[0].length;
        this.matrix = new int[n][m];
        this.visited = new int[n][m][2];
        this.max = 0;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                matrix[i][j] = grid[i][j];
                if(matrix[i][j] == -1) matrix[i][j] = 0;
            }
        }
        
        
        int step = 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                for(int k = 0; k < 2; k++){
                    if(visited[i][j][k] == 0){
                        bfs(i, j, k, step);
                        step++;
                    }
                }
            }
        }
        
        int answer = (int)max;
        return answer;
    }
}

// 40000 000000

/**
[[1, -1, 1, -1], [1, 1, -1, -1], [-1, -1, 1, 1], [-1, -1, 1, -1]]
answer : 14
**/