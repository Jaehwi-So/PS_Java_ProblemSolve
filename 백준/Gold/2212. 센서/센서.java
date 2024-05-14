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
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] sensors = new int[n];
        int[] temp = new int[n];
        for(int i = 0; i < n; i++){
            sensors[i] = Integer.parseInt(st.nextToken());

        }
        Arrays.sort(sensors);

        temp[0] = 0;
        for(int i = 1; i < n; i++){
            temp[i] = sensors[i] - sensors[i-1];
        }
        Arrays.sort(temp);

        int result = 0;
        for(int i = 1; i < n - k + 1; i++){
           result += temp[i];
        }



//        System.out.println(Arrays.toString(sensors));
//        System.out.println(Arrays.toString(temp));
        System.out.println(result);



    }
}