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
list.size() - count + 1
count = 4 1231234 -> 3  
count = 3 1234 -> 2   
count = 2 134 -> 3
count = 1 34 -> 4
**/