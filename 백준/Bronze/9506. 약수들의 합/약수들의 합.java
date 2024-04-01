import java.util.*;

class Main {
    static void calc(int a){
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= a / 2; i++){
            if(a % i == 0){
                list.add(i);
            }
        }

        int sum = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(a + " = ");
        for(int i = 0; i < list.size(); i++){
            int k = list.get(i);
            sb.append(k);
            if(i != list.size() - 1){
                sb.append(" + ");
            }
            sum += k;

        }
        if(sum != a){
            System.out.println(a + " is NOT perfect.");
        }
        else{
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            int a = sc.nextInt();
            if(a == -1){
                break;
            }
            calc(a);
        }
    }
}
