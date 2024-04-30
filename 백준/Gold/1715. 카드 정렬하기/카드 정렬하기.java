import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> queue = new PriorityQueue();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            queue.add(Integer.parseInt(st.nextToken()));
        }

        int result = 0;
        while(queue.size() > 1){
            int m = queue.poll();
            int k = queue.poll();
            int tmp = m + k;
            result += tmp;
            queue.add(tmp);
        }

        System.out.println(result);

    }
}
