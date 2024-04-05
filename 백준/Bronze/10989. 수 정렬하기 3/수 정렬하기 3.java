import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] numbers = new int[100001];

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            numbers[Integer.parseInt(br.readLine())]++;
        }

        for(int i = 0; i < numbers.length; i++){
            while(numbers[i] > 0){
                sb.append(i).append("\n");
                numbers[i]--;
            }
        }
        System.out.println(sb);
    }
}
