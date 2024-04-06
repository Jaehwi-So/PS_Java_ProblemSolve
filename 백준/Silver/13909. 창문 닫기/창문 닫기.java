import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int count = 0;
        for(int i = 1; i * i <= n; i++){
            count++;
        }
        System.out.println(count);

    }
}

// (1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
// (1, 0, 1, 0, 1, 0, 1, 0, 1, 0)
// (1, 0, 0, 0, 1, 1, 1, 0, 0, 0)
// 1, 0, 0, 1, 1, 1, 1, 1, 0, 0)
// 1, 0, 0, 1, 0, 1, 1, 1, 0, 1

// 1, 0, 0, 1, 0, 0 ,0, 0, 1, 0
// 결론 : 제곱수 1, 3, 9 ..


// (1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
// (1, 0, 1, 0, 1, 0, 1, 0, 1, 0)
// (1, 0, 0, 0, 1, 1, 1, 0, 0, 0)
// 1, 0, 0, 1, 1, 1, 1, 1, 0, 0)
// 1, 0, 0, 1, 0, 1, 1, 1, 0, 1

// 1, 0, 0, 1, 0, 0 ,0, 0, 1, 0
