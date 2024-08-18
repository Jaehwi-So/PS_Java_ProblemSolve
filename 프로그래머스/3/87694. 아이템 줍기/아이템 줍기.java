import java.util.*;

class Point{
    int row;
    int col;
    public Point(int row, int col){
        this.row = row;
        this.col = col;
    }
}

class RPoint extends Point{
    int dist;
    public RPoint(int row, int col, int dist){
        super(row, col);
        this.dist = dist;
    }
}

class Solution {
    static int n = 0;
    static int m = 0;
    static int[][] matrix;
    static boolean[][] visited;
    static int[] dy = {0, 0, -1, 1, 1, 1, -1, -1};
    static int[] dx = {1, -1, 0, 0, 1, -1, 1, -1};
    
    
    static void getSideLine(){
        Stack<Point> stack = new Stack();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 1){
                    for(int k = 0; k < 8; k++){
                        int row = i + dy[k];
                        int col = j + dx[k];
                        if(row < 0 || row >= n || col < 0 || col >= m || matrix[row][col] == 3){
                            stack.push(new Point(i, j));
                            break;
                        }
                    }
                }
            }
        }
        while(!stack.isEmpty()){
            Point p = stack.pop();
            matrix[p.row][p.col] = 2;
        }
    }
    
    static void getOutside(Point start){
        Queue<Point> queue = new LinkedList();
        queue.offer(start);
        matrix[start.row][start.col] = 3;
        
        while(!queue.isEmpty()){
            Point current = queue.poll();
            for(int i = 0; i < 4; i++){
                int row = current.row + dy[i];
                int col = current.col + dx[i];
                if(row >= 0 && row < n && col >= 0 && col < m && matrix[row][col] == 0){
                    queue.offer(new Point(row, col));
                    matrix[row][col] = 3;
                }
            }
        }       
    }
    
    static int getResult(RPoint start, RPoint end){
        Queue<RPoint> queue = new LinkedList();
        visited = new boolean[n][m];
        queue.offer(start);
        visited[start.row][start.col] = true;
        
        while(!queue.isEmpty()){
            RPoint current = queue.poll();
            if(current.row == end.row && current.col == end.col){
                return current.dist;
            }
            for(int i = 0; i < 4; i++){
                int row = current.row + dy[i];
                int col = current.col + dx[i];
                if(row >= 0 && row < n && col >= 0 && col < m){
                    if(!visited[row][col] && matrix[row][col] == 2){
                        queue.offer(new RPoint(row, col, current.dist + 1));
                        visited[row][col] = true;
                    }
                }
            }
        }  
        return 0;
    }
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        // 두 배로 확장하여 도형 그리기
        for(int[] point : rectangle){
            n = Math.max(n, point[3] * 2 + 1);
            m = Math.max(m, point[2] * 2 + 1);
        }
        matrix = new int[n][m];
        for(int[] point : rectangle){
            for(int i = point[1] * 2; i <= (point[3] * 2) ; i++){
                for(int j = point[0] * 2; j <= (point[2] * 2); j++){
                    matrix[i][j] = 1;
                }
            }
        }
        
        // 도형의 완전 바깥쪽 구분
        for(int i = 0; i < n; i++){
            if(matrix[i][0] == 0) getOutside(new Point(i, 0));
            if(matrix[i][m-1] == 0) getOutside(new Point(i, m-1));
        }
        for(int i = 0; i < m; i++){
            if(matrix[0][i] == 0) getOutside(new Point(0, i));
            if(matrix[n-1][i] == 0) getOutside(new Point(n-1, i));
        }
        
        // 도형 윤곽선 구분         
        getSideLine();

        // for(int i = matrix.length - 1; i >= 0; i--){
        //     System.out.println(Arrays.toString(matrix[i]));
        // }

        // 윤곽선을 따라 탐색 후 2로 나누어 원래 크기로 조절
        int answer = getResult(new RPoint(characterY * 2, characterX * 2, 0), new RPoint(itemY * 2, itemX * 2, 0)) / 2;
        return answer;
    }
}