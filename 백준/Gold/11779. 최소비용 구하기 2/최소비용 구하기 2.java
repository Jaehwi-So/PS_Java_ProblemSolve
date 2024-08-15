import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge>{
    int index;
    int weight;
    String path;
    int count;

    public Edge(int index, int weight, String path, int count){
        this.index = index;
        this.weight = weight;
        this.path = path;
        this.count = count;
    }


    public int compareTo(Edge e){
        return this.weight - e.weight;
    }
}


public class Main {
    static int n;
    static int m;
    static List<Edge>[] graph;
    static int[] distance;
    static boolean[] visited;

    static Edge dijakstra(int start, int end){
        Edge s = new Edge(start, 0, Integer.toString(start), 1);
        distance[start] = 0;
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.offer(s);

        while(!queue.isEmpty()){
            Edge current = queue.poll();
            if(visited[current.index]) continue;

            if(current.index == end){
                return current;
            }
            visited[current.index] = true;

            for(Edge next : graph[current.index]){
                int nextDist = distance[current.index] + next.weight;
                if(!visited[next.index] && distance[next.index] > nextDist){
                    distance[next.index] = nextDist;
                    Edge e = new Edge(next.index, nextDist, current.path, current.count + 1);
                    e.path += " " + next.index;
                    queue.offer(e);
                }
            }
        }

        return null;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        graph = new LinkedList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new LinkedList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, w, "", 0));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        distance = new int[n+1];
        visited = new boolean[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        Edge result = dijakstra(start, end);
        System.out.println(result.weight);
        System.out.println(result.count);
        System.out.println(result.path);

    }
}
