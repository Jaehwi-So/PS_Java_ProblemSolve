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
        long[] array = new long[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            array[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(array);

        long min = Long.MAX_VALUE;
        long a = 0;
        long b = 0;

        int left = 0;
        int right = n - 1;

        while(left < right){
            long current = Math.abs(array[left] + array[right]);
            if(current <= min){
                min = current;
                a = array[left];
                b = array[right];
            }
            if(array[right] + array[left] > 0){
                right--;
            }
            else if(array[right] + array[left] < 0){
                left++;
            }
            else{
                break;
            }
        }

        System.out.println(a + " " + b);
    }
}