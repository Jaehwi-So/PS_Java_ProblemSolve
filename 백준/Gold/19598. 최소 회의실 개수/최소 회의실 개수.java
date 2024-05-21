import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Meeting implements Comparable<Meeting>{
    int start;
    int end;
    public Meeting(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Meeting o) {
        if(this.start - o.start == 0){
            return this.end - o.end;
        }
        return this.start - o.start;
    }

    public String toString(){
        return String.format("(%d - %d)", start, end);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Meeting[] array = new Meeting[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            array[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        PriorityQueue<Meeting> queue = new PriorityQueue<>(new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o1.end - o2.end;
            }
        });

        Arrays.sort(array);
//        System.out.println(Arrays.toString(array));

        queue.add(array[0]);
        for(int i = 1; i < n; i++){
            if(queue.peek().end <= array[i].start){
                Meeting m = queue.poll();
                m.end = array[i].end;
                queue.offer(m);
            }
            else{
                queue.offer(array[i]);
            }
        }
        System.out.println(queue.size());
    }
}