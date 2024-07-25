
import java.io.*;
import java.util.*;

class Wheel{
    int index;
    int[] sequence;
    Wheel before;
    Wheel after;

    // 1 : 시계방향, -1 : 반시계방향
    public void rotate(int direction){
        if(Main.isRotate[index]){
            return;
        }

        Main.isRotate[index] = true;
        if(after != null && sequence[2] != after.sequence[6]){
            after.rotate(direction * -1);
        }
        if(before != null && sequence[6] != before.sequence[2]){
            before.rotate(direction * -1);
        }
        if(direction == 1){
            int temp = sequence[7];
            for(int i = 6; i >= 0; i--){
                sequence[i+1] = sequence[i];
            }
            sequence[0] = temp;
        }
        else if(direction == -1){
            int temp = sequence[0];
            for(int i = 1; i < 8; i++){
                sequence[i-1] = sequence[i];
            }
            sequence[7] = temp;
        }
    }
}

public class Main {
    static boolean[] isRotate;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Wheel[] wheels = new Wheel[4];
        for(int i = 0; i < 4; i++){
            st = new StringTokenizer(br.readLine());
            Wheel wheel = new Wheel();
            char[] chs = st.nextToken().toCharArray();
            int[] sequence = new int[8];
            for(int j = 0; j < 8; j++){
                sequence[j] = Character.getNumericValue(chs[j]);
            }
            wheel.index = i;
            wheel.sequence = sequence;
            wheel.before = null;
            wheel.after = null;
            if(i >= 1){
                wheel.before = wheels[i-1];
                wheels[i-1].after = wheel;
            }
            wheels[i] = wheel;
        }
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken()) - 1;
            int direct = Integer.parseInt(st.nextToken());
            isRotate = new boolean[4];
            wheels[index].rotate(direct);
        }

        int poo = 1;
        int result = 0;
        for(Wheel wheel : wheels){
            if(wheel.sequence[0] == 1){
                result += poo;
            }
            poo *= 2;
        }

        System.out.println(result);
    }
}