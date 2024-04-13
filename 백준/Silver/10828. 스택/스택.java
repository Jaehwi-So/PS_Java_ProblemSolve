
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class MyStack{
    List<Integer> elements = new LinkedList<>();

    void push(int ele){
        elements.add(ele);
    }

    int pop(){
        if(elements.size() == 0){
            return -1;
        }
        return elements.remove(elements.size() - 1);
    }

    int size(){
        return elements.size();
    }

    int isEmpty(){
        return size() == 0 ? 1 : 0;
    }

    int peek(){
        if(elements.size() == 0){
            return -1;
        }
        return elements.get(elements.size() - 1);
    }



}

public class Main {

    static MyStack stack = new MyStack();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int n = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if(command.equals("push")){
                int element = Integer.parseInt(st.nextToken());
                stack.push(element);
            }
            else if(command.equals("pop")){
                sb.append(stack.pop()).append("\n");
            }
            else if(command.equals("size")){
                sb.append(stack.size()).append("\n");
            }
            else if(command.equals("empty")){
                sb.append(stack.isEmpty()).append("\n");
            }
            else if(command.equals("top")){
                sb.append(stack.peek()).append("\n");
            }
        }
        System.out.println(sb);

    }
}