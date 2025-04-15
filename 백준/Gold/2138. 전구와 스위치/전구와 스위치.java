import java.io.*;
import java.util.*;

public class Main {
    static char change(char ch){
        return ch == '0' ? '1' : '0';
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        char[] sequence = st.nextToken().toCharArray();
        st = new StringTokenizer(br.readLine());
        char[] answer = st.nextToken().toCharArray();

        char[] A = Arrays.copyOf(sequence, n);
        char[] B = Arrays.copyOf(sequence, n);
        B[0] = change(B[0]);
        B[1] = change(B[1]);
        int aCnt = 0;
        int bCnt = 1;

        for(int i = 1; i < n - 1; i++){
            if(A[i-1] != answer[i-1]){
                A[i-1] = change(A[i-1]);
                A[i] = change(A[i]);
                A[i+1] = change(A[i+1]);
                aCnt++;
            }

        }

        if(A[n-1] != answer[n-1]){
            A[n-1] = change(A[n-1]);
            A[n-2] = change(A[n-2]);
            aCnt++;
        }

        for(int i = 1; i < n - 1; i++){
            if(B[i-1] != answer[i-1]){
                B[i-1] = change(B[i-1]);
                B[i] = change(B[i]);
                B[i+1] = change(B[i+1]);
                bCnt++;
            }
        }

        if(B[n-1] != answer[n-1]){
            B[n-1] = change(B[n-1]);
            B[n-2] = change(B[n-2]);
            bCnt++;
        }

        boolean isA = true;
        boolean isB = true;
        for(int i = 0; i < n; i++){
            if(A[i] != answer[i]){
                isA = false;
            }
            if(B[i] != answer[i]){
                isB = false;
            }
        }

        int result = Integer.MAX_VALUE;
        if(isA) result = Math.min(result, aCnt);
        if(isB) result = Math.min(result, bCnt);
        if(result == Integer.MAX_VALUE) result = -1;
        System.out.println(result);
    }
}