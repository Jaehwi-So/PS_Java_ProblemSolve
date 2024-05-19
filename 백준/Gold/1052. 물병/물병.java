import java.io.IOException;
import java.util.Scanner;

public class Main {

    static int calc(int max){
        int temp = 1;
        while(temp <= max){
            temp *= 2;
        }
        if(temp > max){
            temp /= 2;
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int last = n;

        if(n <= k){
            System.out.println(0);
            return;
        }

        for(int i = 1; i < k; i++){
            int temp = calc(last);
            last = last - temp;
            if(last == 0){
                System.out.println(0);
                return;
            }
//            System.out.println(last);
        }

        int temp = 1;
        while(temp < last){
            temp *= 2;
        }


        System.out.println(temp - last);
    }
}
