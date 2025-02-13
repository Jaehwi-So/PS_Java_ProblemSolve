import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] array;
    static int move;
    static int p;
    static int q;
    static int move2;

    static void firstRightMove(){
        int len = n*2 - 1;
        int[] temp = new int[len + 1]; //10 -> 18 = 19
        for(int i = 1; i <= n; i++){
            temp[i] = array[i];
        }
        for(int i = n + 1; i <= len; i++){
            temp[i] = array[i-n];
        }

        int max = 0;
        int idx = 0;
        for(int i = 1; i <= n; i++){
            int l = 0;
            for(int j = i + 1; j <= i + n - 1; j++){
                if(temp[j-1] - 1 == temp[j]){
                    l++;
                }
                else if(temp[j-1] == 1 && temp[j] == n){
                    l++;
                }
                else{
                    break;
                }
            }
            if(l > max){
                max = l;
                idx = i;
            }
        }
//        System.out.println(Arrays.toString(temp));
//        System.out.println(idx + "(" +temp[idx] + ")" + " " + (idx + max) + "(" +temp[idx + max] + ")" );
        int start = idx;
        int end = idx+max;
        int range = max;
        if(range + 1 == n){ //전체수열이 뒤바뀌어있을경우
            move = 1; //오른쪽으로 1칸 이동
            if(temp[n-1] == 1) move++;

        }
        else if(end > n){ // 떨어져 있는 경우
//            System.out.println("this case");
            move = n - start + 1; //뒤집자마자 원래대로되면
            if(temp[end] == 1) move++;
        }
        else{ // 붙어있는 경우
            move = 1; //오른쪽으로 1칸 이동
            if(end + move > n) move = n - start + 1;
            int next = start + move;
            if(next > n) next %= n;
            if(temp[end] == next){ //뒤집자마자 원래대로되면
                move++;
            }
        }


        int[] moveArray = new int[n+1];
        for(int i = 0; i <= n; i++){
            int next = i+move;
            if(next > n) next %= n;
            moveArray[next] = array[i];
        }

//        System.out.println(Arrays.toString(moveArray));
        array = moveArray;

        //오른쪽 이동 :
        // if(idx+max > n) idx ~ n까지, array[1] = array[start], array[1 + range] = array[end]
        // else 적당히 (1이동 or 2이동)


    }

    static void reverse(){
        int max = 0;
        int idx = 0;
        for(int i = 1; i <= n; i++){
            int l = 0;
            for(int j = i + 1; j <= n; j++){
                if(array[j-1] - 1 == array[j]){
                    l++;
                }
                else if(array[j-1] == 1 && array[j] == n){
                    l++;
                }
                else{
                    break;
                }
            }
            if(l > max){
                max = l;
                idx = i;
            }
        }

        p = idx;
        q = idx + max;

        int[] temp = new int[n+1];
        temp = Arrays.copyOf(array, n+1);

        for(int i = 0; i <= q-p; i++){
            temp[p+i] = array[q-i];
        }

        array = temp;
//        System.out.println(Arrays.toString(temp));
    }

    static void secondRightMove(){
        int idx = 0;
        for(int i = 1; i <= n; i++){
            if(array[i] == n){
                idx = i;
                break;
            }
        }

        move2 = n - idx;

        int[] temp = new int[n+1];
        for(int i = 1; i <= n; i++){
            int next = i + move2;
            if(next > n) next %= 10;
            temp[next] = array[i];
        }
//        System.out.println(Arrays.toString(temp));


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        array = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        firstRightMove();
        reverse();
        secondRightMove();



        System.out.println(move2);
        System.out.println(p + " " + q);
        System.out.println(move);


    }
}