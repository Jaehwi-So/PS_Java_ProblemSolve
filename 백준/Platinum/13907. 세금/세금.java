import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int k;
    static int s, d;
    static int[][] D; //D[A][B] = A개의 간선을 거쳐서 B로 갈 수 있는 최단 거리
    static int[] costs;
    static List<int[]>[] graph;
    static int[] min;


    //D 계산
    static void dijakstra(){
        Queue<int[]> queue = new LinkedList();
        queue.offer(new int[]{s, 0, 0});
        D[0][s] = 0;
        min[s] = 0;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            if(current[2] >= n) continue;
            if(D[current[2]][current[0]] < current[1]) continue;

            for(int[] next : graph[current[0]]){
                if(current[1] + next[1] < D[current[2] + 1][next[0]]){
                    if(min[next[0]] <= current[1] + next[1]){
                        continue;
                    }
                    D[current[2] + 1][next[0]] = current[1] + next[1];
                    min[next[0]] = current[1] + next[1];
                    queue.offer(new int[]{next[0], D[current[2] + 1][next[0]], current[2] + 1});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, w});
            graph[b].add(new int[]{a, w});
        }


        costs = new int[k+1];
        for(int i = 1; i <= k; i++){
            st = new StringTokenizer(br.readLine());
            costs[i] = Integer.parseInt(st.nextToken());
        }



        D = new int[n+1][n+1];
        for(int[] line : D){
            Arrays.fill(line, Integer.MAX_VALUE);
        }

        min = new int[n+1];
        Arrays.fill(min, Integer.MAX_VALUE);

        dijakstra(); //노드 별로 거치는 간선 개수에 따른 최단거리 계산

//        for(int[] line : D){
//            System.out.println(Arrays.toString(line));
//        }


        long append = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= k; i++){
            append += costs[i];
            long answer = Integer.MAX_VALUE;
            for(int j = 0; j <= n; j++){
                long distance = (j * append) + D[j][d];
                answer = Math.min(answer, distance);
            }
            sb.append(answer).append("\n");
        }

        System.out.println(sb);

    }
}
