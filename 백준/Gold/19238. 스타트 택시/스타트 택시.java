import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int cost;
    static int[][] matrix;
    static int[] start;
    static int[][][] persons;
    static List<Integer>[][] ends;
    static boolean[] success;
    static int[] dist;
    static int[][] nextDist;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static final int MAX = 5000000;

    static int bfs(int srow, int scol, int erow, int ecol){
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n+1][n+1];
        queue.offer(new int[]{srow, scol, 0});
        visited[srow][scol] = true;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            if(current[0] == erow && current[1] == ecol){
                return current[2];
            }
            else{
                for(int i = 0; i < 4; i++){
                    int row = current[0] + dy[i];
                    int col = current[1] + dx[i];
                    if(row <= 0 || col <= 0 || row > n || col > n) continue;
                    if(matrix[row][col] == 1) continue;
                    if(!visited[row][col]){
                        visited[row][col] = true;
                        queue.offer(new int[]{row, col, current[2] + 1});
                    }
                }
            }
        }
        return MAX;
    }

    static int[] globalBfs(int srow, int scol){
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n+1][n+1];
        queue.offer(new int[]{srow, scol, 0});
        visited[srow][scol] = true;

        int[] result = new int[m];
        Arrays.fill(result, MAX);

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            if(ends[current[0]][current[1]].size() > 0){
                for(int index : ends[current[0]][current[1]]){
                    result[index] = current[2];
                }
            }
            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row <= 0 || col <= 0 || row > n || col > n) continue;
                if(matrix[row][col] == 1) continue;
                if(!visited[row][col]){
                    visited[row][col] = true;
                    queue.offer(new int[]{row, col, current[2] + 1});
                }
            }
        }
        return result;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cost = Integer.parseInt(st.nextToken());

        matrix = new int[n+1][n+1];
        ends = new ArrayList[n+1][n+1];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
                ends[i][j] = new ArrayList<>();
            }
        }


        persons = new int[m][2][2];
        start = new int[2];
        dist = new int[m];
        nextDist = new int[m][m];


        st = new StringTokenizer(br.readLine());
        start[0] = Integer.parseInt(st.nextToken());
        start[1] = Integer.parseInt(st.nextToken());

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            persons[i][0][0] = Integer.parseInt(st.nextToken());
            persons[i][0][1] = Integer.parseInt(st.nextToken());
            persons[i][1][0] = Integer.parseInt(st.nextToken());
            persons[i][1][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(persons, new Comparator<int[][]>() {
            @Override
            public int compare(int[][] o1, int[][] o2) {
                if(o1[0][0] == o2[0][0]) return o1[0][1] - o2[0][1];
                return o1[0][0] - o2[0][0];
            }
        });

        for(int i = 0; i < m; i++){
            ends[persons[i][1][0]][persons[i][1][1]].add(i);
        }

        for(int i = 0; i < m; i++){
            dist[i] = bfs(persons[i][0][0], persons[i][0][1], persons[i][1][0], persons[i][1][1]);
            int[] globDist = globalBfs(persons[i][0][0], persons[i][0][1]);
            for(int j = 0; j < m; j++){
                if(i == j) continue;
                nextDist[j][i] = globDist[j];
            }
        }
        
        success = new boolean[m];
        int current = 0;
        int min = MAX;
        for(int i = 0; i < m; i++){
            int d = bfs(start[0], start[1], persons[i][0][0], persons[i][0][1]);
            if(d < min){
                min = d;
                current = i;
            }
        }
        if(min + dist[current] <= cost){
            success[current] = true;
            cost = cost - min + dist[current];
            while(cost >= 0){
                min = MAX;
                int next = -1;
                for(int i = 0; i < m; i++){
                    if(!success[i]){
                        if(nextDist[current][i] < min){
                            min = nextDist[current][i];
                            next = i;
                        }
                    }
                }
                if(next == -1) break;
                if(nextDist[current][next] + dist[next] <= cost){
                    success[next] = true;
                    cost = cost - min + dist[next];
                    current = next;
                }
                else{
                    break;
                }
            }
        }

        int result = cost;
        for(boolean b : success){
            if(b == false){
                result = -1;
                break;
            }
        }
        System.out.println(result);
    }
}