import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack();
        int n = sc.nextInt();
        for(int i = 0; i < n; i++){
            int k = sc.nextInt();
            if(k == 0){
                stack.pop();
            }
            else{
                stack.push(k);
            }
        }
        int sum = 0;
        while(!stack.isEmpty()){
            sum += stack.pop();
        }
        System.out.println(sum);
    }
}
