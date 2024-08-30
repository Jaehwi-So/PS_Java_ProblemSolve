import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> list;
    static StringBuilder sb = new StringBuilder();

    static void postorder(int start, int end){
        int root = list.get(start);

        int leftRoot = -1;
        int rightRoot = -1;
        for(int i = start; i <= end; i++){
            if(leftRoot == -1 && list.get(i) < root){
                leftRoot = i;
            }
            if(rightRoot == -1 && list.get(i) > root){
                rightRoot = i;
            }
            if(leftRoot != -1 && rightRoot != -1) break;
        }

        if(leftRoot != -1){
            if(rightRoot == -1) postorder(leftRoot, end);
            else postorder(leftRoot, rightRoot - 1);
        }
        if(rightRoot != -1){
            postorder(rightRoot, end);
        }
        sb.append(root).append("\n");

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        list = new ArrayList<>();
        while((s = br.readLine()) != null && !s.isEmpty()){
            list.add(Integer.parseInt(s));
        }
        postorder(0, list.size() - 1);
        System.out.println(sb);

    }
}