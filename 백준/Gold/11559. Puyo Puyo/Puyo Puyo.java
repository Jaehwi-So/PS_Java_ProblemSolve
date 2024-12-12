import java.io.*;
import java.util.*;

public class Main {
    static char[][] matrix;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static void replace(){
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 6; j++){
                if(matrix[i][j] == '-'){
                    for(int k = i; k > 0; k--){
                        matrix[k][j] = matrix[k-1][j];
                    }
                    matrix[0][j] = '.';
                }
            }
        }
    }

    static boolean bfs(int srow, int scol){
        Stack<int[]> stack = new Stack<>();
        Queue<int[]> queue = new LinkedList<>();
        char color = matrix[srow][scol];
        matrix[srow][scol] = '-';
        queue.offer(new int[]{srow, scol});

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            stack.push(current);

            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row >= 12 || col >= 6 || row < 0 || col < 0){
                    continue;
                }
                if(matrix[row][col] == color){
                    queue.offer(new int[]{row, col});
                    matrix[row][col] = '-';
                }
            }
        }

        if(stack.size() < 4){
            while(!stack.isEmpty()){
                int[] point = stack.pop();
                matrix[point[0]][point[1]] = color;
            }
            return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        matrix = new char[12][6];
        for(int i = 0; i < 12; i++){
            matrix[i] = br.readLine().toCharArray();
        }

        int result = 0;
        while(true){
            boolean exit = true;
            for(int i = 0; i < 12; i++){
                for(int j = 0; j < 6; j++){
                    if(matrix[i][j] != '.' && matrix[i][j] != '-'){
                        boolean success = bfs(i, j);
                        if(success){
                            exit = false;
                        }
                    }
                }
            }
            if(exit){
                break;
            }
            else{
                result++;
                replace();
            }
        }

        System.out.println(result);

    }
}