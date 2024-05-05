import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long max = Integer.MIN_VALUE;
        int[] data = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            data[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, data[i]);
        }

        long left = 0;
        long right = max + 1;

        while(left < right){
            long mid = (left + right) / 2;
            long value = 0;
            for(int i = 0; i < n; i++){
                value += Math.max(data[i] - mid, 0);
            }
            if(value >= m){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }

        System.out.println(left - 1);

    }
}
