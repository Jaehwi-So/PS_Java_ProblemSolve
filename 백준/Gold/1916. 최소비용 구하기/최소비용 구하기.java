import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        List<int[]>[] graph = new List[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new LinkedList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, cost});
        }

        int[] distance = new int[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                return arr1[1] - arr2[1];
            }
        });

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        distance[start] = 0;

        queue.offer(new int[]{start, 0});

        while(!queue.isEmpty()){
            int[] current = queue.poll();

            if(!visited[current[0]]){
                visited[current[0]] = true;

                for(int[] next : graph[current[0]]){
                    if(!visited[next[0]] && distance[current[0]] + next[1] < distance[next[0]]){
                        distance[next[0]] = distance[current[0]] + next[1];
                        queue.offer(new int[]{next[0], distance[next[0]]});
                    }
                }
            }

        }

        System.out.println(distance[end]);


    }
}