import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dice = new int[7];


        long n = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int maxOne = Integer.MIN_VALUE;
        int minOne = Integer.MAX_VALUE;
        int minTwo = Integer.MAX_VALUE;
        int minThree = Integer.MAX_VALUE;

        for(int i = 1; i <= 6; i++){
            dice[i] = Integer.parseInt(st.nextToken());
            minOne = Math.min(minOne, dice[i]);
            maxOne = Math.max(maxOne, dice[i]);
        }

        for(int i = 1; i <= 6; i++){
            for(int j = 1; j <= 6; j++){
                if(i != j && i + j != 7){
                    minTwo = Math.min(minTwo, dice[i] + dice[j]);
                }

                for(int k = 1; k <= 6; k++){
                    if((i == k || j == k || i == j)){
                        continue;
                    }
                    else if(i + j + k >= 8 && i + j + k <= 13){
                        if(i + j == 7 || j + k == 7 || k + i == 7){
                            continue;
                        }

                    }
                    minThree = Math.min(minThree, dice[i] + dice[j] + dice[k]);
                }
            }
        }

//        System.out.println(minOne);
//        System.out.println(minTwo);
//        System.out.println(minThree);

        if(n == 1){
            long result = 0;
            for(int i = 1; i <= 6; i++){
                result += dice[i];
            }
            result -= maxOne;
            System.out.println(result);
        }
        else{
            long twoNumber = (4L * (n-1)) + ((n * n) - 4 - ((n - 2) * (n - 2)));
            long threeNumber = 4L;
            long oneNumber = (long)((n * n) - ((n - 2) * (n - 2)) - 4) * (n - 1) + (n - 2) * (n - 2);

//            System.out.println(oneNumber);
//            System.out.println(twoNumber);
//            System.out.println(threeNumber);

            long result = (oneNumber * minOne) + (twoNumber * minTwo) + (threeNumber * minThree);
            System.out.println(result);
        }


    }
}