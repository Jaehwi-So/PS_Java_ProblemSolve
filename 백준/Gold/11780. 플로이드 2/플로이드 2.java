import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int INF = 100000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        int[][] matrix = new int[n+1][n+1];
        String[][] str = new String[n+1][n+1];
        for(int i = 1; i <= n; i++){
            Arrays.fill(matrix[i], INF);
            Arrays.fill(str[i], "");
            matrix[i][i] = 0;
        }

        for(int j = 0; j < m; j++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            matrix[s][e] = Math.min(matrix[s][e], w);
            str[s][e] = Integer.toString(e);
        }

        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    if(matrix[i][j] > matrix[i][k] + matrix[k][j]){
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                        str[i][j] = str[i][k] + " " + str[k][j];
                    }

                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(matrix[i][j] == INF){
                    matrix[i][j] = 0;
                }
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(str[i][j].length() == 0){
                    sb.append(0).append("\n");
                }
                else{
                    String[] s = str[i][j].split(" ");
                    sb.append(s.length + 1).append(" ").append(i).append(" ").append(str[i][j]).append("\n");
                }
            }
        }
        System.out.println(sb);


    }
}
