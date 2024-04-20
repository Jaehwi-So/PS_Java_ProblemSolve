import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[][] dist = new long[n][2];
        long[] price = new long[n];

        sc.nextLine();
        for(int i = 1; i < n; i++){
            dist[i][0] = sc.nextLong();
        }
        for(int i = 0; i < n; i++){
            price[i] = sc.nextLong();
        }

        dist[0][1] = Long.MAX_VALUE;
        long result = 0;
        for(int i = 1; i < n; i++){
            dist[i][1] =  Math.min(dist[i-1][1], price[i-1]);
            result += dist[i][0] * dist[i][1];
//            System.out.println(dist[i][1]);
        }
        System.out.println(result);

    }
}
