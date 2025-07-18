import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int k;
    static char[] command;
    static int[][] plants;
    static Map<Integer, List<int[]>> forwardMap = new HashMap<>();
    static Map<Integer, List<int[]>> backwardMap = new HashMap<>();

    static int binarySearch(int x, List<int[]> list){
        int left = 0;
        int right = list.size();

        // 0 5 2 -> 0 2
        while(left < right){
            int mid = (left + right) / 2;
            int number = list.get(mid)[0];
//            System.out.println("BS " + Arrays.toString(list.get(mid)));
            if(number == x){
                return mid;
            }
            else if(number > x){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }

        return right;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        command = st.nextToken().toCharArray();



        plants = new int[n+1][2];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            plants[i][0] = Integer.parseInt(st.nextToken());
            plants[i][1] = Integer.parseInt(st.nextToken());
            int f = plants[i][0] - plants[i][1];
            int b = plants[i][0] + plants[i][1];
            if(!forwardMap.containsKey(f)){
                forwardMap.put(f, new ArrayList<>());
            }
            if(!backwardMap.containsKey(b)){
                backwardMap.put(b, new ArrayList<>());
            }
            forwardMap.get(f).add(new int[]{plants[i][0], plants[i][1], i});
            backwardMap.get(b).add(new int[]{plants[i][0], plants[i][1], i});
        }

//        System.out.println(forwardMap);
//        System.out.println(backwardMap);



        for(Integer key : forwardMap.keySet()){
            List<int[]> list = forwardMap.get(key);
            Collections.sort(list, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });
        }
        for(Integer key : backwardMap.keySet()){
            List<int[]> list = backwardMap.get(key);
            Collections.sort(list, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });
        }

        int[] pos = new int[]{plants[1][0], plants[1][1]};

        for(int i = 0; i < k; i++){
            char c = command[i];
            int f = 0;
            Map<Integer, List<int[]>> map = null;
            if(c == 'A' || c == 'D'){
                map = forwardMap;
                f = pos[0] - pos[1];
            }
            else if(c == 'B' || c == 'C'){
                map = backwardMap;
                f = pos[0] + pos[1];
            }
//            System.out.println("Pos " + Arrays.toString(pos));
            if(map.containsKey(f)){
                List<int[]> list = map.get(f);
                int currentIdx = binarySearch(pos[0], list);

                // trace
//                for(int[] line : list){
//                    System.out.println(Arrays.toString(line));
//                }
//                System.out.println("Current IDX : " + currentIdx);

                int next = -1;
                if(c == 'A' || c == 'B'){
                    if(list.size() > currentIdx + 1) next = currentIdx + 1;
                }
                else{
                    if(currentIdx > 0) next = currentIdx - 1;
                }

                if(next != -1){
                    int[] cur = list.get(next);
                    if(backwardMap.containsKey(pos[0] + pos[1])){
                        List<int[]> delList = backwardMap.get(pos[0] + pos[1]);
                        int didx = binarySearch(pos[0], delList);
                        delList.remove(didx);
                    }
                    if(forwardMap.containsKey(pos[0] - pos[1])){
                        List<int[]> delList = forwardMap.get(pos[0] - pos[1]);
                        int didx = binarySearch(pos[0], delList);
                        delList.remove(didx);
                    }
                    pos[0] = cur[0];
                    pos[1] = cur[1];
                }

            }
        }
        System.out.println(pos[0] + " " + pos[1]);
    }
}