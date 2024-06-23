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
        int[] array = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int start = 0;
        int end = 0;
        int result = Integer.MAX_VALUE;
        while(start <= end && end <= n) {
            if(sum < m){
                sum += array[end++];
            }
            else{
                result = Math.min(result, end-start);
                sum -= array[start++];
            }

        }

        if(result == Integer.MAX_VALUE){
            System.out.println(0);
        }
        else{
            System.out.println(result);
        }

    }
}
