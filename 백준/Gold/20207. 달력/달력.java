import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Time implements Comparable<Time>{
    int start;
    int end;
    public Time(int start, int end){
        this.start = start;
        this.end = end;
    }

    public int compareTo(Time t){
        return this.start - t.start;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Time[] array = new Time[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            array[i] = new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(array);

        int sum = 0;
        int start = array[0].start;
        int end = array[0].end;
        int[] matrix = new int[1001];
        for(int j = array[0].start; j <= array[0].end; j++){
            matrix[j]++;
        }

        for(int i = 1; i <= n; i++){
            if(i == n || array[i].start > end + 1){
                int max = Integer.MIN_VALUE;
                for(int j = start; j <= end; j++){
                    max = Math.max(max, matrix[j]);
                }
                sum += (end - start + 1) * max;
                if(i == n){
                    break;
                }
                start = array[i].start;
            }

            end = Math.max(end, array[i].end);
            for(int j = array[i].start; j <= array[i].end; j++){
                matrix[j]++;
            }
        }

        System.out.println(sum);

    }
}