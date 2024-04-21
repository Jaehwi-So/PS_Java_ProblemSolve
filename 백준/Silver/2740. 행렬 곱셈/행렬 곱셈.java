import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] A = new int[n][m];



        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] B = new int[m][k];

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < k; j++){
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] R = new int[n][k];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < k; j++){
                int sum = 0;
                for(int l = 0; l < m; l++){
                    sum += A[i][l] * B[l][j];
                }
                R[i][j] = sum;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int[] line : R){
           for(int i : line){
               sb.append(i).append(" ");
           }
           sb.append("\n");
        }
        System.out.println(sb);
    }
}