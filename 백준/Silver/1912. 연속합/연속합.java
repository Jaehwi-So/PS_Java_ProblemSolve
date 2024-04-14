import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] p;
    static int[] numbers;
    static int n;

    static int max = Integer.MIN_VALUE;



    static void run(){
        p[0] = 0;
        for(int i = 1; i <= n; i++){
            p[i] = Math.max(numbers[i], p[i - 1] + numbers[i]);
            max = Math.max(p[i], max);
        }
        System.out.println(max);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        p = new int[n + 1];
        numbers = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            int k = Integer.parseInt(st.nextToken());
            numbers[i] = k;
        }
        run();
    }
}