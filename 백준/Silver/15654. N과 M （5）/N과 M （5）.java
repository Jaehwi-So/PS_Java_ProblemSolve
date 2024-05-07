import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] array;
    static int n;
    static int m;
    static int temp[];
    static StringBuffer sb = new StringBuffer();

    static boolean isDuplicate(int step){
        for(int i = 0; i < step - 1; i++){
            for(int j = i + 1; j < step; j++){
                if(temp[i] == temp[j]){
                    return true;
                }
            }
        }
        return false;
    }
    static void backtrack(int step){
        if(isDuplicate(step) == false){
            if(step == m){
                for(int i = 0; i < step; i++){
                    sb.append(temp[i]).append(" ");
                }
                sb.append("\n");
//                System.out.println(Arrays.toString(temp));
            }
            else{
                for(int i = 0; i < n; i++){
                    temp[step] = array[i];
                    backtrack(step + 1);
                    temp[step] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        array = new int[n];
        temp = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);
        backtrack(0);
        System.out.println(sb);

    }
}
