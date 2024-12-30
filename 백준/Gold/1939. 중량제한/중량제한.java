import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<int[]>[] graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, w});
            graph[b].add(new int[]{a, w});
        }

        int[] D = new int[n+1];
        boolean[] visited = new boolean[n+1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        Arrays.fill(D, -1);
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        D[start] = Integer.MAX_VALUE;
        pq.offer(new int[]{start, Integer.MAX_VALUE});

        while(!pq.isEmpty()){
            int[] current = pq.poll();
            if(!visited[current[0]]){
                visited[current[0]] = true;
                for(int[] next : graph[current[0]]){
                    int min = Math.min(D[current[0]], next[1]);
                    if(min > D[next[0]]){
                        D[next[0]] = min;
                        pq.offer(next);
                    }
                }
            }

        }
        System.out.println(D[end]);
    }
}