import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] rgbs;

    static int[][] k;

    static int n;

    /** 113
     * 2 8 4*
     * 1* 7 3   1 1
     * 4 3* 6
     */

    // 6 11 10
    static void calc(){
        k[0][0] = rgbs[0][0];
        k[0][1] = rgbs[0][1];
        k[0][2] = rgbs[0][2];
        for(int i = 1; i < n; i++){
            k[i][0] = Math.min(k[i-1][1], k[i-1][2]) + rgbs[i][0];
            k[i][1] = Math.min(k[i-1][0], k[i-1][2]) + rgbs[i][1];
            k[i][2] = Math.min(k[i-1][0], k[i-1][1]) + rgbs[i][2];
        }

        System.out.println(Math.min(k[n-1][2], Math.min(k[n-1][0], k[n-1][1])));
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        rgbs = new int[n][3];
        k = new int[n][3];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            rgbs[i][0] = Integer.parseInt(st.nextToken());
            rgbs[i][1] = Integer.parseInt(st.nextToken());
            rgbs[i][2] = Integer.parseInt(st.nextToken());
        }

        calc();
    }
}
