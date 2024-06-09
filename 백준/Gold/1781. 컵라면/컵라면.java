import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Homework implements Comparable<Homework>{
    int value;
    int deadline;
    public Homework(int deadline, int value){
        this.deadline = deadline;
        this.value = value;
    }


    @Override
    public int compareTo(Homework h){
        return h.deadline - this.deadline;
    }


}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int maxDay = Integer.MIN_VALUE;
        Homework[] homeworks = new Homework[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            homeworks[i] = new Homework(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            maxDay = Math.max(maxDay, homeworks[i].deadline);
        }

        Arrays.sort(homeworks);

        PriorityQueue<Homework> queue = new PriorityQueue(new Comparator<Homework>() {
            @Override
            public int compare(Homework o1, Homework o2) {
                return o2.value - o1.value;
            }
        });

        int index = 0;
        int value = 0;
        for(int i = maxDay; i > 0; i--){
            while(index < n){
                if(homeworks[index].deadline == i){
                    queue.offer(homeworks[index]);
                    index++;
                }
                else{
                    break;
                }
            }
            if(!queue.isEmpty()){
                value += queue.poll().value;
            }
        }
        System.out.println(value);
    }
}