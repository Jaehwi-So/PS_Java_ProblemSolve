import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int[][] score = new int[scores.length][3];
        for(int i = 0; i < scores.length; i++){
            score[i][0] = scores[i][0];
            score[i][1] = scores[i][1];
            score[i][2] = i == 0 ? 1 : 0;
        }
        Arrays.sort(score, new Comparator<int[]>(){
            public int compare(int[] s1, int[] s2){
                return s2[0] - s1[0];
            }
        });
        
        PriorityQueue<int[]> queue = new PriorityQueue(new Comparator<int[]>(){
            public int compare(int[] s1, int[] s2){
                return s2[1] - s1[1];
            }
        });
        
        
        // 앞에는 높거나 같은 애들만 있음. 
        int last = -1;
        Queue<int[]> temp = new LinkedList();
        for(int i = 0; i < score.length; i++){
            if(last != score[i][0]){
                while(!temp.isEmpty()){
                    queue.offer(temp.poll());
                }
            }
            if(!queue.isEmpty() && queue.peek()[1] > score[i][1]){
                int[] tmp = new int[]{score[i][0], score[i][1], 0, score[i][2]};
                temp.offer(tmp);
            }
            else{
                int[] tmp = new int[]{score[i][0], score[i][1], 1, score[i][2]};
                temp.offer(tmp);
            }
            last = score[i][0];
        }
        
        while(!temp.isEmpty()){
            queue.offer(temp.poll());
        }
        
        PriorityQueue<int[]> result = new PriorityQueue(new Comparator<int[]>(){
            public int compare(int[] s1, int[] s2){
                if((s2[0] + s2[1]) == (s1[0] + s1[1])){
                    return s2[3] - s1[3];
                }
                return (s2[0] + s2[1]) - (s1[0] + s1[1]);
            }
        });
        
        while(!queue.isEmpty()){
            int[] array = queue.poll();
            if(array[2] == 1){
                result.offer(array);
            }
            else if(array[3] == 1){
                return -1;
            }
        }
        
        int answer = 1;
        while(!result.isEmpty()){
            int[] t = result.poll();
            if(t[3] == 1){
                break;
            }
            answer++;
        }
        

        return answer;
    }
}

// 1번 점수가 높은 순으로 큐에 Push -> 큐에는 2번 점수가 높은 순으로 정렬
// 들어오는 애들은 이미 1번 점수는 앞에 애들보다 낮은데, 2번 점수조차 낮으면 탈락