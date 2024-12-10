import java.io.*;
import java.util.*;

public class Main {
    static int f;
    static int s;
    static int u;
    static int g;
    static int d;
    static boolean[] visited;

    static int bfs(){
        Queue<int[]> queue = new LinkedList<>();
        visited[s] = true;
        queue.offer(new int[]{s, 0});

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int height = current[0];
            if(height == g){
                return current[1];
            }
            else{
                int up = height + u;
                int down = height - d;
                if(up <= f && !visited[up]){
                    queue.offer(new int[]{up, current[1] + 1});
                    visited[up] = true;
                }
                if(down > 0 && !visited[down]){
                    queue.offer(new int[]{down, current[1] + 1});
                    visited[down] = true;
                }
            }
        }

        return -1;


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        visited = new boolean[f+1];

        int result = bfs();
        if(result == -1){
            System.out.println("use the stairs");
        }
        else{
            System.out.println(result);
        }
    }
}