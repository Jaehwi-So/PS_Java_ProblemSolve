import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static char[][] matrix;
    static boolean[][] visited;
    static int[] dx = {1, 1, 1};
    static int[] dy = {1, 0, -1};
    static Stack<int[]> stack = new Stack<>();
    static int result = 0;

    static void dfs(int[] start){
        stack.push(start);
        while(!stack.isEmpty()){
            int[] current = stack.pop();
            if(!visited[current[0]][current[1]]){
                visited[current[0]][current[1]] = true;
                if(current[1] == m - 1){
                    result++;
                    break;
                }
                else{
                    for(int i = 0; i < 3; i++){
                        int row = current[0] + dy[i];
                        int col = current[1] + dx[i];
                        if(row < 0 || col < 0 || row >= n || col >= m){
                            continue;
                        }
                        if(matrix[row][col] == '.'){
                            stack.push(new int[]{row, col});
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new char[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            matrix[i] = st.nextToken().toCharArray();
        }

        for(int i = 0; i < n; i++){
            stack.setSize(0);
            dfs(new int[]{i, 0});
        }
        System.out.println(result);
    }
}