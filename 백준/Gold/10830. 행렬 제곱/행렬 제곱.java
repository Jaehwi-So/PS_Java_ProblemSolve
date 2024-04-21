import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static long[][] A;
    static long[][] calc(long[][] a, long[][] b){
        long[][] R = new long[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                long sum = 0;
                for(int l = 0; l < n; l++){
                    sum += (a[i][l] * b[l][j]) % 1000;
                }
                R[i][j] = sum % 1000;
            }
        }

        return R;
    }
    static long[][] func(long b){
        if(b == 1){
            return A;
        }
        else if(b == 2){
            return calc(A, A);
        }
        long[][] t = func(b / 2);
        if(b % 2 == 0){
             return calc(t, t);
        }
        else{
            return calc(A, calc(t, t));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        A = new long[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                A[i][j] = Long.parseLong(st.nextToken()) % 1000;
            }
        }

        long[][] R = func(b);


        StringBuilder sb = new StringBuilder();
        for(long[] line : R){
            for(long i : line){
                sb.append(i).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
