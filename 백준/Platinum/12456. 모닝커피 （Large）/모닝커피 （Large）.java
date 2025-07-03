import java.io.*;
import java.util.*;

class Coffee{
    long amount;
    long expired;
    long score;
    public Coffee(long amount, long expired, long score){
        this.amount = amount;
        this.expired = expired;
        this.score = score;
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int test = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for(int C = 1; C <= test; C++){

            PriorityQueue<Coffee> waitQueue = new PriorityQueue<>(new Comparator<Coffee>() {
                @Override
                public int compare(Coffee o1, Coffee o2) {
                    if(o1.expired == o2.expired) return 0;
                    return o1.expired >= o2.expired ? -1 : 1;
                }
            });

            PriorityQueue<Coffee> pq = new PriorityQueue<>(new Comparator<Coffee>() {
                @Override
                public int compare(Coffee o1, Coffee o2) {
                    if(o1.score == o2.score) return 0;
                    return o1.score > o2.score ? -1 : 1;
                }
            });

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); //커피 종류
            long k = Long.parseLong(st.nextToken()); //최대 날짜
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                long c = Long.parseLong(st.nextToken()); //수량
                long t = Long.parseLong(st.nextToken()); //유통기한
                long s = Long.parseLong(st.nextToken()); //가치
                waitQueue.offer(new Coffee(c, t, s));
            }

            long current = k;
            long score = 0;

            while(current >= 1){
                // 대기 큐에서 임박직전인 커피들 꺼내기
                while(!waitQueue.isEmpty() && waitQueue.peek().expired == current){
                    pq.offer(waitQueue.poll());
                }

                // 현재 후보 큐가 비어있다면 시간 점프
                if(pq.isEmpty()){
                    if(waitQueue.isEmpty()){ //모든 커피를 소비했다면
                        break;
                    }
                    else{
                        long next = waitQueue.peek().expired;
                        current = next;
                    }
                }
                else{
                    Coffee c = pq.peek();
                    long next = 0L; // 다음 체크 시점
                    if(!waitQueue.isEmpty()){
                        next = waitQueue.peek().expired;
                    }
                    long amount = Math.min(current - next, c.amount); //수량

                    c.amount -= amount;
                    if(c.amount == 0) pq.poll(); // 모든 커피를 다 마신 경우

                    score += c.score * amount;
                    current -= amount;
//                    System.out.println(amount + "개 : " + c.score);
                }
            }

            sb.append(String.format("Case #%d: %d\n", C, score));
        }

        System.out.println(sb);


    }
}