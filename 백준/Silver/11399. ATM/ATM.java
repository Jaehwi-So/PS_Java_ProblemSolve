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
        int[] array = new int[n+1];
        int sum = 0;

        Arrays.sort(array);

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);

        for(int i = 1; i <= n; i++){
            array[i] += array[i-1];
            sum += array[i];
        }

        System.out.println(sum);
    }
}
