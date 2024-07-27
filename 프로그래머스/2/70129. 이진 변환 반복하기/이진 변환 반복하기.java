class Solution {
    static int count = 0;
    static StringBuilder sb;
    
    static String getBinary(int number){
        sb = new StringBuilder();
        while(number > 0){
            int k = number % 2;
            sb.insert(0, k);
            number /= 2; 
        }
        return sb.toString();
    }
    
    static String run(String s){
        int cnt = 0;
        char[] chs = s.toCharArray();
        for(char ch : chs){
            if(ch == '1'){
                cnt++;
            }
        }
        count += s.length() - cnt;
        String str = getBinary(cnt);
        return str;
    }
    
    public int[] solution(String s) {
        int tCount = 0;
        while(!s.equals("1")){
            s = run(s);
            tCount++;
        }
        
        int[] answer = {tCount, count};
        return answer;
    }
}