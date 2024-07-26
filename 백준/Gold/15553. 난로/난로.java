
import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] array = new int[n];
        Integer[] time = new Integer[n];

        st = new StringTokenizer(br.readLine());
        array[0] = Integer.parseInt(st.nextToken());
        time[0] = 0;

        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            array[i] = Integer.parseInt(st.nextToken());
            time[i] = array[i] - array[i-1] - 1;
        }

        int total = array[n-1] - array[0] + 1;
        Arrays.sort(time, Collections.reverseOrder());
        for(int i = 0; i < k - 1; i++){
            total -= time[i];
        }

        System.out.println(total);

    }
}