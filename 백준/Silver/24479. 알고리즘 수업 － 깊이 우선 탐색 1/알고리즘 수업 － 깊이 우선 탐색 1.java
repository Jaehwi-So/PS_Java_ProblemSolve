import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] visited;
    static void dfs(List<Integer>[] adj_list, int start){
        visited = new int[adj_list.length];
        int seq = 1;
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        while(!stack.isEmpty()){
            int k = stack.pop();
            if(visited[k] == 0) {
                visited[k] = seq++;
            }
            for(Integer i : adj_list[k]){
                if(visited[i] == 0){
                    stack.push(i);
                }
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        List<Integer>[] adj_list = new List[n+1];
        for(int i = 0; i <= n; i++){
            adj_list[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adj_list[start].add(end);
            adj_list[end].add(start);
        }

        for(int i = 0; i <= n; i++){
            Collections.sort(adj_list[i], Collections.reverseOrder());
        }

        dfs(adj_list, r);
        
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < visited.length; i++){
            sb.append(visited[i]).append("\n");
        }
        System.out.println(sb);
    }
}