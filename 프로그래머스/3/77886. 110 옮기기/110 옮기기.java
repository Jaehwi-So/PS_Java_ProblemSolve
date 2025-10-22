import java.util.*;

class Solution {
    
    static String operate(char[] chs, int n){
        Stack<Character> stack = new Stack();
        StringBuilder sb = new StringBuilder();
        
        int count = 0;
        for(int i = 0; i < n; i++){
            stack.push(chs[i]);
            while(!stack.isEmpty() && stack.size() >= 3){
                char bf1 = stack.pop();
                char bf2 = stack.pop();
                char bf3 = stack.pop();
                // System.out.println(bf3 + " " + bf2 + " " + bf1);
                if(bf3 == '1' && bf2 == '1' && bf1 == '0'){
                    count++;
                }
                else{
                    stack.push(bf3);
                    stack.push(bf2);
                    stack.push(bf1);
                    break;
                }
            }
        }
        
        // 1 0 110 1
        //0 110 1
        // 1. 가장 처음 등장하는 '11' 앞에 모든 '110' 삽입, 
        // 가장 마지막에 등장하는 '0' 뒤에 모든 '110' 삽입 (0이 없다면 맨 처음에 삽입)
        while(!stack.isEmpty()){
            sb.insert(0, stack.pop());
        }

        char[] chs2 = sb.toString().toCharArray();
        int index = 0;
        for(int i = 0; i < chs2.length; i++){
            if(chs2[i] == '0'){
                index = i + 1;
            }
        }
        
        
        StringBuilder sb2 = new StringBuilder();
        for(int i = 0; i < count; i++){
            sb2.append("110");
        }
        
        // 0111
        sb.insert(index, sb2.toString());
        return sb.toString();
    }
    public String[] solution(String[] s) {
        int idx = 0;
        String[] answer = new String[s.length];
        
        for(String str : s){
            char[] chs = str.toCharArray();
            int n = str.length();

            answer[idx] = operate(chs, n);
            idx++;
        }

        return answer;
    }
}


// 110은 0 뒤로. 앞에 0이 없으면 맨 앞으로.