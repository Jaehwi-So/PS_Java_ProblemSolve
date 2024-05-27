import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static boolean[] isVisited;
    static void dfs(boolean[][] adj_mat){
        int start = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        while(!stack.isEmpty()){
            int k = stack.pop();
            if(isVisited[k] == false){
                isVisited[k] = true;
                for(int i = 0; i < adj_mat.length; i++){
                    if(isVisited[i] == false && adj_mat[k][i] == true){
                        stack.push(i);
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] adj_mat = new boolean[n][n];
        isVisited = new boolean[n];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adj_mat[a][b] = true;
            adj_mat[b][a] = true;
        }

        dfs(adj_mat);
        int count = -1;
        for(boolean b : isVisited){
            if(b){
                count++;
            }
        }
        System.out.println(count);
    }
}
