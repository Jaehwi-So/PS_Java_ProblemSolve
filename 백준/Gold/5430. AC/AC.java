import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n; i++){
            //RDD
            st = new StringTokenizer(br.readLine());
            String cStr = st.nextToken();
            char[] command = cStr.toCharArray();

            //4
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            Deque<Integer> deque = new ArrayDeque<>();

            //[1,2,3,4]
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            s = s.substring(1, s.length() - 1);
            String[] numbers;
            if(m > 0){
                numbers = s.split(",");
                for(int j = 0; j < m; j++){
                    int number = Integer.parseInt(numbers[j]);
                    deque.addFirst(number);
                }
            }

            //Command
            boolean reverse = false;
            boolean error = false;
            for(int j = 0; j < command.length; j++){
                if(command[j] == 'R'){
                    reverse = !reverse;
                }
                else{
                    if(deque.isEmpty()){
                        error = true;
                        break;
                    }
                    else if(reverse == true){
                        deque.removeFirst();
                    }
                    else{
                        deque.removeLast();
                    }
//                    System.out.println(deque);
                }
            }

            if(error){
                sb.append("error");
            }
            else{
                sb.append("[");
                while(!deque.isEmpty()){
                    if(reverse == true){
                        sb.append(deque.removeFirst());
                    }
                    else{
                        sb.append(deque.removeLast());
                    }
                    if(!deque.isEmpty()){
                        sb.append(",");
                    }
                }
                sb.append("]");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
}
