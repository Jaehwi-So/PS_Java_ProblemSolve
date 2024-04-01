import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[] sample = new int[n];
        for(int i = 0; i < n; i++){
            sample[i] = sc.nextInt();
        }

        int[] money = {25, 10, 5, 1};
        for(int s : sample){
            for(int m : money){
                System.out.print((s / m) + " ");
                s = s % m;
            }
            System.out.println();
        }

    }
}