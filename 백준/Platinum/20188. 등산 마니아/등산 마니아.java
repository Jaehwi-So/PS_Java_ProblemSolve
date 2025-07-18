import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<Integer>[] graph;
    static int[] subtree;
    static boolean[] visited;

    static void dfs(int index){
        int size = 1;
        visited[index] = true;
        for(int next : graph[index]){
            if(!visited[next]){
                dfs(next);
                size += subtree[next];
            }
        }
        subtree[index] = size;
    }

    static long combination(int n){
        return ((long)n * (n-1)) / 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];
        visited = new boolean[n+1];
        subtree = new int[n+1];

        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList();
        }

        for(int i = 0; i < n - 1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

//        visited[1] = true;
        dfs(1);
        long result = 0;
        long pair = combination(n);   //7 6 5 4 3 2 1   8C2 - 5C2 = 28 - 10
        for(int i = 2; i <= n; i++){
            int existPair = n - subtree[i];
            long calc = pair - combination(existPair);
            result += calc;
        }
        System.out.println(result);
    }
}