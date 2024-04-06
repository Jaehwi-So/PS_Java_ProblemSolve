import java.util.*;
import java.io.*;


class MyQueue{
    LinkedList<Integer> elements = new LinkedList<>();

    void push(int e){
        elements.addLast(e);
    }

    int pop(){
        if(empty() == 1){
            return -1;
        }
        return elements.removeFirst();
    }

    int front(){
        if(empty() == 1){
            return -1;
        }
        return elements.getFirst();
    }

    int back(){
        if(empty() == 1){
            return -1;
        }
        return elements.getLast();
    }

    int size(){
        return elements.size();
    }

    int empty(){
        return elements.size() == 0 ? 1: 0;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        MyQueue queue = new MyQueue();

        StringBuilder sb = new StringBuilder();

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(r.readLine());
        for(int i = 0; i < n; i++){
            String[] str = r.readLine().split(" ");
            String s = str[0];
            if(s.equals("push")){
                int k = Integer.parseInt(str[1]);
                queue.push(k);
            }
            else if(s.equals("pop")){
                sb.append(queue.pop()).append("\n");
            }
            else if(s.equals("size")){
                sb.append(queue.size()).append("\n");
            }
            else if(s.equals("empty")){
                sb.append(queue.empty()).append("\n");
            }
            else if(s.equals("front")){
                sb.append(queue.front()).append("\n");
            }
            else if(s.equals("back")){
                sb.append(queue.back()).append("\n");
            }
        }
        System.out.println(sb);
    }
}
