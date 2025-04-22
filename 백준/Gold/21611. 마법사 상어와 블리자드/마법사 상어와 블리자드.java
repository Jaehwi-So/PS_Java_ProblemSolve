import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] dy = {0, 1, 0, -1, 0};
    static int[] dx = {0, 0, 1, 0, -1};
    static int[][] commands;
    static int[][] delIdx;
    static int[] sequence;
    static int result = 0;

    static void conquer(){
        int[] temp = new int[sequence.length];
        int index = 1;
        for(int i = 1; i < sequence.length; i++){
            if(sequence[i] != 0){
                temp[index] = sequence[i];
                index++;
            }
        }
        sequence = temp;
    }

    static void destroy(){
        Stack<int[]> stack = new Stack<>();
        Stack<int[]> temp = new Stack<>();

        for(int i = 1; i < sequence.length; i++){
            stack.push(new int[]{sequence[i], 1});
        }

        while(true){
            boolean exit = true;
            while(!stack.isEmpty()){
                int[] current = stack.pop();
                if(stack.isEmpty() || stack.peek()[0] != current[0]){
                    if(current[1] >= 4){
                        exit = false;
                        result += (current[0] * current[1]);
                    }
                    else{
                        temp.push(current);
                    }
                }
                else if(stack.peek()[0] == current[0]){
                    int[] before = stack.pop();
                    stack.push(new int[]{current[0], current[1] + before[1]});
                }
            }
            while(!temp.isEmpty()){
                stack.push(temp.pop());
            }
            if(exit) break;
        }

        Stack<Integer> seq = new Stack<>();
        while(!stack.isEmpty()){
            int[] current = stack.pop();
            seq.push(current[0]);
            seq.push(current[1]);
        }

        int len = seq.size();
        int[] array = new int[sequence.length];
        for(int i = 1; i <= len && i < sequence.length; i++){
            array[i] = seq.pop();
        }
        sequence = array;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sequence = new int[n*n];
        int row = (n+1)/2 - 2;
        int col = (n+1)/2 - 2;
        int iter = 2;
        int index = 1;
        while(index < n*n){
            for(int dir = 1; dir <= 4; dir++){
                for(int i = 0; i < iter; i++){
                    row = row + dy[dir];
                    col = col + dx[dir];
                    sequence[index++] = matrix[row][col];
                }
            }
            col = col - 1;
            row = row - 1;
            iter += 2;
        }

        commands = new int[m][2];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            commands[i][0] = Integer.parseInt(st.nextToken());
            commands[i][1] = Integer.parseInt(st.nextToken());
        }

        delIdx = new int[5][n / 2]; // 상 하 좌 우        -> 좌 하 우 상
        int num = -1;
        int[] numbers = {0, 0, 0, 0, 0};
        for(int i = 0; i < (n / 2); i++){
            num += 2;
            numbers[3] += num;
            delIdx[3][i] = numbers[3];

            num += 2;
            numbers[2] += num;
            delIdx[2][i] = numbers[2];

            num += 2;
            numbers[4] += num;
            delIdx[4][i] = numbers[4];

            num += 2;
            numbers[1] += num;
            delIdx[1][i] = numbers[1];
        }

        for(int[] command : commands){  //방향, 거리
            for(int i = 0; i < command[1]; i++){
                sequence[delIdx[command[0]][i]] = 0;
            }
            conquer();
            destroy();
        }
        System.out.println(result);
    }
}