import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] D = new int[n+1];
        List<Integer>[] graph = new ArrayList[n+1];

        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
            D[a]++;
            D[b]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] isSocial = new boolean[n+1];

        for(int i = 1; i <= n; i++){
            if(D[i] == 1){
                queue.offer(i);
            }
        }

        int count = 0;
        while(!queue.isEmpty()){
            int current = queue.poll();
            D[current] = 0;

            for(int next : graph[current]){
                D[next]--;
                if(!isSocial[next] && !isSocial[current]){
                    isSocial[next] = true;
                    count++;
                }
                if(D[next] == 1){
                    queue.offer(next);
                }
            }
        }

        System.out.println(count);


    }
}