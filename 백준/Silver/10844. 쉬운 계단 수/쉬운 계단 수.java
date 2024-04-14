import java.util.Scanner;


// 1 -> 9
// 2 -> 10 12 21 23 32 34 43 45 54 56 65 67 76 78 87 89 98 = 17 = 8 + 8 + 1
// 3 -> 212 121 123 321 323 .. 898 = 4 * 7 = 28 + 2

public class Main {
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[][] arr = new int[n][12];
        arr[0][0] = 0;
        arr[0][1] = 0;
        arr[0][11] = 0;
        for(int i = 2; i <= 10; i++){
            arr[0][i] = 1;
        }
        for(int i = 1; i < n; i++){
            arr[i][0] = 0;
            arr[i][11] = 0;
            for(int j = 1; j <= 10; j++){
                arr[i][j] = (arr[i-1][j-1] + arr[i-1][j+1]) % 1000000000;
            }
        }

        int num = 0;
        for(int j = 1; j <= 10; j++){
            num += arr[n-1][j];
            num = num % 1000000000;
        }
        System.out.println(num);

    }
}
