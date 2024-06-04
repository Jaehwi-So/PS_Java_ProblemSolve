
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] array;
    static int n;
    static int lower;
    static int upper;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static List<List<int[]>> list = new ArrayList<>();

    static int bfs(int cr, int cc){
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> result = new ArrayList<>();
        queue.offer(new int[]{cr, cc});
        visited[cr][cc] = true;
        result.add(new int[]{cr, cc});

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];

            for(int i = 0; i < 4; i++){
                int row = r + dy[i];
                int col = c + dx[i];
                if(row >= 0 && row < n && col >= 0 && col < n){
                    int calc = Math.abs(array[row][col] - array[r][c]);
                    if(visited[row][col] == false && (calc >= lower && calc <= upper)){
                        queue.offer(new int[]{row, col});
                        visited[row][col] = true;
                        result.add(new int[]{row, col});
                    }
                }
            }
        }
        if(result.size() > 1){
            list.add(result);
        }
        return result.size();
    }

    static void move(){
        for(List<int[]> cluster : list){
            int sum = 0;
            for(int[] point : cluster){
                sum += array[point[0]][point[1]];
            }
            int calc = sum / cluster.size();
            for(int[] point : cluster){
                array[point[0]][point[1]] = calc;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        lower = Integer.parseInt(st.nextToken());
        upper = Integer.parseInt(st.nextToken());
        array = new int[n][n];
        visited = new boolean[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while(true){
            list.clear();
            for(boolean[] line : visited){
                Arrays.fill(line, false);
            }

            boolean isExit = true;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(visited[i][j] == false){
                        int cnt = bfs(i, j);
                        if(cnt > 1){
                            isExit = false;
                        }
                    }
                }
            }

            if(isExit){
                break;
            }

            move();
            day++;

//            for(List<int[]> cluster : list){
//                for(int[] point : cluster){
//                    System.out.print("(" + point[0] + ", "+ point[1] + ")");
//                }
//                System.out.println();
//            }

//            for(int[] line : array){
//                System.out.println(Arrays.toString(line));
//            }
//            System.out.println();
        }

        System.out.println(day);


    }
}