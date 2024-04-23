import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static char[] list;
    static Character[] temp;

    static StringBuilder sb = new StringBuilder();

    static List<String> result = new ArrayList();

    static Character[] words = {'a', 'e', 'i', 'o', 'u'};
    static List<Character> wordsList = Arrays.asList(words);

    static boolean isPromising(int step){
        for(int i = 0; i < step - 1; i++){
            for(int j = i + 1; j < step; j++){
                if(temp[i].compareTo(temp[j]) >= 0){
                    return false;
                }
            }
        }
        return true;
    }

    static boolean check(){
        int cntM = 0;
        int cntJ = 0;

        for(int i = 0; i < n; i++){
            if(wordsList.contains(temp[i])){
                cntM++;
            }
            else{
                cntJ++;
            }
        }

        if(cntM > 0 && cntJ > 1){
            return true;
        }
        else{ 
            return false;
        }

    }

    static void calc(int step){
        if(isPromising(step)){
            if(step == n){
                if(check()){
                    sb.setLength(0);
                    for(Character ch : temp){
                        sb.append(ch);
                    }
                    result.add(sb.toString());
                }
            }
            else{
                for(int i = 0; i < m; i++){
                    temp[step] = list[i];
                    calc(step + 1);
                    temp[step] = null;
                }
            }
        }

    }

    // a c i s t w

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        list = new char[m];
        temp = new Character[n];

        for(int i = 0; i < m; i++){
            list[i] = st.nextToken().charAt(0);
        }

        calc(0);

        Collections.sort(result);
        sb.setLength(0);
        for(int i = 0; i < result.size(); i++){
            sb.append(result.get(i)).append("\n");
        }
        System.out.println(sb);


    }
}
