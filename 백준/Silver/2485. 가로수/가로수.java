import java.util.Scanner;



public class Main {
    static int gcd(int a, int b){
        while(b != 0){
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    static int gcdMulti(int[] arr){
        int result = arr[0];
        for(int i = 1; i < arr.length; i++){
            result = gcd(result, arr[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] distance = new int[n - 1];

        int before = sc.nextInt();
        int total = 0;

        for(int i = 0; i < n - 1; i++){
            int current = sc.nextInt();
            int d = current - before;
            before = current;
            distance[i] = d;
            total += d;
        }

        int gcd = gcdMulti(distance);

        int result = (total / gcd) - n + 1;
        System.out.println(result);


        // 1 3 7 13 -> 2 4 6 -> 2
        // 1 3 7 14 -> 2 4 7 -> 1
        // 1 10 31 46 -> 9 21 15 -> 3



    }
}