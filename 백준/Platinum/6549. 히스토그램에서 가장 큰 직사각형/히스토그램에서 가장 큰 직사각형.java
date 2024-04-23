import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] hist;

    static long calc(int start, int end){
        if(start == end){
            return hist[start];
        }
        else{
            int mid = (start + end) / 2;
            long left = calc(start, mid);
            long right = calc(mid + 1, end);
            long result = Math.max(Math.max(left, right), getArea(start, end, mid));
            return result;
        }
    }

    static long getArea(int start, int end, int mid){
        long height = hist[mid];
        long max = height;
        int left = mid;
        int right = mid;

        while(start < left && end > right){
            if(hist[left - 1] > hist[right + 1]){
                left--;
                height = Math.min(height, hist[left]);
            }
            else{
                right++;
                height = Math.min(height, hist[right]);
            }

            int width = right - left + 1;

            max = Math.max(max, width * height);
        }

        while(start < left){
            left--;
            height = Math.min(height, hist[left]);
            int width = right - left + 1;
            max = Math.max(max, width * height);
        }

        while(end > right){
            right++;
            height = Math.min(height, hist[right]);
            int width = right - left + 1;
            max = Math.max(max, width * height);
        }

        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if(n == 0){
                break;
            }
            hist = new long[n];

            for(int i = 0; i < n; i++){
                hist[i] = Integer.parseInt(st.nextToken());
            }

            System.out.println(calc(0, hist.length - 1));
        }


    }
}