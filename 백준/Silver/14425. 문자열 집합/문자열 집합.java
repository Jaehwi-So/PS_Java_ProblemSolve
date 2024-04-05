import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        Set set = new HashSet<String>();
        for(int i = 0; i < n; i++){
            set.add(sc.nextLine());
        }

        int cnt = 0;
        for(int i = 0; i < m; i++){
            String str = sc.next();
            if(set.contains(str)){
                cnt++;
            }
        }
        System.out.println(cnt);


    }
}