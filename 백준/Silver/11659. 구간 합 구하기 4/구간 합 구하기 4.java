import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        numbers = new long[n + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            numbers[i] = numbers[i-1] + Long.parseLong(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            long result = numbers[end] - numbers[start - 1];
            sb.append(result).append("\n");
        }

        System.out.println(sb);

    }
}
