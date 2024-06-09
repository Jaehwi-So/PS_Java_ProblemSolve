import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Lecture implements Comparable<Lecture>{
    int start;
    int end;
    public Lecture(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Lecture o) {
        if(this.start == o.start){
            return this.end - o.end;
        }
        return this.start - o.start;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Lecture[] lectures = new Lecture[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            lectures[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(lectures);

        PriorityQueue<Lecture> queue = new PriorityQueue<>(new Comparator<Lecture>() {
            @Override
            public int compare(Lecture o1, Lecture o2) {
                return o1.end - o2.end;
            }
        });

        queue.offer(lectures[0]);
        for(int i = 1; i < n; i++){
            Lecture current = lectures[i];
            if(!queue.isEmpty()){
                Lecture lec = queue.peek();
                if(current.start >= lec.end){
                    queue.poll();
                    queue.offer(new Lecture(lec.start, current.end));
                }
                else{
                    queue.offer(current);
                }
            }
            else{
                queue.offer(current);
            }
        }
        System.out.println(queue.size());
    }
}