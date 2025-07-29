import java.util.*;

class Solution {
    
    static long parse(String s){
        char[] chs = s.toCharArray();
        long k = 1;
        long sequence = 0;
        for(int i = chs.length - 1; i >= 0; i--){
            sequence += ((int)chs[i] - 96) * k;
            k *= 26;
        }
        return sequence;
    }
    
    
    static String getResult(long sequence){
        StringBuilder sb = new StringBuilder();
        while(sequence > 0){
            sequence--;
            sb.append((char)(((sequence) % 26) + 97)); //0 ~ 25
            sequence /= 26;
        }
        return sb.reverse().toString();
    }

    public String solution(long n, String[] bans) {
        
        Arrays.sort(bans, new Comparator<String>(){
            public int compare(String s1, String s2){
                if(s1.length() == s2.length()){
                    return s1.compareTo(s2);
                }
                return s1.length() - s2.length();
            };
        });
        
        for(String s : bans){
            long sq = parse(s);
            if(sq <= n) n++;
            else break;
        }
        
//         for(int i = 1; i < 100; i++){
//             System.out.println(i + " " + getResult(i));
//         }
        
        // System.out.println(getResult(n));
        String answer = getResult(n);
        return answer;
        
    }
}