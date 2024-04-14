import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] triangle;
    static int[][] solved;

    static void solve(){
        solved[0][1] = triangle[0][1];
        for(int i = 1; i < n; i++){
            for(int j = 1; j <= i + 1; j++){
                solved[i][j] = Math.max(solved[i-1][j-1], solved[i-1][j]) + triangle[i][j];
            }
        }
//        for(int[] line : solved){
//            for(int i : line){
//                System.out.print(i + " ");
//            }
//            System.out.println();
//        }

        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++){
            max = Math.max(max, solved[n-1][i]);
        }
        System.out.println(max);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        triangle = new int[n][n+2];
        solved = new int[n][n+2];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= i + 1; j++){
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
    }
}
