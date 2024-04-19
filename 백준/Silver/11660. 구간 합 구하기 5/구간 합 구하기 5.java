import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        array = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0){
                    array[i][j] = Integer.parseInt(st.nextToken());
                }
                else if(j == 0){
                    array[i][j] = array[i-1][n-1] + Integer.parseInt(st.nextToken());
                }
                else{
                    array[i][j] = array[i][j-1] + Integer.parseInt(st.nextToken());
                }
            }
        }

//        for(int[] l : array){
//            System.out.println(Arrays.toString(l));
//        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            int sum = 0;
            for(int j = y1 - 1; j <= y2 - 1; j++){
                int s;
                if(x1 == 1 && j == 0){
                    s = 0;
                }
                else if(x1 == 1){
                    s = array[j-1][n-1];
                }
                else{
                    s = array[j][x1 - 2];
                }
                sum += array[j][x2 - 1] - s;
            }
           sb.append(sum).append("\n");

        }

        System.out.println(sb);

    }
}
