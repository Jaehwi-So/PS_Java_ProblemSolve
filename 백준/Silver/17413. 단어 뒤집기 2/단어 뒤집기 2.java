import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        Stack<Character> stack = new Stack();
        StringBuilder sb = new StringBuilder();

        char[] chs = str.toCharArray();
        for(int j = 0; j < chs.length; j++){
            if(chs[j] == '<'){
                while(!stack.isEmpty()){
                    sb.append(stack.pop());
                }
                while(chs[j] != '>'){
                    sb.append(chs[j]);
                    j++;
                }
                sb.append(chs[j]);
            }
            else if(chs[j] == ' '){
                while(!stack.isEmpty()){
                    sb.append(stack.pop());
                }
                sb.append(" ");
            }
            else{
                stack.push(chs[j]);
            }
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }
}
