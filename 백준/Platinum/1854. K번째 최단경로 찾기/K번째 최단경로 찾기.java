import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<int[]>[] graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, c});
        }



        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        pq.offer(new int[]{1, 0});

        PriorityQueue<Integer>[] result = new PriorityQueue[n+1];
        for(int i = 1; i <= n; i++){
            result[i] = new PriorityQueue<>(Collections.reverseOrder());
        }
        result[1].offer(0);
        while(!pq.isEmpty()){
            int[] current = pq.poll();
//            System.out.println(current[0] + " : " + current[1]);
            for(int[] next : graph[current[0]]){
                int dist = current[1] + next[1];
                if(result[next[0]].size() < k){
                    result[next[0]].offer(dist);
                    pq.offer(new int[]{next[0], dist});
                }
                else if(result[next[0]].peek() > dist){
                    result[next[0]].poll();
                    result[next[0]].offer(dist);
                    pq.offer(new int[]{next[0], dist});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            if(result[i].size() == k){
                sb.append(result[i].poll()).append("\n");
            }
            else{
                sb.append(-1).append("\n");
            }

        }
        System.out.println(sb);
    }
}