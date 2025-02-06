import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int test = Integer.parseInt(st.nextToken());

        for(int t = 0; t < test; t++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            List<int[]>[] graph = new ArrayList[n+1];
            for(int i = 1; i <= n; i++) graph[i] = new ArrayList();
            for(int i = 0; i < d; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                graph[b].add(new int[]{a, time});
            }

            int[] distance = new int[n+1];
            Arrays.fill(distance, Integer.MAX_VALUE);

            PriorityQueue<int[]> pq = new PriorityQueue(new Comparator<int[]>() {
                @Override
                public int compare(int[] a1, int[] a2) {
                    return a1[1] - a2[1];
                }
            });

            pq.offer(new int[]{c, 0});
            distance[c] = 0;

            while(!pq.isEmpty()){
                int[] current = pq.poll();
                if(distance[current[0]] < current[1]) continue;
                for(int[] next : graph[current[0]]){
                    if(distance[next[0]] > distance[current[0]] + next[1]){
                        distance[next[0]] = distance[current[0]] + next[1];
                        pq.offer(new int[]{next[0], distance[next[0]]});
                    }
                }
            }

            int max = 0;
            int cnt = 0;
            for(int i = 1; i <= n; i++){
                if(distance[i] != Integer.MAX_VALUE){
                    max = Math.max(max, distance[i]);
                    cnt++;
                }
            }
           sb.append(cnt).append(" ").append(max).append("\n");
        }

        System.out.println(sb);

    }
}