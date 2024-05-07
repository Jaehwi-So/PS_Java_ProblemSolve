import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Integer[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //1~1000

        array = new Integer[n];
        st = new StringTokenizer(br.readLine());
        int max = 0;
        for(int i = 0; i < n ; i++){
            int k = Integer.parseInt(st.nextToken());
            array[i] = k;
            max += k;
        }

        Arrays.sort(array);
//        System.out.println(Arrays.toString(array));
        boolean[] numbers = new boolean[max + 2];
        numbers[0] = true;
        numbers[array[0]] = true;
        int total = array[0];

        /**
         * 5
         * 1 2 3 5 7
         */
        for(int i = 1; i < n; i++){

            // 해당 추 이하의 숫자는 모두 채워져 있어야 한다.
            int weight = array[i];
            int range = Math.max(total, weight - 1);
            for(int j = 0; j <= range; j++){
                if(numbers[j] == false){
                    System.out.println(j);
                    return;
                }
                numbers[weight + j] = true;
            }
//            System.out.println(weight + " " + Arrays.toString(numbers));
            total += weight;
        }

        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] == false){
                System.out.println(i);
                return;
            }
        }


    }
}
