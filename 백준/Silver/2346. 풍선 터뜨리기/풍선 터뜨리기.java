import java.util.*;

class Entry{
    int paper;
    int index;

    public Entry(int paper, int index){
        this.paper = paper;
        this.index = index;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Deque<Entry> deque = new ArrayDeque<>();
        for(int i = 1; i <= n; i++){
            deque.add(new Entry(sc.nextInt(), i));
        }

        Entry entry = deque.poll();
        int moveNumber = entry.paper;
        StringBuilder sb = new StringBuilder();
        sb.append(entry.index).append(" ");
        while(!deque.isEmpty()){

            //양수면 앞쪽을 모두 큐 뒤쪽으로 보낸 후 가장 앞쪽 원소 제거
            if(moveNumber > 0){
                for(int i = 0; i < moveNumber - 1; i++){
                    deque.addLast(deque.pollFirst());
                }
                Entry next = deque.pollFirst();
                moveNumber = next.paper;
                sb.append(next.index).append(" ");
            }

            //음수면 뒤쪽을 모두 큐 앞쪽으로 보낸 후 가장 뒤쪽 원소 제거
            else{
                for(int i = 0; i < -moveNumber - 1; i++){
                    deque.addFirst(deque.pollLast());
                }
                Entry next = deque.pollLast();
                moveNumber = next.paper;
                sb.append(next.index).append(" ");
            }

            //두 가지 케이스 모두 큐의 순서는 유지됨
        }
        System.out.println(sb);
    }
}
