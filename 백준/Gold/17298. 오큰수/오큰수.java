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
        Queue<Integer> queue = new LinkedList<>();
        for(int i = n - 1; i >= 0; i--){
            queue.clear();
            while(!stack.isEmpty()){
                int k = stack.pop();
                queue.add(k);
                if(k > array[i]){
                    result[i] = k;
                    break;
                }
            }
            if(result[i] != -1){
                while(!queue.isEmpty()){
                    stack.push(queue.poll());
                }
            }

            stack.push(array[i]);
        }


        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < n; i++){
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
    }
}
