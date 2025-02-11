import java.io.*;
import java.util.*;

public class Main {
    static int len;
    static Integer[] numbers;
    static int[] deletes = new int[10];
    static int[] counts = new int[10];
    static int[] maxNum = new int[10];

    static int getMaxIndex(int start){
        int max = -1;
        int idx = -1;
        int[] temp = Arrays.copyOf(deletes, 10);
        for(int i = start; i < len; i++){

            if(max < numbers[i]){
                if(counts[numbers[i]] < maxNum[numbers[i]]){
                    max = numbers[i];
                    idx = i;
                }
            }
            if(temp[numbers[i]] == 0){
                break;
            }
            temp[numbers[i]]--;
        }

        for(int i = start; i < idx; i++){
            deletes[numbers[i]]--;
        }
        return idx;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] a = st.nextToken().toCharArray();
        st = new StringTokenizer(br.readLine());
        char[] b = st.nextToken().toCharArray();
        len = a.length;
        numbers = new Integer[len];

        for(int i = 0; i < len; i++){
            numbers[i] = Character.getNumericValue(a[i]);
            maxNum[numbers[i]]++;
        }
        for(int i = 0; i < b.length; i++){
            deletes[Character.getNumericValue(b[i])]++;
            maxNum[Character.getNumericValue(b[i])]--;
        }

//        System.out.println(Arrays.toString(maxNum));
        int start = 0;
        StringBuilder sb = new StringBuilder();
        while(start < len){
            int maxIdx = getMaxIndex(start);
            if(maxIdx == -1) break;
            counts[numbers[maxIdx]]++;
            sb.append(numbers[maxIdx]);
//            System.out.println(Arrays.toString(deletes));
            start = maxIdx + 1;
        }
        System.out.println(sb);

    }
}