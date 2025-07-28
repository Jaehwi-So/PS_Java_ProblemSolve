import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] array;
    static int result = 0;

    static void operate1(int index, int count){
        array[index] -= count;
        result += (count * 3);
    }
    static void operate2(int index, int count){
        array[index] -= count;
        array[index+1] -= count;
        result += (count * 5);
    }

    static void operate3(int index, int count){
        array[index] -= count;
        array[index+1] -= count;
        array[index+2] -= count;
        result += (count * 7);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        array = new int[n+2];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i++){
            if(array[i+1] > array[i+2]){ //다음 공장이 그 다음 공장보다 수량이 많은 경우 2개연산 먼저해서 수량 맞추기
                //2개 감소
                operate2(i, Math.min(array[i], array[i+1] - array[i+2]));

                //3개 감소
                operate3(i, Math.min(array[i], Math.min(array[i+1], array[i+2])));

                //1개 감소
                operate1(i, array[i]);
            }
            else{
                //3개 감소
                operate3(i, Math.min(array[i], Math.min(array[i+1], array[i+2])));

                //2개 감소
                operate2(i, Math.min(array[i], array[i+1]));

                //1개 감소
                operate1(i, array[i]);
            }
        }

        System.out.println(result);

    }
}