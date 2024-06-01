import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[][] array;
    static boolean[][] visited;
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};
    static int weight = 2;
    static int[] bfs(int r, int c){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c, 0});
        for(boolean[] line : visited){
            Arrays.fill(line, false);
        }

        int min = Integer.MAX_VALUE;
        List<int[]> candidate = new ArrayList<>();
        while(!queue.isEmpty()){
            int[] k = queue.poll();
            int cr = k[0];
            int cc = k[1];
            int step = k[2];
            if(visited[cr][cc] == false){
                visited[cr][cc] = true;
                if(array[cr][cc] > 0 && array[cr][cc] < weight && array[cr][cc] != 9){
                    if(step <= min){
                        min = step;
                        candidate.add(k);
                    }
                }
                for(int i = 0; i < 4; i++){
                    int row = cr + dy[i];
                    int col = cc + dx[i];
                    if(row >= 0 && col >= 0 && row < n && col < n){
                        if(visited[row][col] == false && array[row][col] <= weight){
                            queue.offer(new int[]{row, col, step + 1});
                        }
                    }
                }
            }
        }

        if(candidate.size() == 0){
            return new int[]{-1, -1, -1};
        }

        Collections.sort(candidate, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]){
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        return candidate.get(0);

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        array = new int[n][n];
        visited = new boolean[n][n];

        int[] current = new int[2];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
                if(array[i][j] == 9){
                    current = new int[]{i, j};
                }
            }
        }

        int result = 0;
        int upCount = 0;
        while(true){
            int[] search = bfs(current[0], current[1]);
            if(search[2] == -1){
                break;
            }
            array[current[0]][current[1]] = 0;
            current[0] = search[0];
            current[1] = search[1];
            result += search[2];
            array[current[0]][current[1]] = 9;

//            for(int[] line : array){
//                System.out.println(Arrays.toString(line));
//            }
//            System.out.println(result + " weight :" + weight);
//            System.out.println("=======");

            upCount++;
            if(weight == upCount){
                weight++;
                upCount = 0;
            }
        }

        System.out.println(result);
    }
}