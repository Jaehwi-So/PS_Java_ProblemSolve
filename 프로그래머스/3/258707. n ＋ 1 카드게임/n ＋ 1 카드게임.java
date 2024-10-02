import java.util.*;
class Card implements Comparable<Card>{
    int seq;
    int number;
    int coin;
    public Card(int seq, int number, int coin){
        this.seq = seq;
        this.number = number;
        this.coin = coin;
    }
    
    public int compareTo(Card c){
        return this.seq - c.seq;
    }
}
class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int[] sequence = new int[n];
        Queue<Card> queue = new LinkedList();
        Queue<Card> temp = new LinkedList();
        
        int index = 1;
        for(int i = 0; i < n; i++){
            boolean success = false;
            while(!queue.isEmpty()){
                Card c = queue.poll();
                if(c.number + cards[i] == n+1){
                    sequence[c.seq] = index;
                    sequence[i] = index;
                    index++;
                    success = true;
                    break;
                }
                else{
                    temp.offer(c);
                }
            }
            if(success == false){
                temp.offer(new Card(i, cards[i], 0));
            }
            while(!temp.isEmpty()){
                queue.offer(temp.poll());
            }
        }
        
        PriorityQueue<Card> pq = new PriorityQueue();
        int save = 0;
        int dSave = 0;
        for(int i = 0; i < n/3; i++){
            if(!pq.isEmpty() && pq.peek().seq == sequence[i]){
                save++;
                coin++;
                pq.poll();
            }
            else{
                pq.offer(new Card(sequence[i], 0, 0));
            }
        }
        
        int round = 0;
        int i;
        for(i = n/3; i < n; i += 2){
            for(int j = i; j < i + 2 && j < n; j++){
                if(!pq.isEmpty() && pq.peek().seq == sequence[j]){
                    if(pq.peek().coin == 0){
                        save++;
                    }
                    else{
                        dSave++;
                    }
                    pq.poll();
                }
                else{
                    pq.offer(new Card(sequence[j], 0, 1));
                }
            }
            
            round++;
            if(save > 0 && coin > 0){
                save--;
                coin--;
            }
            else if(dSave > 0 && coin > 1){
                dSave--;
                coin-=2;
            }
            else{
                break;
            }
        }
        
        if(i == n){
            round++;
        }

        int answer = round;
        return answer;
    }
}

/**
3 6 7 2 1 10 5 9 8 12 11 4
2 1 1   4  2 3   3 4
**/