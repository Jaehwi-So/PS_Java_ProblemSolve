import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Word implements Comparable<Word>{
    char ch;
    long count;
    public Word(char ch, long count){
        this.ch = ch;
        this.count = count;
    }

    @Override
    public int compareTo(Word o) {
        if(o.count > this.count){
            return -1;
        }
        else{
            return 1;
        }
    }
    
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Map<Character, Long> map = new HashMap<>();
        List<char[]> strings = new ArrayList<>();
        boolean[] isFirst = new boolean[10];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            char[] chs = st.nextToken().toCharArray();
            strings.add(chs);
            for(int j = chs.length - 1; j >= 0; j--){
                if(!map.containsKey(chs[j])){
                    map.put(chs[j], (long)Math.pow(10, (chs.length - 1 - j)));
                }
                else{
                    map.put(chs[j], map.get(chs[j]) + (long)Math.pow(10, (chs.length - 1 - j)));
                }
                if(j == 0){
                    isFirst[(int)chs[j] - 65] = true;
                }
            }
        }
        List<Word> list = new ArrayList<>();
        for(char c : map.keySet()){
            list.add(new Word(c, map.get(c)));
        }
        Collections.sort(list);
//        System.out.println(list);

        long result = 0;
        boolean zero = true;
        int element = 10 - list.size();
        if(list.size() == 10){
            zero = false;
            element += 1;
        }

        for(Word w : list){
            if(isFirst[(int)w.ch - 65] || zero == true){
                result += (w.count * element);
                element++;
            }
            else{
                zero = true;
            }
        }


        System.out.println(result);

    }
}

