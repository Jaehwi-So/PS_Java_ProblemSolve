import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] array;
    static int find(int x){
        if(x == array[x]){
            return x;
        }

        array[x] = find(array[x]);
        return array[x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        array = new int[n+1];
        for(int i = 1; i <= n; i++){
            array[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int result = 0;
        for(int i = 1; i <= p; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int index = find(k);
            if(index == 0){
                break;
            }
            result++;
            array[index] = find(index - 1);
        }

        System.out.println(result);

    }
}
