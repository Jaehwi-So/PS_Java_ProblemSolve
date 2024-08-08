import java.util.*;

class Plan implements Comparable<Plan>{
    String name;
    int start;
    int playtime;
    public Plan(String name, int start, int playtime){
        this.name = name;
        this.start = start;
        this.playtime = playtime;
    }
    
    public int compareTo(Plan p){
        return this.start - p.start;
    }
    
    public String toString(){
        return "(" + this.name + ", " + this.start + ", " + this.playtime + ")";
    }
}

class Solution {
    public String[] solution(String[][] plans) {
        Plan[] list = new Plan[plans.length];
        for(int i = 0; i < plans.length; i++){
            int time = Integer.parseInt(plans[i][1].substring(0, 2)) * 60;
            int minute = Integer.parseInt(plans[i][1].substring(3, 5));
            list[i] = new Plan(plans[i][0], time + minute, Integer.parseInt(plans[i][2]));
        }
        Arrays.sort(list);
        String[] answer = new String[list.length];
        
        Stack<Plan> stack = new Stack();
        stack.push(list[0]);
        int currentTime = list[0].start;
        int next = 1;
        int index = 0;
        
        while(!stack.isEmpty()){
            if(next < list.length){
                int nextTime = list[next].start;
                if(currentTime < nextTime){
                    Plan current = stack.pop();
                    if(currentTime + current.playtime > nextTime){ 
                        current.playtime = current.playtime - (nextTime - currentTime);
                        currentTime += (nextTime - currentTime);
                        stack.push(current);
                    }
                    else{
                        answer[index] = current.name;
                        currentTime += current.playtime;
                        index++;
                    }
                }
                if(currentTime == nextTime){
                    stack.push(list[next]);
                    next++;
                }
                else if(stack.isEmpty() && next < list.length){
                    stack.push(list[next]);
                    currentTime = list[next].start;
                    next++;
                }
            }
            else{
                answer[index] = stack.pop().name;
                index++;
            }
        }
        
        // for(String s : answer){
        //     System.out.print(s + " ");
        // }
        

        return answer;
    }
}