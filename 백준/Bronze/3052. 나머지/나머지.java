import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Set set = new HashSet();
        for(int i = 0; i < 10; i++){
            int k = sc.nextInt();
            int key = k % 42;
            set.add(key);
        }
        System.out.println(set.size());
    }
}