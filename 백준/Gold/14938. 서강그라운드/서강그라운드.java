import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int INF = 10000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int[] items = new int[n+1];
        int[][] D = new int[n+1][n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            items[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(D[i], INF);
            D[i][i] = 0;
        }

        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            D[start][end] = weight;
            D[end][start] = weight;
        }

        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    int weight = D[i][k] + D[k][j];
                    if(D[i][j] > weight){
                        D[i][j] = weight;
                    }
                }
            }
        }

        int max = 0;
        for(int i = 1; i <= n; i++){
            int sum = 0;
            for(int j = 1; j <= n; j++){
                if(D[i][j] <= m){
                    sum += items[j];
                }
            }
            max = Math.max(sum, max);
        }

//        for(int[] line : D){
//            System.out.println(Arrays.toString(line));
//        }
        System.out.println(max);
    }
}
