import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int getMaxIndex(int[] array, int startIndex, int endIndex){
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for(int i = startIndex; i <= endIndex; i++){
            if(max < array[i]){
                maxIndex = i;
                max = array[i];
            }
        }
        return maxIndex;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] array = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());


        int startIndex = 0;


        while(k > 0){
            int endIndex = Math.min(n - 1, startIndex + k);
            int maxIdx = getMaxIndex(array, startIndex, endIndex);
            if(maxIdx == -1){
                break;
            }

            for(int i = maxIdx; i > startIndex; i--){
                int swap = array[i];
                array[i] = array[i-1];
                array[i-1] = swap;
                k--;
            }
            startIndex++;
//            System.out.println(maxIdx + " " + startIndex + " " + endIndex + " " + k);
//            System.out.println(Arrays.toString(array));


        }

        StringBuilder sb = new StringBuilder();
        for(int i : array){
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}