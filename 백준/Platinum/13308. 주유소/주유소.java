import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] costs;
    static List<int[]>[] graph;
    static final int MAX = 40000 * 2500 * 2500;

    static int dijakstra(){

        int[][] visited = new int[n+1][2501];
        for(int[] line : visited){
            Arrays.fill(line, MAX);
        }
        Queue<int[]> queue = new LinkedList<>(); //노드, 총비용, 최소기름비용
        queue.offer(new int[]{1, 0, costs[1]});
        visited[1][costs[1]] = 0;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            if(visited[current[0]][current[2]] < current[1]) continue;

            for(int[] next : graph[current[0]]){
                int val = current[1] + (current[2] * next[1]);
                int min = Math.min(costs[next[0]], current[2]);
                if(visited[next[0]][min] > val){
                    visited[next[0]][min] = val;
                    queue.offer(new int[]{next[0], val, min});
                }
            }
        }

        int result = MAX;
        for(int i = 0; i <= 2500; i++){
            result = Math.min(result, visited[n][i]);
        }

        return result;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        costs = new int[n+1];
        graph = new ArrayList[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            costs[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new int[]{e, w});
            graph[e].add(new int[]{s, w});
        }

        System.out.println(dijakstra());
    }
}