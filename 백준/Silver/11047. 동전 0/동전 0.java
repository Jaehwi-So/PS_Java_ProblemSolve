import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Integer[] money = new Integer[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            money[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(money, Collections.reverseOrder());

        int count = 0;
        for(int m : money){
            count += k / m;
            k = k % m;
            if(k == 0){
                break;
            }
        }
        System.out.println(count);
    }
}