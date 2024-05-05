import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] house;
    static int n;
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        house = new int[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            house[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(house);


        // 최소 간격을 범위로 잡음
        int left = 1;
        int right = house[n-1] - house[0] + 1;
        while(left < right){
            int mid = (left + right) / 2;
            int calc = getAvailableCount(mid); // 해당 간격으로 공유기를 몇 개 설치할 수 있는 지 확인하기
            if(calc >= c){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }

        int upperBound = right - 1;
        System.out.println(upperBound);
    }

    static int getAvailableCount(int distance){
        int before = house[0];
        int count = 1;
        for(int i = 1; i < n; i++){
            if(before + distance <= house[i]){
                before = house[i];
                count++;
            }
        }
        return count;
    }
}
