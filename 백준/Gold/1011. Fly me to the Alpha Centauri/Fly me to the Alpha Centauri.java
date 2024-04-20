import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long calcStep(long distance){
        int step = 1;
        int recur = 0;
        long before = 1;
        long number = 1;
        if(distance == 1){
            return 1L;
        }
        while(true){
            if(recur == 2){
                step++;
                recur = 0;
            }
            long calc = before + step;
            if(distance < calc){
                return number;
            }
            number++;
            recur++;
            before = calc;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            sb.append(calcStep(y - x)).append("\n");
        }

        System.out.println(sb);

    }
}
