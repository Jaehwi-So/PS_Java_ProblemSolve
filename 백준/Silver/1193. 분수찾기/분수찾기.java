import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();

        int step = 1;
        while(x > step){
            x -= step;
            step++;
        }

        int i = x;
        int j = step + 1 - i;

        if(step % 2 != 0){
            int tmp = i;
            i = j;
            j = tmp;
        }

        System.out.println(i + "/" + j);
    }
}

// 3/2
/**
 * 1  2  6  7  15
 * 3  5  8  14
 * 4  9  13
 * 10 12
 * 11
 *
 * 2 3 4 5
 * 3 4 5
 * 4 5
 * 5
 */

