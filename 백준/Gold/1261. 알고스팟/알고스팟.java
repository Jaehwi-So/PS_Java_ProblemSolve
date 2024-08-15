
import java.io.*;
import java.util.*;

class Node{
    int row;
    int col;
    int dist;
    public Node(int row, int col, int dist){
        this.row = row;
        this.col = col;
        this.dist = dist;
    }
}
public class Main {
    static int n;
    static int m;
    static char[][] matrix;
    static int[][] dp;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static void bfs(){
        Node start = new Node(0, 0, 0);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);
        dp[0][0] = Character.getNumericValue(matrix[0][0]);

        while(!queue.isEmpty()){
            Node current = queue.poll();
            if(dp[current.row][current.col] >= current.dist){
                for(int i = 0; i < 4; i++){
                    int nrow = current.row + dx[i];
                    int ncol = current.col + dy[i];
                    if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m){
                        int nextDist = current.dist + Character.getNumericValue(matrix[nrow][ncol]);
                        if(nextDist < dp[nrow][ncol]){
                            dp[nrow][ncol] = nextDist;
                            queue.offer(new Node(nrow, ncol, nextDist));
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        matrix = new char[n][m];
        dp = new int[n][m];
        for(int[] line : dp){
            Arrays.fill(line, Integer.MAX_VALUE);
        }
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            matrix[i] = st.nextToken().toCharArray();
        }

        bfs();

//        for(int[] line : dp){
//            System.out.println(Arrays.toString(line));
//        }
        System.out.println(dp[n-1][m-1]);

    }
}