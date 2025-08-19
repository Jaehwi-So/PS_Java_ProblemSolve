import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] matrix;
    static int[] start = new int[2];
    static int[] end = new int[2];
    static int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dx = {-2, -1, 1, 2, -2, -1, 1, 2};
    static final int MAX = Integer.MAX_VALUE;
    static int[][] dist;
    static long[][] result;
    static boolean[][] visited;
    static Set<Integer>[][] paths;
    static boolean[][] pathDP;

    static void getDistance(){
        dist = new int[n][m];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        for(int[] line : dist){
            Arrays.fill(line, MAX);
        }

        dist[start[0]][start[1]] = 0;
        pq.offer(new int[]{start[0], start[1], 0});

        while(!pq.isEmpty()){
            int[] current = pq.poll();
            if(dist[current[0]][current[1]] < current[2]) continue;

            for(int i = 0; i < 8; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || col < 0 || row >= n || col >= m) continue;
                if(matrix[row][col] == 2) continue;
                int next = matrix[row][col] == 1 ? current[2] : current[2] + 1;
                if(dist[row][col] > next){
                    dist[row][col] = next;
                    pq.offer(new int[]{row, col, next});
                }
            }
        }
    }

    static Set<Integer> getAvaliablePath(int r, int c){
        if(!pathDP[r][c]){
            visited = new boolean[n][m];
            paths[r][c] = getPath(r, c);
            pathDP[r][c] = true;
        }
        return paths[r][c];

    }
    static Set<Integer> getPath(int r, int c){
        Set<Integer> result = new HashSet<>();
        visited[r][c] = true;
        for(int i = 0; i < 8; i++){
            int row = r + dy[i];
            int col = c + dx[i];
            if(row < 0 || col < 0 || row >= n || col >= m) continue;
            if(matrix[row][col] == 2) continue;
            if(!visited[row][col]){
                if(matrix[row][col] == 0){
                    result.add((row * m) + col);
                }
                else{
                    Set<Integer> tmp = getPath(row, col);
                    result.addAll(tmp);
                }
            }
        }
        return result;
    }

    static long getResult(int r, int c){
        if(result[r][c] == Long.MAX_VALUE){
            result[r][c] = 0L;
            Set<Integer> path = getAvaliablePath(r, c);
//            System.out.println(r + " " + c);
//            System.out.println(path);
            for(int next : path){
                int row = next / m;
                int col = next % m;
                if(dist[row][col] == dist[r][c] - 1){
                    result[r][c] += getResult(row, col);
                }
            }
        }
        return result[r][c];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];

        //3 = start, 4 = end
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                int k = Integer.parseInt(st.nextToken());
                if(k == 3){
                    start[0] = i;
                    start[1] = j;
                    k = 1;
                }
                else if(k == 4){
                    end[0] = i;
                    end[1] = j;
                    k = 1;
                }
                matrix[i][j] = k;
            }
        }


        getDistance();

        if(dist[end[0]][end[1]] == MAX){
            System.out.println(-1);
            return;
        }

        if(dist[end[0]][end[1]] == 0){
            System.out.println(0);
            System.out.println(1);
            return;
        }

        paths = new Set[n][m];
        pathDP = new boolean[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                paths[i][j] = new HashSet<>();
            }
        }


//        for(int[] line : dist){
//            System.out.println(Arrays.toString(line));
//        }

        result = new long[n][m];
        for(long[] line : result){
            Arrays.fill(line, Long.MAX_VALUE);
        }

        Set<Integer> starts = getAvaliablePath(start[0], start[1]);
        for(int k : starts){
            int r = k / m;
            int c = k % m;
            if(dist[r][c] == 1) {
                result[r][c] = 1L;
            }
        }
        long answer = 0;
        Set<Integer> ends = getAvaliablePath(end[0], end[1]);
//        System.out.println(ends);
        for(int k : ends){
            int r = k / m;
            int c = k % m;
            if(dist[r][c] == dist[end[0]][end[1]]){
                answer += getResult(r, c);
            }
        }

        System.out.println(dist[end[0]][end[1]]);
        System.out.println(answer);
    }
}