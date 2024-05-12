import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] array = new int[n];
        int[] result = new int[n];
        Arrays.fill(result, -1);

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < n; i++){
            while(!stack.isEmpty()){
                if(array[stack.peek()] >= array[i]){
                    break;
                }
                result[stack.pop()] = array[i];
            }
            stack.push(i);
        }


        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < n; i++){
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
    }
}
