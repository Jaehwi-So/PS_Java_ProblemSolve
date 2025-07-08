import java.io.*;
import java.util.*;

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

        int left = 0;
        int right = n - 1;
        long min = Integer.MAX_VALUE;
        long[] result = new long[2];

        while(left < right){
            long current = array[left] + array[right];
            if(min > Math.abs(current)){
                min = Math.abs(current);
                result[0] = array[left];
                result[1] = array[right];
            }
            if(current == 0){
                break;
            }
            else if(current > 0){
                right--;
            }
            else{
                left++;
            }
        }

        System.out.println(result[0] + " " + result[1]);

    }
}