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
        int[][] array = new int[2][5];
        int[][] temp = new int[2][5];
        Arrays.fill(array[0], 0);
        Arrays.fill(array[1], 0);
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());

            temp[0][0] = 0;
            temp[1][0] = 900001;
            for(int j = 1; j <= 3; j++){
                int k = Integer.parseInt(st.nextToken());
                temp[0][j] = k + Math.max(Math.max(array[0][j-1], array[0][j]), array[0][j+1]);
                temp[1][j] = k + Math.min(Math.min(array[1][j-1], array[1][j]), array[1][j+1]);
            }
            temp[0][4] = 0;
            temp[1][4] = 900001;

            for(int l = 0; l <= 1; l++){
                for(int j = 0; j < 5; j++){
                    array[l][j] = temp[l][j];
                }
            }

        }

//        System.out.println();
//        for(int k : array[0]){
//            System.out.print(k + " ");
//        }
//        System.out.println();
//        for(int k : array[1]){
//            System.out.print(k + " ");
//        }
//        System.out.println();


        int max = Integer.MIN_VALUE;
        for(int k : array[0]){
            max = Math.max(k, max);
        }
        int min = Integer.MAX_VALUE;
        for(int k : array[1]){
            min = Math.min(k, min);
        }
        System.out.println(max + " " + min);

    }
}