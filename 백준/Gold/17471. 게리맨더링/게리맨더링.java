import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] value;
    static List<Integer>[] graph;
    static int[] zone;
    static int answer = Integer.MAX_VALUE;

    static int[] bfs(int z){
        int start = 0;
        boolean[] visited = new boolean[n+1];
        for(int i = 1; i <= n; i++){
            if(zone[i] == z){
                start = i;
                break;
            }
        }
        if(start == 0) return new int[]{-1, -1};
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        int count = 0;
        int val = 0;
        while(!queue.isEmpty()){
            int current = queue.poll();
            count++;
            val += value[current];
            for(int next : graph[current]){
                if(!visited[next] && zone[next] == z){
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        return new int[]{count, val};

    }

    static void operate(int index){
        if(index == n + 1){
            int[] zone1 = bfs(1);
            int[] zone2 = bfs(2);

            if(zone1[0] + zone2[0] == n){
                answer = Math.min(answer, Math.abs(zone1[1] - zone2[1]));
            }
        }
        else{
            zone[index] = 1;
            operate(index + 1);
            zone[index] = 2;
            operate(index + 1);
            zone[index] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        value = new int[n+1];
        graph = new ArrayList[n+1];
        zone = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            value[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList();
        }

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            for(int j = 0; j < k; j++){
                int a = Integer.parseInt(st.nextToken());
                graph[i].add(a);
                graph[a].add(i);
            }
        }

        operate(1);

        if(answer == Integer.MAX_VALUE) answer = -1;
        System.out.println(answer);


    }
}