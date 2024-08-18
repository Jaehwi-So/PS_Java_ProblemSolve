import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        

        Arrays.sort(people);
        
        int front = 0;
        int rear = people.length - 1;
        
        int count = 0;
        while(front <= rear){
            int end = people[rear];
            if(people[front] + end <= limit){
                front++;
            }
            rear--;
            count++;
        }
       
        return count;
    }
}

// 20 40 50 80