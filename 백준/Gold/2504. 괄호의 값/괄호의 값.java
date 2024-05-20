import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] chs = str.toCharArray();

        Stack<Character> stack = new Stack<>();
        Stack<Integer> value = new Stack<>();
        int result = 0;
        for(char c : chs){
            if(c == '(' || c == '['){
                stack.push(c);
                value.push(1);
            }
            else{
                if(stack.isEmpty()){
                    result = 0;
                    break;
                }

                char t = stack.pop();

                if(c == ')' && t == '('){
                    while(!value.isEmpty() && value.peek() != 1){
                        int v = value.pop();
                        if(!value.isEmpty() && value.peek() != 1){
                            int k = value.pop();
                            value.push(k + v);
                        }
                        else{
                            value.push(v);
                            break;
                        }
                    }

                    if(!value.isEmpty()){
                        int k = value.pop();
                        if(!value.isEmpty() && k != 1){
                            value.pop();
                        }
                        value.push(k * 2);
                    }
                }

                else if(c == ']' && t == '['){
                    while(!value.isEmpty() && value.peek() != 1){
                        int v = value.pop();
                        if(!value.isEmpty() && value.peek() != 1){
                            int k = value.pop();
                            value.push(k + v);
                        }
                        else{
                            value.push(v);
                            break;
                        }

                    }

                    if(!value.isEmpty()){
                        int k = value.pop();
                        if(!value.isEmpty() && k != 1){
                            value.pop();
                        }
                        value.push(k * 3);
                    }
                }
                else{
                    result = 0;
                    break;
                }
            }

//            System.out.println(value + " " + stack);
            if(stack.isEmpty()){
                result += value.pop();
            }

        }

        System.out.println(result);


    }
}