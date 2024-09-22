import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        Stack<int[]> stack = new Stack();
        int n = numbers.length;
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        for(int i = 0; i < n; i++){
            int number = numbers[i];
            while(!stack.isEmpty() && stack.peek()[0] < number){
                int[] p = stack.pop();
                answer[p[1]] = number;
            }
            stack.push(new int[]{numbers[i], i});
        }
        // System.out.println(Arrays.toString(answer));

        return answer;
    }
}