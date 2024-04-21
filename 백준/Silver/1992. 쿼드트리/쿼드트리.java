import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int check(int[][] array, int startX, int startY, int endX, int endY){
        if(startX >= endX || startY >= endY){
            return array[startY][startX];
        }
        for(int i = startY; i <= endY; i++){
            for(int j = startX; j <= endX; j++){
                if(array[i][j] != array[startY][startX]){
                    return -1;
                }
            }
        }
        return array[startY][startX];
    }
    static void split(int[][] array, int startX, int startY, int endX, int endY){
        int ck = check(array, startX, startY, endX, endY);

        if(ck == -1){
            sb.append("(");
            int midX = startX + ((endX - startX) / 2);
            int midY = startY + ((endY - startY) / 2);
            split(array, startX, startY, midX, midY);
            split(array, midX + 1, startY, endX, midY);
            split(array, startX, midY + 1, midX, endY);
            split(array, midX + 1, midY + 1, endX, endY);
            sb.append(")");
        }
        else{
            sb.append(ck);
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] array = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            char[] chs = s.toCharArray();
            for(int j = 0; j < n; j++){
                array[i][j] = Character.getNumericValue(chs[j]);
            }
        }

        split(array, 0, 0, n-1, n-1);
        System.out.println(sb);



    }
}
