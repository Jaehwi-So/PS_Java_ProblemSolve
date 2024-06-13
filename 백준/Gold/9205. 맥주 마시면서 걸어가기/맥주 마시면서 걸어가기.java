import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] adjMat;
    static int[][] points;

    static boolean bfs(int start){
        boolean[] visited = new boolean[n+2];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        while(!queue.isEmpty()){
            int index = queue.poll();
            visited[index] = true;
            if(index == n+1){
                return true;
            }
            else{
                for(int i = 0; i < n+2; i++){
                    if(visited[i] == false && adjMat[index][i] <= 1000){
                        queue.offer(i);
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int test = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < test; t++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            adjMat = new int[n+2][n+2];
            points = new int[n+2][2];
            points[0][0] = Integer.parseInt(st.nextToken());
            points[0][1] = Integer.parseInt(st.nextToken());
            for(int i = 1; i <= n; i++){
                st = new StringTokenizer(br.readLine());
                points[i][0] = Integer.parseInt(st.nextToken());
                points[i][1] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            points[n+1][0] = Integer.parseInt(st.nextToken());
            points[n+1][1] = Integer.parseInt(st.nextToken());

            for(int i = 0; i < n+1; i++){
                for(int j = i; j < n+2; j++){
                    int distance = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                    adjMat[i][j] = distance;
                    adjMat[j][i] = distance;
                }
            }

//            for(int[] line : adjMat){
//                System.out.println(Arrays.toString(line));
//            }

            if(bfs(0)){
                sb.append("happy").append("\n");
            }
            else{
                sb.append("sad").append("\n");
            }
        }
        System.out.println(sb);

    }
}