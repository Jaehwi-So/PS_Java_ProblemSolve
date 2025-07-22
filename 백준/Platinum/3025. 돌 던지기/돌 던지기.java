import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int k;
    static int[] actions;
    static char[][] matrix;
    static List<int[]>[] blocks;
    static Stack<int[]>[] stacks;

    static int binarySearch(int row, int col){
        List<int[]> target = blocks[col];
        int left = 0;
        int right = target.size();
        while(left < right){
            int mid = (left + right) / 2;
            int current = target.get(mid)[0];
            if(current <= row){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }
        return right;
    }


    static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new char[n][m];
        blocks = new ArrayList[m];
        stacks = new Stack[m];

        for(int i = 0; i < m; i++){
            blocks[i] = new ArrayList<>();
            stacks[i] = new Stack<>();
            blocks[i].add(new int[]{n, 1});
        }

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            matrix[i] = st.nextToken().toCharArray();
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 'X'){
                    blocks[j].add(new int[]{i, 1});
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        actions = new int[k];
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            actions[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < m; i++){
            Collections.sort(blocks[i], (int[] a, int[] b) -> {
                return a[0] - b[0];
            });
        }
    }


    static void action(int row, int col, int scol){
        int blockIdx = binarySearch(row, col);
        int[] block = blocks[col].get(blockIdx);
        int brow = block[0] - block[1] + 1;
//        System.out.println(row + " " + col + " " + brow);
//        System.out.println(Arrays.toString(block));
        if(brow < n && matrix[brow][col] == 'O'){
            if(col > 0 &&  matrix[brow-1][col-1] == '.' && matrix[brow][col-1] == '.'){ //왼쪽으로 굴러가기
                if(stacks[scol].isEmpty() || !(stacks[scol].peek()[0] == brow - 1 && stacks[scol].peek()[1] == col)){
                    stacks[scol].push(new int[]{brow - 1, col});
                }
                action(brow, col - 1, scol);
            }
            else if(col < m-1 && matrix[brow-1][col+1] == '.' && matrix[brow][col+1] == '.'){ //오른쪽으로 굴러가기
                if(stacks[scol].isEmpty() || !(stacks[scol].peek()[0] == brow - 1 && stacks[scol].peek()[1] == col)){
                    stacks[scol].push(new int[]{brow - 1, col});
                }
                action(brow, col + 1, scol);
            }
            else{
                matrix[brow-1][col] = 'O';
                block[1]++;
            }
        }
        else{
            matrix[brow-1][col] = 'O';
            block[1]++;
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        for(int i = 0; i < k; i++){
            int row = 0;
            int col = actions[i] - 1;

            while(!stacks[actions[i] - 1].isEmpty()){
                int[] cur = stacks[actions[i] - 1].peek();
                if(matrix[cur[0]][cur[1]] != '.') stacks[actions[i] - 1].pop();
                else{
                    row = cur[0];
                    col = cur[1];
                    break;
                }
            }

            action(row, col, actions[i] - 1);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                sb.append(matrix[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
}