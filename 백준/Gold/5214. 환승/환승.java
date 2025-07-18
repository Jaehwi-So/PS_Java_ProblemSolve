import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int k;
    static int m;
    static int len;
    static List<Integer>[] graph;

    static int bfs(){
        boolean[] visited = new boolean[len+1];
        Queue<int[]> queue = new LinkedList<>();
        visited[1] = true;
        queue.offer(new int[]{1, 1});

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            if(current[0] == n) return current[1];
            for(int next : graph[current[0]]){
                if(!visited[next]){
                    visited[next] = true;
                    if(next > n) queue.offer(new int[]{next, current[1] + 1});
                    else queue.offer(new int[]{next, current[1]});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        len = n+k;
        graph = new ArrayList[len+1];
        for(int i = 1; i <= len; i++){
            graph[i] = new ArrayList();
        }

        for(int i = 1; i <= k; i++){
            int index = n + i;
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                int a = Integer.parseInt(st.nextToken());
                graph[a].add(index);
                graph[index].add(a);
            }
        }

        System.out.println(bfs());

    }
}