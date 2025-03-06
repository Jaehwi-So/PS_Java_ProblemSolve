import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] array;
    static final int MAX = 1000000000;

    static boolean calc(long time){
        int block = 0;
        for(int i = 0; i < n; i++){
            int b = (int)Math.ceil((double)array[i] / time);

            if(b <= 1.0){
                block++;
            }
            else{
                block -= (b - 2);
            }
        }

        if(block > 0) return true;
        else return false;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        array = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(array);

        long start = 1;
        long end = MAX;

        while(start < end){
            long mid = (start + end) / 2;
            boolean b = calc(mid);
            if(b) end = mid;
            else start = mid + 1;
        }
        System.out.println(end);

    }
}