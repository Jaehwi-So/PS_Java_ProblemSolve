import java.util.*;

class Solution {
    public String solution(String number, int k) {
        char[] chs = number.toCharArray();
        int length = chs.length;
        List<Integer> list = new ArrayList();
        for(int i = 0; i < length; i++){
            list.add(Character.getNumericValue(chs[i]));
        }
        
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack();
    
        
        int count = 0;
        for(int i = 0; i < length; i++){
            while(!stack.isEmpty() && stack.peek() < list.get(i) && count < k){
                stack.pop();
                count++;
            }
            stack.push(list.get(i));
        }
        
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        
        sb.reverse();
        
        String answer = sb.toString().substring(0, number.length() - k);
        return answer;
    }
}

/**
333222111
**/