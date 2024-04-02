import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 0;
        for(int i = 0; i < 10000666; i++){
            String s = Integer.toString(i);
            if(s.contains("666")){
                count++;
            }
            if(count == n){
                System.out.println(s);
                return;
            }

        }
    }
}