import java.util.*;

class Solution {
    public int solution(int[] order) {
        int index = 0;
        Stack<Integer> stack = new Stack();
        
        for(int seq = 1; seq <= order.length; seq++){
            if(seq == order[index]){
                index++;
            }
            else{
                while(!stack.isEmpty()){
                    if(stack.peek() == order[index]){
                        stack.pop();
                        index++;
                    }
                    else{
                        break;
                    }
                }
                stack.push(seq);
            }    
        }
        
        while(!stack.isEmpty()){
            if(stack.peek() == order[index]){
                stack.pop();
                index++;
            }
            else{
                break;
            }
        }
        
        int answer = index;
        return answer;
    }
}