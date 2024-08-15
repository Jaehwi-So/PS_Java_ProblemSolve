import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int row;
    int col;
    int w;
    public Node(int row, int col, int w){
        this.row = row;
        this.col = col;
        this.w = w;
    }

    public int compareTo(Node n){
        return this.w - n.w;
    }

}

public class Main {

    static int n;
    static char[][] matrix;
    static int[][] weight;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static int parseChar(char ch){
        return Character.getNumericValue(ch);
    }

    static int dijakstra(){
        Node start = new Node(0, 0, parseChar(matrix[0][0]));
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(start);
        weight[0][0] = parseChar(matrix[0][0]);

        while(!queue.isEmpty()){
            Node current = queue.poll();
            if(visited[current.row][current.col]) continue;

            if(current.row == n-1 && current.col == n-1){
                break;
            }

            visited[current.row][current.col] = true;

            for(int i = 0; i < 4; i++){
                int nrow = current.row + dy[i];
                int ncol = current.col + dx[i];

                if(nrow < 0 || nrow >= n || ncol < 0 || ncol >= n) continue;
                if(visited[nrow][ncol]) continue;

                if(weight[nrow][ncol] > weight[current.row][current.col] + parseChar(matrix[nrow][ncol])){
                    weight[nrow][ncol] = weight[current.row][current.col] + parseChar(matrix[nrow][ncol]);
                    queue.offer(new Node(nrow, ncol, weight[nrow][ncol]));
                }
            }
        }

        return weight[n-1][n-1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int index = 1;
        StringBuilder sb = new StringBuilder();
        while(true){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            if(n == 0) break;

            matrix = new char[n][n];
            weight = new int[n][n];
            visited = new boolean[n][n];
            for(int[] line : weight){
                Arrays.fill(line, Integer.MAX_VALUE);
            }

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    matrix[i][j] = st.nextToken().charAt(0);
                }
            }

            String s = String.format("Problem %d: %d", index, dijakstra());
            index++;
            sb.append(s).append("\n");
        }

        System.out.println(sb);

    }
}