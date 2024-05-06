import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Score implements Comparable<Score>{
    int a;
    int b;
    public Score(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Score s) {
        if(s.a == this.a){
            return this.b - s.b;
        }
        return this.a - s.a;
    }

    public String toString(){
        return a + " " + b;
    }
}
public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static void enroll() throws IOException{
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Score[] scores = new Score[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            scores[i] = new Score(a, b);
        }
        Arrays.sort(scores); //A 최상위 순서
//        System.out.println(Arrays.toString(scores));


        PriorityQueue<Score> queue = new PriorityQueue<>(new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                return o1.b - o2.b;
            }
        }); //A는 똑똑한 순서. B도 똑똑한 순서

        queue.offer(scores[0]);
        for(int i = 1; i < n; i++){
            Score s = queue.peek();
            if(s.b >= scores[i].b){ //당연히 s보다 A는 아래지만, B는 위면 큐에 집어넣음
                queue.offer(scores[i]);
            }

//            System.out.println("queue : " + queue);
        }
        sb.append(queue.size()).append("\n");

    }
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for(int i = 0; i < t; i++){
            enroll();
        }
        System.out.println(sb);
    }
}
