import java.io.*;
import java.util.*;

public class Main {

    static int result = 0;
    static int[] dice;

//    static boolean[] visited;
    static int[] nodes = new int[]{
            0, 2, 4, 6, 8, // 0
            10, 12, 14, 16, 18, // 5
            20, 22, 24, 26, 28, // 10
            30, 32, 34, 36, 38, // 15
            13, 16, 19, // 20
            22, 24, // 23
            28, 27, 26, // 25
            25, 30, 35, // 28
            40, 0 //31
    };

    static int[][] path = new int[][]{
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 31, 32, 32, 32, 32, 32, 32},
            {0, 1, 2, 3, 4, 5, 20, 21, 22, 28, 29, 30, 31, 32, 32, 32, 32, 32, 32}, // 10~
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 23, 24, 28, 29, 30, 31, 32, 32, 32, 32, 32, 32},  // 20~
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 25, 26, 27, 28, 29, 30, 31, 32, 32, 32, 32, 32, 32} // 30~
    };

    // 1 1 2
    // 2 3 4 2 3 4 1
    // 2 5 9 11 14 18 19
    // 4 10 18 22 28 36 38
    // 54 64 38 118 38 156

    static void dfs(int step, int[] current, int[] type, int weight, int[] trace, boolean[] visited){
        if(step >= 10){
//
            if(weight > result){
                result = weight;
//                System.out.println(Arrays.toString(current) + " " + Arrays.toString(type) + " " + step + " " + weight);
//                System.out.println(Arrays.toString(trace));
//                System.out.println("Weight " + weight);
            }
        }
        else{
            for(int i = 0; i < 4; i++){
                int index = path[type[i]][current[i]];
                int[] next = Arrays.copyOf(current, current.length);
                int[] nextType = Arrays.copyOf(type, type.length);
                int[] nextTrace = Arrays.copyOf(trace, trace.length);
                boolean[] nextVisited = Arrays.copyOf(visited, visited.length);

                if(index == 32){
                    dfs(step + 1, next, nextType, weight, nextTrace, nextVisited);
                }
                else{
                    if(type[i] == 0){
                        if(index == 5) nextType[i] = 1;
                        else if(index == 10) nextType[i] = 2;
                        else if(index == 15) nextType[i] = 3;
                    }
                    next[i] = current[i] + dice[step + 1];
                    int nextidx = path[nextType[i]][next[i]];
                    if(visited[nextidx] && nextidx != 32){
                        continue;
                    }
                    int nextWeight = weight + nodes[nextidx];

                    nextTrace[step + 1] = nextWeight;
                    nextVisited[index] = false;
                    nextVisited[nextidx] = true;
                    dfs(step + 1, next, nextType, nextWeight, nextTrace, nextVisited);
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        dice = new int[11];

        for(int i = 1; i <= 10; i++){
            dice[i] = Integer.parseInt(st.nextToken());
        }

//        for(int[] line : path){
//            for(int i : line){
//                System.out.print(nodes[i] + " ");
//            }
//            System.out.println();
//        }

        dfs(0, new int[4], new int[4], 0, new int[11], new boolean[nodes.length]);



        System.out.println(result);

    }
}