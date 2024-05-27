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
    static int dfs(Point s){
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
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        array = new short[n+2][n+2];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            char[] chs = st.nextToken().toCharArray();
            for(int j = 1; j <= n; j++){
                array[i][j] = (short) (chs[j-1] - '0');
            }
        }

        StringBuilder sb = new StringBuilder();
        List<Integer> results = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(array[i][j] == 1){
                    int result = dfs(new Point(i, j));
                    results.add(result);
                }
            }
        }
        System.out.println(results.size());
        Collections.sort(results);
        for(Integer i : results){
            sb.append(i).append("\n");
        }
        System.out.println(sb);
    }
}