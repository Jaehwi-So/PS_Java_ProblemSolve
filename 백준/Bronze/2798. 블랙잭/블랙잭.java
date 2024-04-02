import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] cards = new int[n];

        for(int i = 0; i < n; i++){
            cards[i] = sc.nextInt();
        }

        int sum = 0;
        for(int i = 0; i < n - 2; i++){
            for(int j = i; j < n - 1; j++){
                for(int k = j; k < n; k++){
                    if(i == j || i == k || j == k){
                        continue;
                    }
                    int tmp = cards[i] + cards[j] + cards[k];
                    if(m - sum > m - tmp && m >= tmp){
                        sum = tmp;
                    }
                }
            }
        }

        System.out.println(sum);
    }
}
