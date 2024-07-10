import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long[] x = new long[n+1];
        long[] y = new long[n+1];
        long left = 0;
        long right = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        x[n] = x[0];
        y[n] = y[0];

        for (int i = 0; i < n; i++) {
            left += x[i] * y[i + 1];
            right += y[i] * x[i + 1];
        }

        double result = Math.abs(left - right) / 2.0;
        System.out.println(String.format("%.1f", result));
    }
}