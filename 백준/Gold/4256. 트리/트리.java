import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] preorder;
    static int[] inorder;
    static StringBuilder sb = new StringBuilder();

    static int getIndex(int node, int left, int right){
        for(int i = left; i <= right; i++){
            if(inorder[i] == node){
                return i;
            }
        }
        return -1;
    }

    static void postorder(int i_rootIdx, int i_left, int i_right, int p_left){
//        System.out.println(i_rootIdx + " " + i_left + " " + i_right);
        if(i_left != i_right){
            int nextIleft1 = i_left;
            int nextIright1 = i_rootIdx - 1;
            if(nextIleft1 <= nextIright1){
                int nextRoot1 = getIndex(preorder[p_left + 1], nextIleft1, nextIright1);
                postorder(nextRoot1, nextIleft1, nextIright1, p_left + 1);
            }

            int len = nextIright1 - nextIleft1 + 1;  // 0  1 2 3  4

            int nextIleft2 = i_rootIdx + 1;
            int nextIright2 = i_right;
            if(nextIleft2 <= nextIright2){
                int nextRoot2 = getIndex(preorder[p_left + 1 + len], nextIleft2, nextIright2);
                postorder(nextRoot2, nextIleft2, nextIright2, p_left + len + 1);
            }
        }

        sb.append(inorder[i_rootIdx]).append(" ");
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int test = Integer.parseInt(st.nextToken());

        for(int t = 0; t < test; t++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            preorder = new int[n];
            inorder = new int[n];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                preorder[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                inorder[i] = Integer.parseInt(st.nextToken());
            }

            int root = getIndex(preorder[0], 0, n-1);
            postorder(root, 0, n-1, 0);
            sb.append("\n");

        }

        System.out.println(sb);

    }
}