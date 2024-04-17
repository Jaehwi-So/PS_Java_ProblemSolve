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
        long[] numbers = new long[n + 1];
        long[] mods = new long[m];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            int mod = (int)(numbers[i-1] + Long.parseLong(st.nextToken())) % m;
            mods[mod] += 1;
            numbers[i] = mod;
        }

        long count = mods[0];

        for(int i = 0; i < m; i++){ //구간 1~5
            count += (mods[i] * (mods[i] - 1)) / 2;
        }

        System.out.println(count);

    }
}