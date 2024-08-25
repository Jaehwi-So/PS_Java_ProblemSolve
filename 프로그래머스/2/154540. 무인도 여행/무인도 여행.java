import java.util.*;
class Solution {
    static int n;
    static int m;
    static boolean[][] visited;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static int[][] matrix;
    static int bfs(int srow, int scol){
        int cnt = 0;
        Queue<int[]> queue = new LinkedList();
        queue.offer(new int[]{srow, scol});
        visited[srow][scol] = true;
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            cnt += matrix[current[0]][current[1]];
            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || row >= n || col < 0 || col >= m) continue;
                if(!visited[row][col] && matrix[row][col] != -1){
                    queue.offer(new int[]{row, col});
                    visited[row][col] = true;
                }
            }
        }
        return cnt;
    }
    public int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        matrix = new int[n][m];
        visited = new boolean[n][m];
        for(int i = 0; i < n; i++){
            char[] chs = maps[i].toCharArray();
            for(int j = 0; j < m; j++){
                if(chs[j] == 'X') matrix[i][j] = -1;
                else matrix[i][j] = Character.getNumericValue(chs[j]);
            }
        }
        
        List<Integer> list = new ArrayList();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] != -1 && !visited[i][j]){
                    int r = bfs(i, j);
                    list.add(r);
                }
            }
        }
        
        Collections.sort(list);
        int[] answer = {};
        if(list.size() == 0){
            answer = new int[]{-1};
        }
        else{
            answer = new int[list.size()];
            for(int i = 0; i < answer.length; i++){
               answer[i] = list.get(i);
            }
        }
        
        
        return answer;
    }
}