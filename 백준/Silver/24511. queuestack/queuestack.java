import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        Deque queue = new ArrayDeque();
        int n = Integer.parseInt(st.nextToken());


        int[] types = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            types[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int a = Integer.parseInt(st.nextToken());
            if(types[i] == 0){
                queue.addFirst(a);
            }
        }

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            queue.addLast(Integer.parseInt(st.nextToken()));
            sb.append(queue.pollFirst()).append(" ");
        }

        System.out.println(sb);
    }
}
