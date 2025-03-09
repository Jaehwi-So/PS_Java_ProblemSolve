import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[][] distance = new long[n+1][k+1];
        List<long[]>[] graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[a].add(new long[]{b, d});
            graph[b].add(new long[]{a, d});
        }

        for(long[] line : distance){
            Arrays.fill(line, Long.MAX_VALUE);
        }


        PriorityQueue<long[]> pq = new PriorityQueue<>(new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                if(o1[1] > o2[1]) return 1;
                else if(o1[1] < o2[1]) return -1;
                else return 0;
            }
        });
        pq.offer(new long[]{1, 0, 0});
        distance[1][0] = 0;

        while(!pq.isEmpty()){
            long[] current = pq.poll();
            int cidx = (int)current[0];
            int cK = (int)current[2];

            if(distance[cidx][cK] < current[1]) continue;

            for(long[] next : graph[cidx]){
                int nidx = (int)next[0];
                if(distance[cidx][cK] + next[1] < distance[nidx][cK]){
                    distance[nidx][cK] = distance[cidx][cK] + next[1];
                    pq.offer(new long[]{nidx, distance[nidx][cK], cK});
                }
                if(cK < k){
                    if(distance[cidx][cK] < distance[nidx][cK + 1]){
                        distance[nidx][cK + 1] = distance[cidx][cK];
                        pq.offer(new long[]{nidx, distance[nidx][cK + 1], cK + 1});
                    }
                }
            }
        }

        long result = Long.MAX_VALUE;
        for(int i = 0; i <= k; i++){
            result = Math.min(result, distance[n][i]);
        }
        System.out.println(result);

    }
}