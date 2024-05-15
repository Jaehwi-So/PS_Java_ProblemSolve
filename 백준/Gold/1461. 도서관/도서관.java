import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> nq = new PriorityQueue<>(Collections.reverseOrder());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int k = Integer.parseInt(st.nextToken());
            if(k > 0){
                pq.offer(k);
            }
            else{
                nq.offer(Math.abs(k));
            }
        }
        pq.offer(0);
        nq.offer(0);
        int result = 0;

        if(pq.peek() > nq.peek()){
            int roop = 0;
            result += pq.peek();
            while(!pq.isEmpty() && roop < m){
                pq.poll();
                roop++;
            }
        }
        else{
            int roop = 0;
            result += nq.peek();
            while(!nq.isEmpty() && roop < m){
                nq.poll();
                roop++;
            }
        }
        while(!pq.isEmpty()){
            int roop = 0;
            result += pq.peek() * 2;
            while(!pq.isEmpty() && roop < m){
                pq.poll();
                roop++;
            }
        }

        while(!nq.isEmpty()){
            int roop = 0;
            result += nq.peek() * 2;
            while(!nq.isEmpty() && roop < m){
                nq.poll();
                roop++;
            }
        }

        System.out.println(result);

    }
}