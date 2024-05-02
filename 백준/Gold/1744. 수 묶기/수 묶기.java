import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> queue = new PriorityQueue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 == 0){
                    return 1;
                }
                else if(o2 == 0){
                    return -1;
                }
                else if(o1 < 0 && o2 < 0){
                    return o1 - o2;
                }
                return o2 - o1;
            }
        });





        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            queue.offer(Integer.parseInt(st.nextToken()));
        }

        int result = 0;

        while(!queue.isEmpty()){
            int i = queue.poll();

            if(!queue.isEmpty()){
                int j = queue.poll();

                if(i*j > i+j){
                    result += i*j;

                }
                else{
                    result += i;
                    queue.offer(j);
                }
            }
            else{
                result += i;
            }

        }

        System.out.println(result);

    }
}
