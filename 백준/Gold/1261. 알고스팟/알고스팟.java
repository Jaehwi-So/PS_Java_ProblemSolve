import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int row;
    int col;
    int dist;
    public Node(int row, int col, int dist){
        this.row = row;
        this.col = col;
        this.dist = dist;
    }

    public int compareTo(Node n){
        return this.dist - n.dist;
    }
}

public class Main {
    static int n;
    static int m;
    static char[][] matrix;
    static int[][] distance;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static void djakstra(){
        Node start = new Node(0, 0, 0);
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(start);
        distance[0][0] = 0;

        while(!queue.isEmpty()){
            Node current = queue.poll();
            if(visited[current.row][current.col]){
                continue;
            }
            visited[current.row][current.col] = true;
            for(int i = 0; i < 4; i++){
                int nrow = current.row + dx[i];
                int ncol = current.col + dy[i];
                if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m){
                    int nextDist = distance[current.row][current.col] + Character.getNumericValue(matrix[nrow][ncol]);
                    if(nextDist < distance[nrow][ncol]){
                        distance[nrow][ncol] = nextDist;
                        queue.offer(new Node(nrow, ncol, nextDist));
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
        distance = new int[n][m];
        visited = new boolean[n][m];

        for(int[] line : distance){
            Arrays.fill(line, Integer.MAX_VALUE);
        }

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            matrix[i] = st.nextToken().toCharArray();
        }

        djakstra();

        System.out.println(distance[n-1][m-1]);

    }
}
