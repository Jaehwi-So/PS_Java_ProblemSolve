import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Integer>[] graph = new ArrayList[n+1];
        int[] D = new int[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            D[b]++;
        }


        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[n+1];
        for(int i = 1; i <= n; i++){
            if(D[i] == 0){
                result[i] = 1;
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()){
            int current = queue.poll();
            for(int next : graph[current]){
                D[next]--;
                if(D[next] == 0){
                    queue.offer(next);
                    result[next] = result[current] + 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);

    }
}