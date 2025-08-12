import java.util.*;
import java.io.*;

public class Main {
    static int n, m, s, e;
    static int[] weight;
    static int[][] edges;
    static final long MIN = Long.MIN_VALUE;
    static Queue<Integer> queue = new LinkedList<>();
    static boolean[] visited;
    static boolean availableFinish(){
        while(!queue.isEmpty()){
            int current = queue.poll();
            if(current == e) return true;
            for(int[] next : edges){
                if(next[0] != current) continue;
                if(visited[next[1]]) continue;
                queue.offer(next[1]);
                visited[next[1]] = true;
            }
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        weight = new int[n];
        edges = new int[m][3];
        visited = new boolean[n+1];

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            weight[i] = Integer.parseInt(st.nextToken());
        }

        long[] distance = new long[n];
        Arrays.fill(distance, MIN);
        distance[s] = weight[s];
        for(int i = 0; i < n - 1; i++){
            for(int j = 0; j < m; j++){
                int[] edge = edges[j];
                if(distance[edge[0]] == MIN) continue;
                distance[edge[1]] = Math.max(distance[edge[1]], distance[edge[0]] - edge[2] + weight[edge[1]]);
//                System.out.println(distance[edge[0]] + " " +  edge[2] + " " + weight[edge[1]]);
            }
//            System.out.println(Arrays.toString(distance));
        }

        long result = distance[e];


        Set<Integer> set = new HashSet<>();
        for(int j = 0; j < m; j++){
            int[] edge = edges[j];
            if(distance[edge[0]] == MIN) continue;
            if(distance[edge[0]] - edge[2] + weight[edge[1]] > distance[edge[1]]){
                set.add(edge[0]);
                set.add(edge[1]);
            }
        }

        for(int node : set){
            visited[node] = true;
            queue.offer(node);
        }
        if(availableFinish()){
            System.out.println("Gee");
            return;
        }

        if(result == MIN){
            System.out.println("gg");
        }
        else{
            System.out.println(result);
        }

    }
}