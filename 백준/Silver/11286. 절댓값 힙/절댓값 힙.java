import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int a = Math.abs(o1);
                int b = Math.abs(o2);
                if(a == b){
                    return o1 - o2;
                }
                return a - b;
            }
        });
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            if(k == 0){
                if(queue.isEmpty()){
                    sb.append(0).append("\n");
                }
                else{
                    sb.append(queue.poll()).append("\n");
                }
            }
            else{
                queue.offer(k);
            }
        }
        System.out.println(sb);

    }
}