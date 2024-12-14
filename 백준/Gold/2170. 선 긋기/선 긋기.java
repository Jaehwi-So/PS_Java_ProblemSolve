
import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long[][] points = new long[n][2];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            points[i][0] = Long.parseLong(st.nextToken());
            points[i][1] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(points, new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                if(o1[0] - o2[0] > 0){
                    return 1;
                }
                else if(o1[0] - o2[0] == 0){
                    return 0;
                }
                else{
                    return -1;
                }
            }
        });

        PriorityQueue<long[]> pq = new PriorityQueue<>(new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                if(o2[1] - o1[1] > 0){
                    return 1;
                }
                else if(o2[1] - o1[1] == 0){
                    return 0;
                }
                else{
                    return -1;
                }
            }
        });

        for(int i = 0; i < n; i++){
            if(!pq.isEmpty() && pq.peek()[1] >= points[i][0]){
                long[] p = pq.poll();
                pq.offer(new long[]{p[0], Math.max(p[1], points[i][1])});

            }
            else{
                pq.offer(points[i]);
            }
        }

        long result = 0;
        while(!pq.isEmpty()){
            long[] p = pq.poll();
            result += p[1] - p[0];
        }
        System.out.println(result);

    }
}