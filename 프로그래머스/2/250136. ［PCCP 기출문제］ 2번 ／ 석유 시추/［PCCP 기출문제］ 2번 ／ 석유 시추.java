import java.util.*;

class Solution {
    static int[][] matrix;
    static boolean[][] visited;
    static int n;
    static int m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    
    static int[] bfs(int srow, int scol){
        int count = 0;
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;
        visited[srow][scol] = true;
        Queue<int[]> queue = new LinkedList();
        queue.offer(new int[]{srow, scol});
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            count++;
            minCol = Math.min(minCol, current[1]);
            maxCol = Math.max(maxCol, current[1]);
            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || row >= n || col < 0 || col >= m){
                    continue;
                }
                if(!visited[row][col] && matrix[row][col] == 1){
                    queue.offer(new int[]{row, col});
                    visited[row][col] = true;
                }
            }
        }
        return new int[]{count, minCol, maxCol};
    }
    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        matrix = land;
        visited = new boolean[n][m];
        int[] result = new int[m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 1 && !visited[i][j]){
                    int[] info = bfs(i, j);
                    for(int k = info[1]; k <= info[2]; k++){
                        result[k] += info[0];
                    }
                }
            }
        }
        
        int answer = 0;
        for(int i = 0; i < m; i++){
            answer = Math.max(answer, result[i]);
        }
        
        return answer;
    }
}