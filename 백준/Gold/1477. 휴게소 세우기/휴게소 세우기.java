import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int l;
    static int[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        array = new int[n+2];
        st = new StringTokenizer(br.readLine());
        array[0] = 0;
        array[1] = l;
        for(int i = 2; i <= n + 1; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(array);

        for(int i = 1; i <= l; i++){
            int c = calc(i);
            if(c <= m){
                System.out.println(i);
                break;
            }
        }
    }

    static int calc(int length){
        int result = 0;
        for(int i = 0; i <= n; i++){
            int distance = array[i+1] - array[i];
            if(distance < length){
                continue;
            }
            int c = (int)Math.ceil((double)distance / length) - 1;
            result += c;
        }
        return result;
    }

    // 0 ~ 100
    // 100 ~ 200
}
