import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static long[] distance;
    static int[] dp;
    static List<int[]>[] graph;
    static int search(int start){
        if(dp[start] == -1){
            int result = 0;
            for(int[] next : graph[start]){
                int nidx = next[0];
                if(distance[nidx] < distance[start]){
                    result += search(nidx);
                }
            }
            dp[start] = result;
        }
        return dp[start];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, d});
            graph[b].add(new int[]{a, d});
        }

        distance = new long[n+1];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[2] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                if(o1[1] > o2[1]) return 1;
                else if(o1[1] == o2[1]) return 0;
                else return-1;
            }
        });
        pq.offer(new long[]{2, 0});

        while(!pq.isEmpty()){
            long[] current = pq.poll();
            int idx = (int)current[0];
            if(distance[idx] < current[1]) continue;
            for(int[] next : graph[idx]){
                if(distance[next[0]] > distance[idx] + next[1]){
                    distance[next[0]] = distance[idx] + next[1];
                    pq.offer(new long[]{next[0], distance[next[0]]});
                }
            }
        }

//        System.out.println(Arrays.toString(distance));

        dp = new int[n+1];
        Arrays.fill(dp, -1);
        dp[2] = 1;

        System.out.println(search(1));


    }
}