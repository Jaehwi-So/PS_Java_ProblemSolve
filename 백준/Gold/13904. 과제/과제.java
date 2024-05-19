import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Homework implements Comparable<Homework> {
    int date;
    int score;
    public Homework(int date, int score){
        this.date = date;
        this.score = score;
    }

    @Override
    public int compareTo(Homework o) {
        if(this.date == o.date){
            return o.score - this.score;
        }
        return o.date - this.date;
    }

    public String toString(){
        return this.date + " " + this.score;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Homework[] homeworks = new Homework[n];
        int maxDate = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            homeworks[i] = new Homework(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            maxDate = Math.max(homeworks[i].date, maxDate);
        }

        Arrays.sort(homeworks);
//        System.out.println(Arrays.toString(homeworks));

        PriorityQueue<Homework> pq = new PriorityQueue<>(new Comparator<Homework>() {
            @Override
            public int compare(Homework o1, Homework o2) {
                return o2.score - o1.score;
            }
        });


        int result = 0;

        // 마지막 날부터 시작. 단 중요도가 높은거 먼저 끝내야함
        // 7 7 7 8인데 7 7을 해야하는 경우?
        int j = 0;
        for(int i = maxDate; i >= 1; i--){
            while(j < n && homeworks[j].date == i){
                pq.offer(homeworks[j]);
                j++;
            }

            while(!pq.isEmpty()){
                Homework h = pq.poll();
                if(h.date >= i){
//                    System.out.println(h + " " + (n - i));
                    result += h.score;
                    break;
                }
            }


//            System.out.println(pq);

        }

        System.out.println(result);

    }
}
