
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Position{
    int redX;
    int redY;
    int blueX;
    int blueY;
    int count;
    int distance;
    public Position(int redX, int redY, int blueX, int blueY, int count, int type){
        this.redX = redX;
        this.redY = redY;
        this.blueX = blueX;
        this.blueY = blueY;
        this.count = count;
        this.distance = type;
    }
}

public class Main {
    static char[][] matrix;
    static boolean[][][][] visited;
    static int n;
    static int m;

    static int[] dx = {0, 0, 1, -1, 0};
    static int[] dy = {1, -1, 0, 0, 0};
    static int result = -1;


    static boolean isAvailable(int x, int y){
        if(x >= 0 && x < m && y >= 0 && y < n && matrix[y][x] == '.'){
            return true;
        }
        return false;
    }

    static boolean isSameTime(int endX, int endY, int blueX, int blueY, int dist){
        while(blueX >= 0 && blueX < m && blueY >= 0 && blueY < n && matrix[blueY][blueX] == '.'){
            blueX += dx[dist];
            blueY += dy[dist];
            if(blueX == endX && blueY == endY){
                return true;
            }
        };
        return false;
    }

    static void bfs(int srX, int srY, int sbX, int sbY, int endX, int endY){
        Queue<Position> queue = new LinkedList<>();
        visited[srX][srY][sbX][sbY] = true;
        for(int i = 0; i < 4; i++){
            Position start = new Position(srX, srY, sbX, sbY, 1, i);
            queue.offer(start);
        }
        while(!queue.isEmpty()){
            Position current = queue.poll();
//            System.out.println(current.count + " (" + current.redX + " " + current.redY + ")" + "(" + current.blueX + " " + current.blueY + ")");

            int pos = current.distance;
            int nextRx = current.redX;
            int nextRy = current.redY;
            int nextBx = current.blueX;
            int nextBy = current.blueY;

            while(true){
                boolean isMove = false;
                int beforeRx = nextRx;
                int beforeRy = nextRy;
                int beforeBx = nextBx;
                int beforeBy = nextBy;
//                System.out.println(current.count + " (" + beforeRx + " " + beforeRy + ")" + "(" + beforeBx + " " + beforeBy + ")");


                if(isAvailable(nextRx + dx[pos], nextRy + dy[pos])){
                    nextRx = nextRx + dx[pos];
                    nextRy = nextRy + dy[pos];
                    isMove = true;
                }
                if(isAvailable(nextBx + dx[pos], nextBy + dy[pos])){
                    nextBx = nextBx + dx[pos];
                    nextBy = nextBy + dy[pos];
                    isMove = true;
                }

                if(nextBx == endX && nextBy == endY || current.count > 10){
                    break;
                }
                if(nextRx == endX && nextRy == endY  && current.count <= 10){
                    if(isSameTime(endX, endY, nextBx, nextBy, pos)){
                        break;
                    }
                    else{
                        result = current.count;
                        return;
                    }
                }

                if(!isMove || (nextRx == nextBx && nextRy == nextBy)){
                    if(!visited[beforeRx][beforeRy][beforeBx][beforeBy]){
                        visited[beforeRx][beforeRy][beforeBx][beforeBy] = true;
                        for(int i = 0; i < 4; i++){
                            if(pos != i){
                                queue.offer(new Position(beforeRx, beforeRy, beforeBx, beforeBy, current.count + 1, i));
                            }
                        }
                    }
                    break;
                }


            }
        }
    }

    // 1 3 -> 1 2, 1 4, 2 3, 0 3

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new char[n][m];
        visited = new boolean[m][n][m][n];
        int redX = 0, redY = 0, blueX = 0, blueY = 0, endX = 0, endY = 0;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            matrix[i] = st.nextToken().toCharArray();
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 'R'){
                    redX = j;
                    redY = i;
                    matrix[i][j] = '.';
                }
                else if(matrix[i][j] == 'B'){
                    blueX = j;
                    blueY = i;
                    matrix[i][j] = '.';
                }
                else if(matrix[i][j] == 'O'){
                    endX = j;
                    endY = i;
                    matrix[i][j] = '.';
                }
            }
        }

//        for(char[] line : matrix){
//            System.out.println(Arrays.toString(line));
//        }

        bfs(redX, redY, blueX, blueY, endX, endY);
        System.out.println(result);


    }
}