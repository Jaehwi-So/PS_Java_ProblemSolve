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
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new int[]{e, s, w});
            graph[e].add(new int[]{s, e, w});
        }

        int[] D = new int[n+1];
        Arrays.fill(D, Integer.MAX_VALUE);
        boolean[] visited = new boolean[n+1];
        D[1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        pq.offer(new int[]{1, 1, 0});

        List<int[]> result = new ArrayList<>();
        while(!pq.isEmpty()){
            int[] current = pq.poll();
            if(!visited[current[0]]){
                visited[current[0]] = true;
                result.add(new int[]{current[1], current[0]});
                for(int[] next : graph[current[0]]){
                    if(D[next[0]] > D[current[0]] + next[2]){
                        D[next[0]] = D[current[0]] + next[2];
                        pq.offer(new int[]{next[0], next[1], D[next[0]]});
                    }
                }
            }
        }


//        System.out.println(Arrays.toString(D));
        StringBuilder sb = new StringBuilder();
        sb.append(result.size() - 1).append("\n");
        for(int i = 1; i < result.size(); i++){
            sb.append(result.get(i)[0]).append(" ").append(result.get(i)[1]).append("\n");
        }
        System.out.println(sb);
    }
}