import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int k;
    static int MAX = 2000000;
    static int[] trace;

    static void bfs(){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        trace[n] = 0;


        while(!queue.isEmpty()){
            int current = queue.poll();

            if(current == k) return;
            if(current - 1 >= 0 && trace[current - 1] == -1){
                trace[current - 1] = current;
                queue.offer(current - 1);
            }
            if(current + 1 <= MAX && trace[current + 1] == -1){
                trace[current + 1] = current;
                queue.offer(current + 1);
            }
            if(current * 2 <= MAX && trace[current * 2] == -1){
                trace[current * 2] = current;
                queue.offer(current * 2);
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        trace = new int[MAX+1];
        Arrays.fill(trace, -1);
        bfs();

        StringBuilder sb = new StringBuilder();
        int current = k;
//        System.out.println(Arrays.toString(trace));
        sb.insert(0, current);
        int count = 0;
        while(current != n){
            sb.insert(0, trace[current] + " ");
            current = trace[current];
            count++;
        }

        System.out.println(count);
        System.out.println(sb);

    }
}