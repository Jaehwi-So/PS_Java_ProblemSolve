import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int min = Integer.MAX_VALUE;
        for(int i = 0; i <= n / 3; i++){
            for(int j = 0; j <= n / 3; j++){
                if(i * 5 + j * 3 == n && i + j < min){
                    min = i + j;
                }
            }
        }
        if(min == Integer.MAX_VALUE){
            min = -1;
        }
        System.out.println(min);
    }
}
