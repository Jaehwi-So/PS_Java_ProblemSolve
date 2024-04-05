import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];

        for(int i = 0; i < n; i++){
            array[i] = sc.nextInt();
        }

        int current = 1;
        int arrIdx = 0;
        Stack<Integer> stack = new Stack<>();
        while(current < n + 1){

            if(!stack.isEmpty() && stack.peek() == current){
                stack.pop();
                current++;
            }
            else if(arrIdx == n && current < n + 1){
                System.out.println("Sad");
                return;
            }
            else if(arrIdx < n){
                stack.push(array[arrIdx]);
                arrIdx++;
            }
            else{
                System.out.println("Sad");
                return;
            }

        }
        System.out.println("Nice");
    }
}