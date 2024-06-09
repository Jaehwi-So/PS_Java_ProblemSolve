import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Station implements Comparable<Station>{
    int location;
    int value;
    public Station(int location, int value){
        this.location = location;
        this.value = value;
    }

    @Override
    public int compareTo(Station o) {
        return this.location - o.location;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Station[] stations = new Station[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            stations[i] = new Station(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(stations);
        st = new StringTokenizer(br.readLine());
        int distance = Integer.parseInt(st.nextToken());
        int energy = Integer.parseInt(st.nextToken());
        int currentDist = 0;
        int index = 0;
        PriorityQueue<Station> queue = new PriorityQueue<>(new Comparator<Station>() {
            @Override
            public int compare(Station o1, Station o2) {
                return o2.value - o1.value;
            }
        });

        int count = 0;
        while(currentDist < distance){
            if(index < n && stations[index].location == currentDist){
                queue.offer(stations[index]);
                index++;
            }
            if(energy == 0){
                if(!queue.isEmpty()){
                    energy += queue.poll().value;
                    count++;
                }
                else{
                    count = -1;
                    break;
                }
            }
            currentDist++;
            energy--;
        }
        System.out.println(count);
    }
}
