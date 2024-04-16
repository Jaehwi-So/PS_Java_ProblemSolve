import java.util.Scanner;

public class Main {
    static int[] numbers;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        numbers = new int[n + 1];

        numbers[1] = 0;

        for(int i = 2; i <= n; i++){
            numbers[i] = numbers[i - 1] + 1;
            if(i % 3 == 0){
                numbers[i] =Math.min(numbers[i], numbers[i / 3] + 1);
            }
            if(i % 2 == 0){
                numbers[i] = Math.min(numbers[i], numbers[i / 2] + 1);
            }
        }

        System.out.println(numbers[n]);
    }
}

