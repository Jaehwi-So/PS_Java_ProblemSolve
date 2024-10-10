import java.util.*;

class City{
    int gold;
    int silver;
    int weight;
    int time;
    
    public City(int gold, int silver, int weight, int time){
        this.gold = gold;
        this.silver = silver;
        this.weight = weight;
        this.time = time;
    }

}
class Solution {
    static long max;
    
    static long[] calc(City[] cities, long time, int a, int b){
        long gold = 0;
        long silver = 0;
        long total = 0;
        
        //총량(800), 최대 금(700), 최대 은(500)
        for(int i = 0; i < cities.length; i++){
            int g = cities[i].gold;
            int s = cities[i].silver;
            int w = cities[i].weight;
            int t = cities[i].time;
            
            long k = 0; //조달가능 횟수    
            long current = time;
            if(current > t){
                current -= t;
                k++;
            }
            k += current / (t * 2); 
            
            
            long weight = w * k; //총 운반가능량
            
            if(weight > g){
                gold += g;
            }
            else{
                gold += weight;
            }
            if(weight > s){
                silver += s;
            }
            else{
                silver += weight;
            }
            
            long tot = weight;
            if(tot > g + s){
                tot = g + s;
            }
            total += tot;
            
        }
        
        return new long[]{gold, silver, total};
    }
    
    static long binarySearch(City[] cities, int a, int b){
        long left = 0;
        long right = max;
        while(left < right){
            long mid = (left / 2) + (right / 2);
            long[] r = calc(cities, mid, a, b);
            long gold = r[0];
            long silver = r[1];
            long total = r[2];
            if(gold < a || silver < b || total < a + b){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }
        return right;        
    }
    
    
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        
        long maxT = 0;
        City[] cities = new City[g.length];
        for(int i = 0; i < cities.length; i++){
            cities[i] = new City(g[i], s[i], w[i], t[i]);
            maxT = Math.max(maxT, t[i]);
        }
        
        max = maxT * (a + b) * 2;
        
        long time = binarySearch(cities, a, b);

        long answer = time;
        return answer;
    }
}
