import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] matrix;
    static int[] dy = {-1, -1, 1, 1};
    static int[] dx = {-1, 1, -1, 1};
    static int[] max = new int[]{0, 0};

    static void push(int row, int col, Stack<int[]> stack){
        for(int i = 2; i < 4; i++){
            int r = row;
            int c = col;
            while(r >= 0 && c >= 0 && r < n && c < n){
                if(matrix[r][c] == 1){
                    matrix[r][c] = 2;
                    stack.push(new int[]{r, c});
                }
                r += dy[i];
                c += dx[i];
            }
        }
    }

    static void pop(Stack<int[]> stack){
        while(!stack.isEmpty()){
            int[] p = stack.pop();
            matrix[p[0]][p[1]] = 1;
        }
    }

    static void dfs(int step, int cnt, int odd){
        boolean hasNext = false;
        Stack<int[]> stack = new Stack<>();

        for(int i = step + 2; i < n * n; i += 2){
            int row = i / n;
            int col = i % n;
            if(matrix[row][col] == 1){
                hasNext = true;
                push(row, col, stack);
                dfs(i, cnt + 1, odd);
                pop(stack);
            }
        }


        if(hasNext == false){
            if(max[odd] < cnt){
                max[odd] = cnt;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        n = a;
        if(a % 2 == 0) n++; // 체스판 길이가 짝수일 경우 홀수로 만들어줌 (0으로 채움)
        matrix = new int[n][n];
        for(int i = 0; i < a; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < a; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 격자를 분리하여 각자 계산, 간섭 불가능
        dfs(-2, 0, 0);
        dfs(-1, 0, 1);
        System.out.println(max[0] + max[1]);

    }
}