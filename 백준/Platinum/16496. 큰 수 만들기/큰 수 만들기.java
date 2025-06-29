import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Number implements Comparable<Number>{
    long number;
    int origin;
    int len;

    public Number(long number, int origin, int len){
        this.number = number;
        this.origin = origin;
        this.len = len;
    }
    public int compareTo(Number n){
        if(this.number == 0) return 1;
        if(n.number == 0) return -1;
        String s1 = Long.toString(n.number) + Long.toString(this.number);
        String s2 = Long.toString(this.number) + Long.toString(n.number);
        long n1 = Long.parseLong(s1.substring(0, Math.min(s1.length(), 10)));
        long n2 = Long.parseLong(s2.substring(0, Math.min(s2.length(), 10)));
        if(n1 > n2) return 1;
        else if(n1 < n2) return -1;
        else{
            if(s1.length() > 10){
                n1 = Long.parseLong(s1.substring(10, s1.length()));
                n2 = Long.parseLong(s2.substring(10, s2.length()));
                if(n1 > n2) return 1;
                else if(n1 < n2) return -1;
                else return 0;
            }
            else{
                return 0;
            }
        }

        /**
         * 10000000001000000000
         1. 233 < 2334 (2333 2334)     233 < 23335      23323335   23335233
         2. 233 > 2331 (2333 2331)     233 < 23331      23323331   23331233
         3. 233 < 2333 (2333 2333)
         4. 233 > 2332 (2333 2333) 2332332 2332233
         4. 211 < 2112 (2333 2333) 211 2112 2112 211
         4. 221 < 2212 (2333 2333) 2212 221 221 2212
         4. 231 > 2312 (2333 2333) 231 2312  2312 231

         4. 2211 > 21122 (2333 2333) 2112112 2112211
         4. 977 > 9777 (9777 9777)
         */
    }

    public String toString(){
        return Integer.toString(this.origin);
    }

}
public class Main {
    static int n;
    static Number[] array;
    static int maxLen = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        array = new Number[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            String s = st.nextToken();
            array[i] = new Number(Integer.parseInt(s), Integer.parseInt(s), s.length());
            maxLen = Math.max(s.length(), maxLen);
        }


        Arrays.sort(array);
//        System.out.println(Arrays.toString(array));

        if(array[0].origin == 0){
            System.out.println(0);
        }
        else{
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++){
                sb.append(array[i].origin);
            }
            System.out.println(sb);

        }

    }

}