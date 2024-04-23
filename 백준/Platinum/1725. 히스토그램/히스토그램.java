import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] hist;

    static long getHist(int start, int end){
        if(start == end){
            return hist[start];
        }
        else{
            int mid = (end + start) / 2;
            long left = getHist(start, mid);
            long right = getHist(mid + 1, end);

            return Math.max(getArea(start, end, mid), Math.max(left, right));
        }
    }

    static long getArea(int start, int end, int mid){
        int left = mid;
        int right = mid;
        long height = hist[mid];
        long max = height;

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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        hist = new long[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            hist[i] = Long.parseLong(st.nextToken());
        }

        System.out.println(getHist(0, hist.length - 1));


    }
}
