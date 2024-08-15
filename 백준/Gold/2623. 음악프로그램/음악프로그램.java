import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new LinkedList[n+1];
        int[] inDegree = new int[n+1];

        for(int i = 1; i <= n; i++){
            graph[i] = new LinkedList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int[] temp = new int[k];
            for(int j = 0; j < k; j++){
                temp[j] = Integer.parseInt(st.nextToken());
                for(int l = 0; l < j; l++){
                    if(!graph[temp[l]].contains(temp[j])){
                        graph[temp[l]].add(temp[j]);
                        inDegree[temp[j]]++;
                    }
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= n; i++){
            if(inDegree[i] == 0){
                queue.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        while(!queue.isEmpty()){
            int current = queue.poll();
            count++;
            sb.append(current).append("\n");
            for(int next : graph[current]){
                inDegree[next]--;
                if(inDegree[next] == 0){
                    queue.offer(next);
                }
            }
        }

        if(count != n){
            System.out.println(0);
        }
        else{
            System.out.println(sb);
        }


    }
}