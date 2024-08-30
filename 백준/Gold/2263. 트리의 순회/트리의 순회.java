import java.io.*;
import java.util.*;

public class Main {
    static int[] inorder;
    static int[] postorder;
    static StringBuilder sb = new StringBuilder();

    static void preorder(int iStart, int iEnd, int pStart, int pEnd){
        int root = postorder[pEnd];
        sb.append(root).append(" ");
        if(iStart < iEnd){
            for(int pivot = iStart; pivot <= iEnd; pivot++){
                if(root == inorder[pivot]){
                    int left = pivot - iStart;
                    int right = iEnd - pivot;
                    if(left > 0){
                        preorder(iStart, pivot - 1, pStart, pStart + left - 1);
                    }
                    if(right > 0){
                        preorder(pivot + 1, iEnd, pStart + left, pEnd - 1);
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        inorder = new int[n];
        postorder = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            inorder[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        preorder(0, inorder.length - 1, 0, postorder.length - 1);
        System.out.println(sb);
    }
}