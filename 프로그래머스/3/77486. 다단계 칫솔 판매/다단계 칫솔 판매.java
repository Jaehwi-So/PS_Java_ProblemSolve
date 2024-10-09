import java.util.*;
class Solution {
    static int n;
    static int[] parents;
    static int[] price;
    
    static void calc(int current, int p){

        int cost = (p / 10);
        
        if(parents[current] >= 0){
            if(cost > 0){
                price[current] += p - cost;
                calc(parents[current], cost);
            }
            else{
                price[current] += p;
            }
            
        }
        else{
            price[current] += p;
        }
    }
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Integer> map = new HashMap();
        
        n = enroll.length;
        parents = new int[n+1];
        map.put("-", 0);
        parents[0] = -1;
        for(int i = 1; i <= n; i++){
            map.put(enroll[i-1], i);
        }
        
        for(int i = 1; i <= n; i++){
            int parent;
            if(referral[i-1].equals("-")){
                parent = 0;
            }
            else{
                parent = map.get(referral[i-1]);
            }
            int child = i;
            parents[child] = parent;
        }
        
        
        price = new int[n+1];
        for(int i = 0; i < seller.length; i++){
            int idx = map.get(seller[i]);
            int p = amount[i] * 100;
            calc(idx, p);
        }
        
        
        int[] answer = new int[price.length - 1];
        for(int i = 0; i < price.length - 1; i++){
            answer[i] = price[i+1];
        }
        

        return answer;
    }
}