import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();


        char[] chs = str.toCharArray();
        int[][] cnt = new int[chs.length + 1][26];
        //97~122
        for(int k = 0; k < chs.length; k++){
            for(int i = 97; i <= 122; i++){
                cnt[k+1][i - 97] = cnt[k][i - 97];
                if((int)chs[k] == i){
                    cnt[k+1][i - 97]++;
                }
            }
        }

//        int index = 0;
//        for(int[] cc : cnt){
//            System.out.print(index++ + " : ");
//            for(int k : cc){
//                System.out.print(k + " ");
//            }
//            System.out.println();
//        }


        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int charIdx = (int)c - 97;
            int result = cnt[r+1][charIdx] - cnt[l][charIdx];
            sb.append(result).append("\n");

        }
        System.out.println(sb);
    }
}
