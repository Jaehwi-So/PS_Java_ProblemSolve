import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        HashMap<Integer, Integer> times = new HashMap();
        HashMap<Integer, Integer> feeMap = new HashMap();

        
        for(int i = 0; i < records.length; i++){
            String s = records[i];
            int hour = Integer.parseInt(s.substring(0, 2));
            int minute = Integer.parseInt(s.substring(3, 5));
            int number = Integer.parseInt(s.substring(6, 10));
            String type = s.substring(11, 13);
            
            int timestamp = (hour * 60) + minute;
            if(type.equals("IN")){
                times.put(number, timestamp);
            }
            else{
                int value = times.remove(number);
                if(!feeMap.containsKey(number)){
                    feeMap.put(number, timestamp - value);
                }
                else{
                    feeMap.put(number, feeMap.get(number) + (timestamp - value));
                }
            }
        }
        
        for(int key : times.keySet()){
            int value = times.get(key);
            int timestamp = (60 * 23) + 59;
            if(!feeMap.containsKey(key)){
                feeMap.put(key, timestamp - value);
            }
            else{
                feeMap.put(key, feeMap.get(key) + (timestamp - value));
            }
        }
        
        List<int[]> list = new ArrayList();
        for(int key : feeMap.keySet()){
            int value = feeMap.get(key);
            int lastTime = value - fees[0];
            int addition = 0;
            if(lastTime > 0){
                addition = (int)Math.ceil((double)lastTime / fees[2]);
            }
            // System.out.println(key + " : " + lastTime + " " + addition + "(" + value);
            int cost = fees[1] + (addition * fees[3]);
            list.add(new int[]{key, cost});
        }
        
        Collections.sort(list, new Comparator<int[]>(){
            public int compare(int[] l1, int[] l2){
                return l1[0] - l2[0];
            }
        });
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i)[1];
        }
            

        
        return answer;
    }
}