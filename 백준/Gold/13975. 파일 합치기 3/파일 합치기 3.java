import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int l = 0; l < t; l++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            PriorityQueue<Long> queue = new PriorityQueue<>(new Comparator<Long>(){
                @Override
                public int compare(Long i1, Long i2){
                    if(i1 - i2 > 0){
                        return 1;
                    }
                    else{
                        return -1;
                    }
                }
            });
            for(int i = 0; i < k; i++){
                queue.offer(Long.parseLong(st.nextToken()));
            }
            long cost = 0;
            while(queue.size() > 1){
                long i1 = queue.poll();
                long i2 = queue.poll();
                cost += (i1 + i2);
                queue.offer(i1 + i2);
            }
            sb.append(cost).append("\n");
        }
        System.out.println(sb);
    }
}
