import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 1000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] D = new int[n+1][n+1];
        for(int[] line : D){
            Arrays.fill(line, MAX);
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            D[s][t] = 1;
        }

        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
                }
            }
        }

        int result = n;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i != j && D[i][j] == D[j][i]){
                    result--;
                    break;
                }
            }
        }
        System.out.println(result);
    }
}