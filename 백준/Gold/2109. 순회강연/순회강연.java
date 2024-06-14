import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Lecture implements Comparable<Lecture>{
    int money;
    int time;
    public Lecture(int money, int time){
        this.money = money;
        this.time = time;
    }

    @Override
    public int compareTo(Lecture l){
        return l.time - this.time;
    }

}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Lecture[] lectures = new Lecture[n];
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            lectures[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            max = Math.max(max, lectures[i].time);
        }
        Arrays.sort(lectures);
        PriorityQueue<Lecture> queue = new PriorityQueue<>(new Comparator<Lecture>() {
            @Override
            public int compare(Lecture o1, Lecture o2) {
                return o2.money - o1.money;
            }
        });
        int index = 0;
        int result = 0;
        for(int i = max; i > 0; i--){
            while(true){
                if(index < n && lectures[index].time == i){
                    queue.offer(lectures[index]);
                    index++;
                }
                else{
                    break;
                }
            }
            if(!queue.isEmpty()){
                result += queue.poll().money;
            }
        }
        System.out.println(result);
    }
}
