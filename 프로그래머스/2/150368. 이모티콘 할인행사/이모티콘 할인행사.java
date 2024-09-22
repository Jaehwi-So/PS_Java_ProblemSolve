import java.util.*;

class Solution {
    static int n;
    static int[] rates;
    static int[][] users;
    static int[] emoticons;
    static int[] answer = new int[2];
    static void calc(int step){
        if(step == n){
            int service = 0;
            int sum = 0;
            for(int i = 0; i < users.length; i++){
                int money = 0;
                for(int j = 0; j < n; j++){
                    if(rates[j] >= users[i][0]){
                        money += (emoticons[j] * (100 - rates[j])) / 100;
                    }
                }
                if(money >= users[i][1]){
                    service += 1;
                }
                else{
                    sum += money;
                }
            }
            
            if(answer[0] < service){
                answer[0] = service;
                answer[1] = sum;
            }
            else if(answer[0] == service){
                answer[1] = Math.max(answer[1], sum);
            }
        }
        else{
            for(int i = 1; i <= 4; i++){
                rates[step] = i * 10;
                calc(step + 1);
                rates[step] = 0;
            }
        }
        
    }
    public int[] solution(int[][] users, int[] emoticons) {
        n = emoticons.length;
        rates = new int[n];
        this.users = users;
        this.emoticons = emoticons;
        calc(0);
        
        return answer;
    }
}

