import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] xarr = new int[n];
        int[] yarr = new int[n];

        for(int i = 0; i < n; i ++){
            xarr[i] = sc.nextInt();
            yarr[i] = sc.nextInt();
        }
        Arrays.sort(xarr);
        Arrays.sort(yarr);

        int width = Math.abs(xarr[0] - xarr[n - 1]);
        int height = Math.abs(yarr[0] - yarr[n - 1]);
        System.out.println(width * height);
    }
}
