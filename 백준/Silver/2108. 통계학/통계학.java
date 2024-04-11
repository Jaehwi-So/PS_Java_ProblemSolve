import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] array = new int[n];
        int sum = 0;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            array[i] = k;
            sum += k;
        }
        Arrays.sort(array);

        int freq = 0;
        List<Integer> freqNums = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            if(map.containsKey(array[i])){
                map.replace(array[i], map.get(array[i]) + 1);
            }
            else{
                map.put(array[i], 1);
            }

            int mapValue = map.get(array[i]);

            if(mapValue > freq){
                freqNums.clear();
                freqNums.add(array[i]);
                freq = mapValue;
            }
            else if (mapValue == freq){
                freqNums.add(array[i]);
            }
        }

        Collections.sort(freqNums);
        int freqNumber = freqNums.size() > 1 ? freqNums.get(1) : freqNums.get(0);



        double average = sum / (double)n; // (-1.5)
        if(average < 0){
            average *= -1;
            average = Math.round(average);
            average *= -1;
        }
        else{
            average = Math.round(average);
        }

        System.out.println((int)average);
        System.out.println(array[n/2]);
        System.out.println(freqNumber);
        System.out.println(array[n - 1] - array[0]);



    }
}
