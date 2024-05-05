import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long max = Integer.MIN_VALUE;
        int[] data = new int[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            data[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, data[i]);
        }

        long left = 0;
        long right = max + 1;

        while(left < right){
            long mid = (left + right) / 2;
            long value = 0;
            for(int i = 0; i < n; i++){
                value += (data[i] / mid);
            }
            if(value >= k){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }

        System.out.println(right - 1);

    }
}