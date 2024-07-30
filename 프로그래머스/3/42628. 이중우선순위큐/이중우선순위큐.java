import java.util.*;

class MyQueue{
    List<Integer> list = new ArrayList();
    
    public void insert(int number){
        int index = findIndex(number);
        System.out.println(index);
        list.add(index, number);
    }
    
    public int findIndex(int number){
        int start = 0;
        int end = this.list.size();
        
        while(start < end){
            int mid = (start + end) / 2;
            if(list.get(mid) > number){
                end = mid;
            }
            else{
                start = mid + 1;
            }
        }
        
        return start;
    }
    
    public int pollFirst(){
        if(!list.isEmpty()){
            return this.list.remove(0);
        }
        return 0;
        
    }
    
    public int pollLast(){
        if(!list.isEmpty()){
            return this.list.remove(list.size() - 1);
        }
        return 0;
    }
    
    public int peekFirst(){
        if(!list.isEmpty()){
            return this.list.get(0);
        }
        return 0;
        
    }
    
    public int peekLast(){
        if(!list.isEmpty()){
            return this.list.get(list.size() - 1);
        }
        return 0;
        
    }
}
class Solution {
    public int[] solution(String[] operations) {
        
        MyQueue queue = new MyQueue();
        
        for(String s : operations){
            String[] str = s.split(" ");
            if(str[0].equals("I")){
                int number = Integer.parseInt(str[1]);
                queue.insert(number);
            }
            else if(str[0].equals("D")){
                int cmd = Integer.parseInt(str[1]);
                if(cmd == 1){
                    queue.pollLast();
                }
                else{
                    queue.pollFirst();
                }
            }
        }
        
        
        int[] answer = {queue.peekLast(), queue.peekFirst()};
        return answer;
    }
}