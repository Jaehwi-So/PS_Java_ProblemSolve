import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int[] distance;
    static List<Integer>[] trace;

    static void dijakstra(int n, int start, List<int[]>[] graph){
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        Arrays.fill(distance, INF);
        for(int i = 0; i < n; i++){
            trace[i] = new ArrayList<>();
        }

        distance[start] = 0;

        pq.offer(new int[]{start, 0, -1});
        while(!pq.isEmpty()){
            int[] current = pq.poll();
            int index = current[0];
            int weight = current[1];
            for(int[] next : graph[index]){
                if(distance[next[0]] > weight + next[1]){
                    distance[next[0]] = weight + next[1];
                    trace[next[0]].clear();
                    trace[next[0]].add(index);
                    pq.offer(new int[]{next[0], distance[next[0]], index});
                }
                else if(distance[next[0]] == weight + next[1]){
                    trace[next[0]].add(index);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if(n == 0 && m == 0) break;

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            List<int[]>[] graph = new ArrayList[n];
            for(int i = 0; i < n; i++){
                graph[i] = new ArrayList();
            }

            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                graph[s].add(new int[]{e, w});
            }


            distance = new int[n];
            trace = new List[n];

            dijakstra(n, start, graph);

            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{end, -1});

            boolean[][] visited = new boolean[n][n];

//            System.out.println(Arrays.toString(distance));
//            for(int i = 0; i < n; i++){
//                System.out.println(trace[i]);
//            }

            while(!queue.isEmpty()){
                int[] edge = queue.poll();
                for(int k : trace[edge[0]]){
                    if(!visited[k][edge[0]]){
                        queue.offer(new int[]{k, edge[0]});
                        visited[k][edge[0]] = true;
                    }
                }

                if(edge[1] != -1){
                    for (int i = 0; i < graph[edge[0]].size(); i++) {
                        int[] node = graph[edge[0]].get(i);
                        if (node[0] == edge[1]) {
                            graph[edge[0]].remove(i);
                            break;
                        }
                    }
                }
            }

            dijakstra(n, start, graph);

//            System.out.println(Arrays.toString(distance));

            if(distance[end] == INF) distance[end] = -1;
            sb.append(distance[end]).append("\n");
        }
        System.out.println(sb);
    }
}