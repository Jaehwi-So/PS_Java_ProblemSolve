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
            array[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(array);

        int a = 0;
        int b = n-1;

        long result = Long.MAX_VALUE;
        while(a < b){
            long sum = array[a] + array[b];
            if(Math.abs(sum) < Math.abs(result)){
                result = sum;
            }
            if(sum > 0) b--;
            else if(sum < 0) a++;
            else{
                break;
            }
        }
        System.out.println(result);

    }
}