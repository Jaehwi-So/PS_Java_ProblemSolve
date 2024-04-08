import java.util.*;

class Deque{
    LinkedList<Integer> elements = new LinkedList<>();

    void enqueue(int x){
        elements.addFirst(x);
    }

    void enqueueLast(int x){
        elements.addLast(x);
    }

    int dequeue(){
        if(size() == 0){
            return -1;
        }
        return elements.removeFirst();
    }

    int dequeueLast(){
        if(size() == 0){
            return -1;
        }
        return elements.removeLast();
    }

    int size(){
        return elements.size();
    }

    int isEmpty(){
        if(size() == 0){
            return 1;
        }
        else{
            return 0;
        }
    }

    int peek(){
        if(size() == 0){
            return -1;
        }

        return elements.getFirst();
    }

    int peekLast(){
        if(size() == 0){
            return -1;
        }
        return elements.getLast();
    }


}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Deque deque = new Deque();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            int k = sc.nextInt();
            if(k == 1){
                int ele = sc.nextInt();
                deque.enqueue(ele);
            }
            else if(k == 2){
                int ele = sc.nextInt();
                deque.enqueueLast(ele);
            }
            else if(k == 3){
                sb.append(deque.dequeue()).append("\n");
            }
            else if(k == 4){
                sb.append(deque.dequeueLast()).append("\n");
            }
            else if(k == 5){
                sb.append(deque.size()).append("\n");
            }
            else if(k == 6){
                sb.append(deque.isEmpty()).append("\n");
            }
            else if(k == 7){
                sb.append(deque.peek()).append("\n");
            }
            else if(k == 8){
                sb.append(deque.peekLast()).append("\n");
            }
        }

        System.out.println(sb);

    }
}