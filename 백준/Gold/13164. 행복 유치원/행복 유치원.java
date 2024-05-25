import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] array = new int[n];
        int[] diff = new int[n];
        array[0] = Integer.parseInt(st.nextToken());
        diff[0] = 0;
        for(int i = 1; i < n; i++){
            array[i] = Integer.parseInt(st.nextToken());
            diff[i] = array[i] - array[i-1];
        }

        Arrays.sort(diff);
        int result = 0;
        for(int i = 1; i <= n - k; i++){
            result += diff[i];
        }
        System.out.println(result);

    }
}