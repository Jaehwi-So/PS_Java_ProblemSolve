import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while(true){
            String str = sc.nextLine();
            if(str.equals(".")){
                break;
            }
            sb.append(str);
        }
        String[] arr = sb.toString().split("\\.");

        List<char[]> list = new ArrayList<>();
        for(String s : arr){
            list.add(s.toCharArray());
        }

        sb.setLength(0);

        Stack<Character> stack = new Stack<>();
        for(char[] chs : list){

            boolean state = true;
            for(char ch : chs){
                if(ch == '[' || ch == '('){
                    stack.push(ch);
                }
                else if(ch == ']' || ch == ')'){
                    char t = ch == ']' ? '[' : '(';
                    if(stack.isEmpty() || t != stack.pop()){
                        state = false;
                        break;
                    }
                }
            }
            if(!stack.isEmpty()){
                state = false;
            }
            String s = state ? "yes" : "no";
            sb.append(s).append("\n");
            stack.clear();



        }
        System.out.println(sb);

    }
}
