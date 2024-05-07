import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] array;
    static int n;
    static int m;
    static int temp[];
    static StringBuffer sb = new StringBuffer();
    static StringBuffer tb = new StringBuffer();
    static ArrayList<String> result = new ArrayList<>();

    static boolean isDecrease(int step){
        for(int i = 0; i < step - 1; i++){
            for(int j = i + 1; j < step; j++){
                if(temp[i] > temp[j]){
                    return true;
                }
            }
        }
        return false;
    }

    static void add(String s){
        for(int i = 0; i < result.size(); i++){
            if(s.equals(result.get(i))){
                return;
            }
        }
        sb.append(s).append("\n");
        result.add(s);
    }
    static void backtrack(int step){
        if(isDecrease(step) == false){
            if(step == m){
                tb.setLength(0);
                for(int i = 0; i < step; i++){
                    tb.append(temp[i]).append(" ");
                }
                add(tb.toString());
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
