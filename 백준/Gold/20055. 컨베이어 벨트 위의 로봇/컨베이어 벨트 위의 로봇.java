import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int k;
    static int[] array;
    static boolean[] robot;
    static void rotate(){
        int temp = array[n*2];
        for(int i = n*2; i >= 2; i--){
            array[i] = array[i-1];
        }
        for(int i = n; i >= 2; i--){
            robot[i] = robot[i-1];
        }
        robot[n] = false;
        robot[1] = false;
        array[1] = temp;
    }

    static void move(){
        for(int i = n; i >= 2; i--){
            if(robot[i] == false && robot[i-1] == true && array[i] > 0){
                robot[i] = robot[i-1];
                robot[i-1] = false;
                array[i]--;
            }
        }
        robot[n] = false;
    }

    static boolean check(){
        int cnt = 0;
        for(int i = 1; i <= n*2; i++){
            if(array[i] == 0){
                cnt++;
            }
        }
        if(cnt >= k) return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        array = new int[(n*2) + 1];
        robot = new boolean[n + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n*2; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }
        int step = 1;
        while(true){
            rotate();
            move();
            if(array[1] > 0){
                robot[1] = true;
                array[1]--;
            }
            if(check()) break;
            step++;
        }
        System.out.println(step);
    }
}