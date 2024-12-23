import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] matrix;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static Queue<int[]> outside = new LinkedList<>();
    static final int MAX = 100;
    static int[] parent;

    static void bfs(int[] start, int index){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        matrix[start[0]][start[1]] = index;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || col < 0 || row >= n || col >= m){
                    continue;
                }
                if(matrix[row][col] == 1){
                    queue.offer(new int[]{row, col});
                    matrix[row][col] = index;
                }
                else if(matrix[row][col] == 0){
                    outside.offer(new int[]{index, row, col, i});
                }
            }
        }


    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int index = 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 1){
                    index++;
                    bfs(new int[]{i, j}, index);
                }
            }
        }

        int[][] graph = new int[index+2][index+2];
        for(int[] line : graph){
            Arrays.fill(line, MAX);
        }
        while(!outside.isEmpty()){
            int[] info = outside.poll();
            int idx = info[0];
            int direct = info[3];
            int row = info[1];
            int col = info[2];
            int distance = 0;
            while(row >= 0 && col >= 0 && row < n && col < m){
                if(matrix[row][col] != 0 && matrix[row][col] <= idx) break;
                else if(matrix[row][col] > idx){
                    if(distance > 1){
                        graph[idx][matrix[row][col]] = Math.min(graph[idx][matrix[row][col]], distance);
                    }
                    break;
                }
                row += dy[direct];
                col += dx[direct];
                distance++;
            }
        }

        List<int[]> edges = new ArrayList<int[]>();

        for(int i = 2; i < index+2; i++){
            for(int j = 2; j < index+2; j++){
                if(graph[i][j] != MAX){
                    edges.add(new int[]{i, j, graph[i][j]});
                }
            }
        }

//        for(int[] edge : edges){
//            System.out.println(Arrays.toString(edge));
//        }

        Collections.sort(edges, new Comparator<int[]>(){
            public int compare(int[] a1, int[] a2){
                return a1[2] - a2[2];
            }
        });

        parent = new int[index+1];
        for(int i = 2; i <= index; i++){
            parent[i] = i;
        }

        int result = 0;
        int count = 0;
        for(int i = 0; i < edges.size(); i++){
            int[] edge = edges.get(i);
            if(union(edge[0], edge[1])){
                result += edge[2];
                count++;
            }
            if(count == index - 2) break;
        }

        if(count == index - 2){
            System.out.println(result);
        }
        else{
            System.out.println(-1);
        }
    }

    static boolean union(int n1, int n2){
        int a = find(n1);
        int b = find(n2);
        if(a == b) return false;
        if(a > b) parent[a] = b;
        else parent[b] = a;
        return true;
    }
    static int find(int n){
        if(parent[n] != n){
            parent[n] = find(parent[n]);
        }
        return parent[n];
    }
}