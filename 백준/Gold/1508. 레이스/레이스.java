import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int k;
    static int[] array;
    static boolean[] trace;
    static boolean[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        array = new int[k];
        trace = new boolean[k];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = n + 1;

        while(left < right){
            int mid = (left + right) / 2;
            if(calc(mid)){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(boolean b : answer){
            if(b) sb.append(1);
            else sb.append(0);
        }
        System.out.println(sb);
    }


    static boolean calc(int dist){
        int before = array[0];
        int number = 1;
        Arrays.fill(trace, false);
        trace[0] = true;
        for(int i = 1; i < k; i++){
            if(array[i] - before >= dist){
                before = array[i];
                trace[i] = true;
                number++;
            }
            else{
                trace[i] = false;
            }
            if(number == m){
                answer = Arrays.copyOf(trace, k);
                return true;
            }
        }
        return false;
    }
}