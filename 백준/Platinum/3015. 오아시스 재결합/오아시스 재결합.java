import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
//
//        st = new StringTokenizer(br.readLine());
        long[] array = new long[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            array[i] = Long.parseLong(st.nextToken());
        }

        Stack<Long> stack = new Stack<>();
//        Queue<Long> queue = new LinkedList<>();
        Stack<Long> temp = new Stack<>();
        long count = 0;

        Map<Long, Long> map = new HashMap<>();
        for(int i = 0; i < n; i++){

            long k = array[i];
            while(!stack.isEmpty() && stack.peek() < k){
                long l = stack.pop();
                map.replace(l, map.get(l) - 1);
                count++;
            }

//            System.out.println(stack + " " + (stack.isEmpty() ? -1 : stack.peek()));
//            count += stack.size();

//            while(!stack.isEmpty() && stack.peek() == k){
//                temp.push(stack.pop());
//                count += 1;
//            }
//

            if(!stack.isEmpty()){
                if(map.containsKey(k) && stack.peek() == k){
                    long sameCnt = map.get(k);
                    count += sameCnt;
                    if(stack.size() > sameCnt){
                        count++;
                    }
                }

                else{
                    count++;
                }


            }



//            while(!temp.isEmpty()){
//                stack.push(temp.pop());
//            }

            stack.push(k);
            if(map.containsKey(k)){
                map.replace(k, map.get(k) + 1L);
            }
            else{
                map.put(k, 1L);
            }
//            System.out.println(stack + " " + count);
        }

        System.out.println(count);
    }
}
