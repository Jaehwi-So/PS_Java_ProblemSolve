import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        Queue<Integer> queue = new LinkedList();
        for(int i = 1; i <= n; i++){
            queue.add(i);
        }

        int count = 1;
        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()){
            if(count == k){
                list.add(queue.poll());
                count = 1;
            }
            else{
                int i = queue.poll();
                queue.add(i);
                count++;
            }

        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        for(int i = 0; i < list.size(); i++){
            sb.append(list.get(i));
            if(i != list.size() - 1){
                sb.append(", ");
            }
        }

        sb.append(">");
        System.out.println(sb);
    }
}
