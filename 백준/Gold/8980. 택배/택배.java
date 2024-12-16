import java.io.*;
import java.util.*;
class Box implements Comparable<Box>{
    int start;
    int end;
    int amount;
    public Box(int start, int end, int amount){
        this.start = start;
        this.end = end;
        this.amount = amount;
    }

    public int compareTo(Box b){
        if(this.start == b.start){
            return this.end - b.end;
        }
        else{
            return this.start - b.start;
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        Box[] boxes = new Box[m];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());
            boxes[i] = new Box(start, end, amount);
        }

        // 시작 오름차순 정렬
        Arrays.sort(boxes);
        int current = 0;
        int index = 0;

        // 도착 내림차순 정렬
        PriorityQueue<Box> pq = new PriorityQueue(new Comparator<Box>() {
            @Override
            public int compare(Box b1, Box b2) {
                return b2.end - b1.end;
            }
        });

        int result = 0;
        Stack<Box> stack = new Stack<>();

        // 마을을 차례로 순회
        for(int i = 1; i <= n; i++) {

            // 내릴 수 있는 박스는 모두 내림 (우선순위 큐의 끝이므로 모두 순회)
            while (!pq.isEmpty()) {
                Box b = pq.poll();
                if(b.end == i){
                    current -= b.amount;
                    result += b.amount;
                }
                else{
                    stack.push(b);
                }
            }

            while(!stack.isEmpty()){
                pq.offer(stack.pop());
            }

            // 현재 실을 수 있는 박스 순회
            while (index < m && boxes[index].start == i) {

                // 현재 박스를 다 실을 수 없는 경우, 도착 지점이 늦은 곳이 있다면 해당 박스의 수량을 버림
                while (!pq.isEmpty() && pq.peek().end > boxes[index].end
                        && current + boxes[index].amount > c) {
                    Box b = pq.poll();
                    int appendix = current + boxes[index].amount - c;
                    if (b.amount > appendix) {
                        b.amount -= appendix;
                        current -= appendix;
                        pq.offer(b);
                    } else {
                        current -= b.amount;
                    }
                }

                // 수량이 넘치는 경우, 일부만 가져갈 수 있다면 가져가기
                if (current + boxes[index].amount > c) {
                    if (current < c) {
                        boxes[index].amount = c - current;
                        pq.offer(boxes[index]);
                        current += boxes[index].amount;
                    }
                }
                // 모두 실을 수 있다면 모두 싣기
                else {
                    pq.offer(boxes[index]);
                    current += boxes[index].amount;
                }
                index++;
            }
        }

        System.out.println(result);
    }
}