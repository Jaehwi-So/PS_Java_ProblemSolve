import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] p;
    static void padoban(int k){

        p[1] = 1;
        p[2] = 1;
        p[3] = 1;

        for(int i = 4; i <= k; i++){
            p[i] = p[i-2] + p[i-3];
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        p = new long[101];
        int[] numbers = new int[n];
        int max = 0;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            numbers[i] = k;
            if(max < k){
                max = k;
            }
        }
        padoban(max);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            sb.append(p[numbers[i]]).append("\n");
        }
        System.out.println(sb);

    }
}