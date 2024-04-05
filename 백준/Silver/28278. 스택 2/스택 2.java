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
    static Scanner sc = new Scanner(System.in);

    static StringBuilder sb = new StringBuilder();

    static void order(int command){
        if(command == 1){
            int element = sc.nextInt();
            stack.push(element);
        }
        else if(command == 2){
            sb.append(stack.pop()).append("\n");
        }
        else if(command == 3){
            sb.append(stack.size()).append("\n");
        }
        else if(command == 4){
            sb.append(stack.isEmpty()).append("\n");
        }
        else if(command == 5){
            sb.append(stack.peek()).append("\n");
        }
    }
    public static void main(String[] args) {

        int n = sc.nextInt();
        for(int i = 0; i < n; i++){
            int command = sc.nextInt();
            order(command);
        }
        System.out.println(sb);

    }
}
