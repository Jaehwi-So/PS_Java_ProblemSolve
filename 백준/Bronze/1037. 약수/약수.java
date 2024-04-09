import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n];
        for(int i = 0; i < n; i++){
            numbers[i] = sc.nextInt();
        }
        Arrays.sort(numbers);
        int result = numbers[0] * numbers[n - 1];
        System.out.println(result);
    }
}
