import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        String[] array = new String[m];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            array[i] = st.nextToken();
        }

        Stack<String> stack = new Stack<>();
        for(int i = m-1; i >= 0; i--){
            if(!set.contains(array[i])){
                set.add(array[i]);
                stack.push(array[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            if(!stack.isEmpty()){
                sb.append(stack.pop()).append("\n");
            }
        }

        System.out.println(sb);
    }
}
