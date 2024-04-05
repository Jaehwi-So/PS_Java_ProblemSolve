import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Set set = new HashSet<Integer>();
        for(int i = 0; i < n; i++){
            set.add(sc.nextInt());
        }
        int m = sc.nextInt();
        for(int i = 0; i < m; i++){
            int number = sc.nextInt();
            if(set.contains(number)){
                System.out.print(1 + " ");
            }
            else{
                System.out.print(0 + " ");
            }
        }


    }
}
