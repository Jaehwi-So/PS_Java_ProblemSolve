import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{
    int index;
    int weight;

    public Edge(int index, int weight){
        this.index = index;
        this.weight = weight;
    }

    public int compareTo(Edge e){
        return e.weight - this.weight;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        List<int[]>[] graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[start].add(new int[]{end, weight});
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> queue = new PriorityQueue<>();

        int[] D = new int[n+1];
        queue.offer(new Edge(start, 0));

        List<Integer>[] traces = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            traces[i] = new ArrayList<>();
        }

        while(!queue.isEmpty()){
            Edge current = queue.poll();
            if(current.weight < D[current.index]) continue;

            for(int[] next : graph[current.index]){
                int weight = D[current.index] + next[1];
                if(D[next[0]] <= weight){
                    if(D[next[0]] < weight){
                        traces[next[0]].clear();
                        queue.offer(new Edge(next[0], weight));
                        D[next[0]] = weight;
                    }
                    traces[next[0]].add(current.index);
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[][] path = new boolean[n+1][n+1];
        boolean[] visited = new boolean[n+1];
        for(Integer idx : traces[end]){
            q.offer(idx);
            path[idx][end] = true;
            visited[idx] = true;
        }

        while(!q.isEmpty()){
            int current = q.poll();
            if(current == start){
                continue;
            }
            for(Integer before : traces[current]){
                if(before == -1) continue;
                if(!path[before][current]){
                    if(!visited[before]){
                        q.offer(before);
                        visited[before] = true;
                    }
                    path[before][current] = true;
                }
            }
        }

//        for(List trace : traces){
//            System.out.println(trace);
//        }
        int result = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(path[i][j]){
//                    System.out.println(i + " " + j);
                    result++;
                }
            }
        }

        System.out.println(D[end]);
        System.out.println(result);

    }
}