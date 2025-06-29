import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int[] array = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<int[]> pq = new PriorityQueue(new Comparator<int[]>(){
            public int compare(int[] n1, int[] n2){
                return n1[0] - n2[0];
            }
        });

        int[] result = new int[n+1];
        for(int i = 1; i <= n; i++){
            pq.offer(new int[]{array[i], i});
            while(!pq.isEmpty() && pq.peek()[1] < i-l+1){
//                System.out.println(pq.peek()[0] + " " + pq.peek()[1]);
                pq.poll();
            }
            result[i] = pq.peek()[0];
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);


    }
}