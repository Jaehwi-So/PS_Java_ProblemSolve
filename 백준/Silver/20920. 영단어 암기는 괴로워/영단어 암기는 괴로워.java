import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Words implements Comparable<Words>{
    String word;
    int freq;

    public Words(String word, int freq){
        this.word = word;
        this.freq = freq;
    }

    public int compareTo(Words w){
        int freqVal = w.freq - freq;
        if(freqVal == 0){
            int length = w.word.length() - word.length();
            if(length == 0){
                return word.compareTo(w.word);
            }
            return length;
        }
        return freqVal;
    }

}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            if(s.length() >= m){
                if(map.containsKey(s)){
                    map.replace(s, map.get(s) + 1);
                }
                else{
                    map.put(s, 1);
                }
            }
        }

        ArrayList<Words> words = new ArrayList<>();
        for(String key : map.keySet()){
            Words word = new Words(key, map.get(key));
            words.add(word);
        }

        Collections.sort(words);

        StringBuilder sb = new StringBuilder();
        for(Words w : words){
            sb.append(w.word).append("\n");
        }
        System.out.println(sb);
    }
}
