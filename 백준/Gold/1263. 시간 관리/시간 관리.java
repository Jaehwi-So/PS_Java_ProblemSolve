import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Work implements Comparable<Work>{
    int durate;
    int end;
    public Work(int durate, int end){
        this.durate = durate;
        this.end = end;
    }

    @Override
    public int compareTo(Work w){
        return w.end - this.end;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Work[] array = new Work[n];
        int last = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            array[i] = new Work(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            last = Math.max(last, array[i].end);
        }
        Arrays.sort(array);


        PriorityQueue<Work> queue = new PriorityQueue(new Comparator<Work>() {
            @Override
            public int compare(Work o1, Work o2) {
                return o1.end - o2.end;
            }
        });

        int result = -1;
        int index = 0;
        for(int i = last; i >= 0; i+=0){
//            System.out.println("last " + i);
            while(index < n){
                if(array[index].end >= i){
                    queue.offer(array[index]);
                    index++;
                }
                else{
                    break;
                }
            }

            if(!queue.isEmpty()){
                Work work = queue.poll();
//                System.out.println(work.durate);
                i -= work.durate;
            }
            else{
                i--;
            }

            if(i < 0){
                result = -1;
                break;
            }

            if(index >= n && queue.isEmpty()){
                result = i;
                break;
            }
        }


//        System.out.println("Index" + index);
        System.out.println(result);
    }
}