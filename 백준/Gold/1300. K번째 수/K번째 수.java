import java.util.Scanner;

public class Main {
    static int n;
    static int k;
    static int search(int start, int end){
        if(start >= end){
            return start;
        }
        int mid = (start + end) / 2;
        int count = 0;
        for(int i = 1; i <= n; i++){
            count += Math.min(mid / i, n);
        }

        if(count >= k){
            return search(start, mid);
        }
        else{
            return search(mid + 1, end);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        System.out.println(search(1, k));


    }
}
