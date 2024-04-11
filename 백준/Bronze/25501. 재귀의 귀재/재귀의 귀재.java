import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int count = 0;
    static int recursion(char[] chs, int l, int r){

        count += 1;

        if(l >= r){
            return 1;
        }
        else if(chs[l] != chs[r]){
            return 0;
        }
        return recursion(chs, l+1, r-1);
    }

    static int isPalindrome(String s){
        char[] chs = s.toCharArray();
        return recursion(chs, 0, chs.length - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            count = 0;
            int result = isPalindrome(s);
            sb.append(result).append(" ").append(count).append("\n");
        }
        System.out.println(sb);

    }
}