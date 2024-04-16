import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static long numbers[];

    static long max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        numbers = new long[n + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            numbers[i] = numbers[i-1] + Long.parseLong(st.nextToken());
//            System.out.print(numbers[i] + " ");
        }

        for(int j = m; j <= n; j++){
            long v = numbers[j] - numbers[j - m];
            max = Math.max(v, max);
        }

        System.out.println(max);


    }
}
