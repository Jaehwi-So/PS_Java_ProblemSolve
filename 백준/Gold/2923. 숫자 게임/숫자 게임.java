import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] array1 = new int[101];
    static int[] array2 = new int[101];

    static void solve(){
        int[] arr1 = Arrays.copyOf(array1, array1.length);
        int[] arr2 = Arrays.copyOf(array2, array1.length);

        int a = 1;
        int b = 100;

        int max = 0;
        while(true){
            while(a <= 100 && arr1[a] == 0) a++;
            while(b >= 1 && arr2[b] == 0) b--;
            if(a > 100 || b < 1) break;

            max = Math.max(a + b, max);
            int min = Math.min(arr1[a], arr2[b]);
            arr1[a] -= min;
            arr2[b] -= min;
        }
        sb.append(max).append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            array1[Integer.parseInt(st.nextToken())]++;
            array2[Integer.parseInt(st.nextToken())]++;
            solve();
        }
        System.out.println(sb);
    }
}