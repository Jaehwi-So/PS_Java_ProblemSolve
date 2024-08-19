import java.util.*;

class Solution {
    static Map<Long, Long> parent = new HashMap();
    
    static void union(long k1, long k2){
        long n1 = find(k1);
        long n2 = find(k2);
        if(n1 > n2){
            parent.put(n2, n1);
        }
        else if(n2 > n1){
            parent.put(n1, n2);
        }
    }
    
    static long find(long k1){
        if(parent.get(k1) != k1){
            parent.put(k1, find(parent.get(k1)));
        }
        return parent.get(k1);
    }
    
    public long[] solution(long k, long[] room_number) {
        
        long[] temp = new long[room_number.length];
        
        for(int i = 0; i < room_number.length; i++){
            temp[i] = room_number[i];
        }
        Arrays.sort(temp);
        
        long index = 0;
        for(long l : temp){
            index++;
            if(l > index){
                parent.put(index, index);
                index = l;
            }
            parent.put(index, index);
        }
        index++;
        parent.put(index, index);
        
        // System.out.println(parent);
        
        long[] answer = new long[room_number.length];
        
        for(int i = 0; i < room_number.length; i++){
            long room = room_number[i];
            long cur = find(room);
            answer[i] = cur;
            union(cur, cur + 1);
            // System.out.println(parent);

        }

        return answer;
    }
}