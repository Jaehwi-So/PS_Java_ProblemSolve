import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point{
    int row;
    int col;
    public Point(int row, int col){
        this.row = row;
        this.col = col;
    }
}

public class Main {
    static short[][] array;
    static void visit(int row, int col){
        array[row][col] = 2;
    }
    static void dfs(Point s){
        int result = 0;
        Stack<Point> stack = new Stack<>();
        stack.push(s);
        while(!stack.isEmpty()){
            Point p = stack.pop();
            if(array[p.row][p.col] == 1){
                visit(p.row, p.col);
                result += 1;
                stack.push(new Point(p.row - 1, p.col));
                stack.push(new Point(p.row + 1, p.col));
                stack.push(new Point(p.row, p.col - 1));
                stack.push(new Point(p.row, p.col + 1));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for(int l = 0; l < t; l++){

            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            array = new short[n+2][m+2];

            for(int i = 0; i < k; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) + 1;
                int y = Integer.parseInt(st.nextToken()) + 1;
                array[y][x] = 1;
            }


            int count = 0;

            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= m; j++){
                    if(array[i][j] == 1){
                        dfs(new Point(i, j));
                        count++;
                    }
                }
            }

            sb.append(count).append("\n");

        }
        System.out.println(sb);

    }
}
