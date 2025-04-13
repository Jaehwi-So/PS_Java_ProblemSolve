import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        char[] str1 = st.nextToken().toCharArray();
        st = new StringTokenizer(br.readLine());
        char[] str2 = st.nextToken().toCharArray();

        int len = str1.length;
        int alen = (int)Math.ceil(len / 2.0);
        int blen = len / 2;

        Character[] sa = new Character[len];
        Character[] sb = new Character[len];

        for(int i = 0; i < len; i++) sa[i] = str1[i];
        for(int i = 0; i < len; i++) sb[i] = str2[i];

        Arrays.sort(sa);
        Arrays.sort(sb, Collections.reverseOrder());

        Character[] a = new Character[alen];
        Character[] b = new Character[blen];
        for(int i = 0; i < alen; i++) a[i] = sa[i];
        for(int i = 0; i < blen; i++) b[i] = sb[i];

        char[] result = new char[len];

        int idxF = 0;
        int idxL = len - 1;
        int aF = 0;
        int aL = alen - 1;
        int bF = 0;
        int bL = blen - 1;

        for(int i = 0; i < len; i++){
            if(i % 2 == 0){
                if(bF == blen || a[aF] < b[bF]){
                    result[idxF++] = a[aF++];
                }
                else{
                    result[idxL--] = a[aL--];
                }
            }
            else{
                if(aF == alen || a[aF] < b[bF]){
                    result[idxF++] = b[bF++];
                }
                else{
                    result[idxL--] = b[bL--];
                }
            }
        }

        StringBuilder sbd = new StringBuilder();
        for(char ch : result){
            sbd.append(ch);
        }
        System.out.println(sbd);
    }
}