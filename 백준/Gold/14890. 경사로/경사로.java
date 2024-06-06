import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int l;
    static int[][] array;
    static int[] dy = {0, 1};
    static int[] dx = {1, 0};

    static Stack<Integer> stack = new Stack<>();

    static int calc(int row, int col, int d){
        stack.setSize(0);
        stack.push(array[row][col]);
        int before = array[row][col];
        row += dy[d];
        col += dx[d];
        while(row < n && col < n){
            int current = array[row][col];
            if(Math.abs(before - current) > 1){
                return 0;
            }
            //앞으로가 더 작은 경우
            else if(before > current){
                for(int i = 1; i < l; i++){
                    row += dy[d];
                    col += dx[d];
                    if(row >= n || col >= n){
                        return 0;
                    }
                    if(array[row][col] != current){
                        return 0;
                    }
                }
            }
            //앞으로가 더 큰 경우
            else if(current > before){
                for(int i = 0; i < l; i++){
                    if(!stack.isEmpty()){
                        int k = stack.pop();
                        if(k != before){
                            return 0;
                        }
                    }
                    else{
                        return 0;
                    }

                }
                stack.setSize(0);
                stack.push(current);
            }
            else{
                stack.push(current);
            }

            before = array[row][col];
            row += dy[d];
            col += dx[d];

        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        array = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        for(int i = 0; i < n; i++){
            result += calc(i, 0, 0);
            result += calc(0, i, 1);
        }

        System.out.println(result);

    }
}
