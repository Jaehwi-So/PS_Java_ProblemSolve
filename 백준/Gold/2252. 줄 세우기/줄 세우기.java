import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] D = new int[n+1];
        List<Integer>[] graph = new LinkedList[n+1];

        for(int i = 1; i <= n; i++){
            graph[i] = new LinkedList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            D[b]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= n; i++){
            if(D[i] == 0){
                queue.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
            int current = queue.poll();
            sb.append(current).append(" ");
            for(int next : graph[current]){
                D[next]--;
                if(D[next] == 0){
                    queue.offer(next);
                }
            }
        }

        System.out.println(sb);
    }
}