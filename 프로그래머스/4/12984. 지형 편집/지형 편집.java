import java.util.*;

public class Solution {
    public long solution(int[][] land, int P, int Q) {
        
        Map<Integer, Integer> map = new HashMap();
        
        long total = 0;
        long totalBlock = 0;
        
        List<Integer> flatList = new ArrayList();
        
        for(int[] line : land){
            for(int k : line){
                flatList.add(k);
                total += k;
                totalBlock ++;
            }
        }
        
        Collections.sort(flatList);
        
        List<int[]> list = new ArrayList();
        int currentKey = -1;
        int currentVal = -1;
        
        for(int k : flatList){
            if(k != currentKey){
                if(currentKey != -1) list.add(new int[]{currentKey, currentVal});
                currentKey = k;
                currentVal = 0;
            }
            currentVal++;
            
        }
        
        list.add(new int[]{currentKey, currentVal});
        
//         for(int[] line : list){
//             System.out.println(Arrays.toString(line));
//         }
        
        
        long answer = total * Q;
        
        long beforeCost = answer;
        long beforeBlock = 0;
        long beforeKey = 0;
        
        if(list.get(0)[0] == 0){
            beforeBlock = list.get(0)[1];
        } 
        
        for(int[] b : list){

            int key = b[0];
            if(key == 0) continue;
            
            long diff = key - beforeKey;
            
            // System.out.println(beforeBlock + " " + totalBlock);
            
            long addBlock = (P * diff * beforeBlock);
            long removeBlockAdj = (Q * diff * (totalBlock - beforeBlock));
            long currentCost = beforeCost + addBlock - removeBlockAdj;
            
            // System.out.println(beforeCost + " " + addBlock + " " + removeBlockAdj);
            
            beforeCost = currentCost;
            beforeKey = key;
            beforeBlock += b[1];
            
            answer = Math.min(answer, currentCost);
            
            
        }
        

        return answer;
    }
}



// 총 23개 블록
// 0 0 1 2 2 4 4 5 5

// 0을 만들기 위해 드는 비용 : 제거(Q * 23)
// 1을 만들기 위해 드는 비용 = 0을 만들기 위한 비용 + 추가(P * 2) - 제거 보정(Q * 7)  7 = 1보다 크거나 같은 칸들의 개수 = 16개 제거
// 2를 만들기 위해 드는 비용 = 1을 만들기 위한 비용 + 추가(P * 3) + 제거 보정 (Q * 6) = 10개 제거
// 4를 만들기 위해 드는 비용 = 2를 만들기 위한 비용 + 추가(P * 5) + 제거 보정 (Q * 2 * 4) = 2개 제거