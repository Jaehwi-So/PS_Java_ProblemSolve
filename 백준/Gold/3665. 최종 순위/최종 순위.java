import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int test = Integer.parseInt(st.nextToken());

        StringBuilder resultSb = new StringBuilder();
        for(int t = 0 ; t < test; t++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            List<Integer>[] graph = new LinkedList[n+1];
            st = new StringTokenizer(br.readLine());
            int[] before = new int[n+1];
            for(int i = 1; i <= n; i++){
                before[i] = Integer.parseInt(st.nextToken());
                graph[i] = new LinkedList<>();
            }

            int[] D = new int[n+1];
            for(int i = 1; i <= n - 1; i++){
                for(int j = i + 1; j <= n; j++){
                    graph[before[i]].add(before[j]);
                    D[before[j]]++;
                }
            }

            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());

            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                Integer a = Integer.parseInt(st.nextToken());
                Integer b = Integer.parseInt(st.nextToken());
                if(graph[a].contains(b)){
                    graph[a].remove(b);
                    graph[b].add(a);
                    D[a]++;
                    D[b]--;
                }
                else if(graph[b].contains(a)){
                    graph[b].remove(a);
                    graph[a].add(b);
                    D[a]--;
                    D[b]++;
                }
            }

//            for(List l : graph){
//                System.out.println(l);
//            }
//            System.out.println();

            Queue<Integer> queue = new LinkedList<>();
            boolean notFix = false;
            for(int i = 1; i <= n; i++){
                if(D[i] == 0){
                    queue.offer(i);
                }
            }

            int count = 0;
            StringBuilder sb = new StringBuilder();
            while(!queue.isEmpty()){
                if(queue.size() > 1){
                    notFix = true;
                }
                int current = queue.poll();
                sb.append(current).append(" ");
                count++;
                for(int next : graph[current]){
                    D[next]--;
                    if(D[next] == 0){
                        queue.offer(next);
                    }
                }
            }

            if(count < n){
                sb.setLength(0);
                sb.append("IMPOSSIBLE").append("\n");
            }
            else if(notFix){
                sb.setLength(0);
                sb.append("?").append("\n");
            }
            else{
                sb.append("\n");
            }
            resultSb.append(sb.toString());

        }

        System.out.println(resultSb);

    }
}
