import java.io.*;
import java.util.*;

class Node{
    int index;
    int direct;
    boolean isBottom;
    int cluster;
    public Node(int index, int direct, int cluster){
        this.index = index;
        this.direct = direct;
        this.cluster = cluster;
        this.isBottom = true;
    }
}

class Cluster{
    int index;
    int row;
    int col;
    Queue<Node> elements = new LinkedList<>();

    public Cluster(Node n, int index, int row, int col){
        this.elements.add(n);
        this.index = index;
        this.row = row;
        this.col = col;
    }

    void reverse(){
        Stack<Node> temp = new Stack<>();
        while(!elements.isEmpty()){
            Node n = elements.poll();
            n.isBottom = false;
            temp.push(n);
        }
        while(!temp.isEmpty()){
            elements.offer(temp.pop());
        }

        this.getBottom().isBottom = true;
    }

    void addNodes(Node n){
        elements.add(n);
        n.cluster = index;
        n.isBottom = false;
    }

    void addNodes(Cluster c){
        while(!c.elements.isEmpty()){
            Node n = c.elements.poll();
            n.cluster = index;
            n.isBottom = false;
            elements.add(n);

        }
    }

    void reverseDirect(){
        if(this.getBottom().direct % 2 == 0){
            this.getBottom().direct--;
        }
        else{
            this.getBottom().direct++;
        }

    }

    Node getBottom(){
        return this.elements.peek();
    }
}

public class Main {
    static int n;
    static int a;
    static int[][] map;
    static int[][] matrix;
    static Node[] nodes;
    static Cluster[] clusters;
    static int[] dy = {0, 0, 0, -1, 1};
    static int[] dx = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[a][a];
        matrix = new int[a][a];

        for(int i = 0; i < a; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < a; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        nodes = new Node[n+1];
        clusters = new Cluster[n+1];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int direct = Integer.parseInt(st.nextToken());
            nodes[i+1] = new Node(i + 1, direct, i+1);
            clusters[i+1] = new Cluster(nodes[i+1], i + 1, row, col);
            matrix[row][col] = i+1;
        }

        int count = 0;
        while(true){
            boolean exit = false;
            for(int k = 1; k <= n; k++){
                Node n = nodes[k];
                if(n.isBottom && clusters[n.cluster].elements.size() >= 4){
                    exit = true;
                    break;
                }
            }
            if(exit) break;
            if(count > 1000){
                count = -1;
                break;
            }
            for(int i = 1; i <= n; i++){
                if(nodes[i].isBottom) {
                    Cluster cluster = clusters[nodes[i].cluster];
                    int nrow = cluster.row + dy[cluster.getBottom().direct];
                    int ncol = cluster.col + dx[cluster.getBottom().direct];
                    boolean move = true;
                    if (nrow < 0 || ncol < 0 || nrow >= a || ncol >= a || map[nrow][ncol] == 2) {
                        cluster.reverseDirect();
                        nrow = cluster.row + dy[cluster.getBottom().direct];
                        ncol = cluster.col + dx[cluster.getBottom().direct];
                        if (nrow < 0 || ncol < 0 || nrow >= a || ncol >= a || map[nrow][ncol] == 2) {
                            move = false;
                        }
                    }
                    if (move) {
                        if (map[nrow][ncol] == 1) {
                            cluster.reverse();
                        }
                        if (matrix[nrow][ncol] != 0) {
                            matrix[cluster.row][cluster.col] = 0;
                            clusters[matrix[nrow][ncol]].addNodes(cluster);
                        } else {
                            matrix[nrow][ncol] = cluster.index;
                            matrix[cluster.row][cluster.col] = 0;
                            cluster.row = nrow;
                            cluster.col = ncol;
                        }

                    }
                }
            }
            count++;
        }

        System.out.println(count);

    }
}