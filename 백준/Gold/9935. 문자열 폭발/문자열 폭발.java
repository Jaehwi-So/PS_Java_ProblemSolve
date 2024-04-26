import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String s = sc.nextLine();
        String c = sc.nextLine();


        char[] chs = s.toCharArray();
        char[] cond = c.toCharArray();

        Stack<Character> stack = new Stack();

        for(int i = 0; i < chs.length; i++){
            stack.push(chs[i]);

            if(stack.size() >= cond.length){
                boolean flag = true;
                for(int j = 0; j < cond.length; j++){
                    if(stack.get(stack.size() - cond.length + j) != cond[j]){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    for(int j = 0; j < cond.length; j++){
                        stack.pop();
                    }
                }
            }
        }

        for(char ch : stack){
            sb.append(ch);
        }


        if(sb.length() == 0){
            System.out.println("FRULA");
        }
        else{
            System.out.println(sb);
        }

    }
}
