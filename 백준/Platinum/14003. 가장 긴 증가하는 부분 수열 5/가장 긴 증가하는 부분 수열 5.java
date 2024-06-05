import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int binarySearch(int number, int size, int[] array){
        int left = 0;
        int right = size;
        while(left < right){
            int mid = (left + right) / 2;
            if(array[mid] == number){
                return mid - 1;
            }
            else if(array[mid] > number){
                right = mid;
            }
            else{
                left = mid + 1;
            }
        }
        return left - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] dp = new int[n+1];
        int[] numbers = new int[n+1];
        int[] numIndex = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        int max = 0;
        int maxIndex = 0;
        for(int i = 1; i <= n; i++){
            int number = Integer.parseInt(st.nextToken());
            numbers[i] = number;
            int beforeIdx = binarySearch(number, max + 1, dp);
            int index = beforeIdx + 1;
            dp[index] = Math.min(dp[index], number);
            numIndex[i] = index;
            if(index > max){
                max = index;
                maxIndex = i;
            }
        }

        int currentIndex = max;
        Stack<Integer> stack = new Stack<>();
        for(int i = maxIndex; i >= 1; i--){
            if(currentIndex == 0){
                break;
            }
            if(currentIndex == numIndex[i]){
                stack.push(numbers[i]);
                currentIndex--;
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }

//        System.out.println(Arrays.toString(numIndex));
//        System.out.println(Arrays.toString(dp));
        System.out.println(max);
        System.out.println(sb);

    }
}