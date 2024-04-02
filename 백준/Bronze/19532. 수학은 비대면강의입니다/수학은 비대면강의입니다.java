import java.util.*;

class Main {
    public static void main(String[] args) {
        int a, b, c, d, e, f;
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        d = sc.nextInt();
        e = sc.nextInt();
        f = sc.nextInt();

        for(int i = -999; i <= 999; i++){
            for(int j = -999; j <= 999; j++){
                if(((a-d) * i) + ((b-e) * j) == (c-f) && (a * i) + (b * j) == c){
                    System.out.println(i + " " + j);
                    return;
                }
            }
        }

        /**
         * x + 3y = -1
         * 4x + y = 7
         * -3x + 2y = -8
         */
    }
}
