import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static boolean[] isVisited;
    static StringBuilder sb = new StringBuilder();
    static void dfs(boolean[][] adj_mat, int start){
        sb.setLength(0);
        isVisited = new boolean[adj_mat.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        while(!stack.isEmpty()){
            int k = stack.pop();
            if(isVisited[k] == false){
                sb.append(k).append(" ");
                isVisited[k] = true;
                for(int i = adj_mat.length - 1; i >= 0; i--){
                    if(isVisited[i] == false && adj_mat[k][i] == true){
                        stack.push(i);
                    }
                }
            }
        }
        System.out.println(sb);
    }

    static void bfs(boolean[][] adj_mat, int start){
        sb.setLength(0);
        isVisited = new boolean[adj_mat.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        while(!queue.isEmpty()){
            int k = queue.poll();
            if(isVisited[k] == false){
                sb.append(k).append(" ");
                isVisited[k] = true;
                for(int i = 0; i < adj_mat.length; i++){
                    if(isVisited[i] == false && adj_mat[k][i] == true){
                        queue.offer(i);
                    }
                }
            }
        }
        System.out.println(sb);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        boolean[][] adj_mat = new boolean[n+1][n+1];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj_mat[a][b] = true;
            adj_mat[b][a] = true;
        }

        dfs(adj_mat, v);
        bfs(adj_mat, v);

    }
}
