
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Register{
    char[] numbers;
    String commands;

    public Register(char[] numbers, String commands){
        this.numbers = numbers;
        this.commands = commands;
    }


}
public class Main {
    static int target;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    static void bfs(Register start){
        Queue<Register> queue = new LinkedList<>();
        visited[getNumber(start.numbers)] = true;
        queue.offer(start);

        while(!queue.isEmpty()){
            Register current = queue.poll();
            int currentNum = getNumber(current.numbers);

            if(currentNum == target){
                sb.append(current.commands).append("\n");
                return;
            }

            int D = (currentNum * 2) % 10000;
            if(!visited[D]){
                queue.offer(new Register(makrCharArray(D), current.commands + "D"));
                visited[D] = true;
            }

            int S = (currentNum - 1);
            if(currentNum == 0){
                S = 9999;
            }
            if(!visited[S]){
                queue.offer(new Register(makrCharArray(S), current.commands + "S"));
                visited[S] = true;
            }

            int L = getNumber(rotateLeft(current.numbers));
            if(!visited[L]){
                queue.offer(new Register(makrCharArray(L), current.commands + "L"));
                visited[L] = true;
            }

            int R = getNumber(rotateRight(current.numbers));
            if(!visited[R]){
                queue.offer(new Register(makrCharArray(R), current.commands + "R"));
                visited[R] = true;
            }
        }
    }


    static char[] makrCharArray(int k){
        char[] result = new char[4];
        Arrays.fill(result, '0');
        int poo = 1000;
        for(int i = 0; i < 4; i++){
            result[i] = (char)((k / poo) + '0');
            k %= poo;
            poo /= 10;
        }
        return result;
    }

    static int getNumber(char[] chs){
        int result = 0;
        int poo = 1;
        for(int i = chs.length - 1; i >= 0; i--){
            result += (Character.getNumericValue(chs[i]) * poo);
            poo *= 10;
        }
        return result;
    }

    static char[] rotateLeft(char[] chs){
        char[] temp = Arrays.copyOf(chs, chs.length);
        char tmp = chs[0];
        for(int i = 0; i < temp.length - 1; i++){
            temp[i] = temp[i+1];
        }
        temp[3] = tmp;
        return temp;
    }

    static char[] rotateRight(char[] chs){
        char[] temp = Arrays.copyOf(chs, chs.length);
        char tmp = chs[3];
        for(int i = 2; i >= 0; i--){
            temp[i+1] = temp[i];
        }
        temp[0] = tmp;
        return temp;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        for(int l = 0; l < t; l++){
            st = new StringTokenizer(br.readLine());
            char[] register = makrCharArray(Integer.parseInt(st.nextToken()));
            target = Integer.parseInt(st.nextToken());
            visited = new boolean[20000];
            bfs(new Register(register, ""));


        }
        
        System.out.println(sb);

    }
}
