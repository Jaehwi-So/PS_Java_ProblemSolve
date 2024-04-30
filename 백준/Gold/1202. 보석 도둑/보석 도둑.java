import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

class Jewelry implements Comparable<Jewelry>{
    int value;
    int weight;

    @Override
    public int compareTo(Jewelry o) {
        if(o.weight == this.weight){
            return o.value - this.value;
        }
        return this.weight - o.weight;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());


        // 보석의 무게가 낮은 순서대로 정렬 -> 같으면 가치가 높은 순서대로 정렬
        ArrayList<Jewelry> jewelries = new ArrayList<>();



        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            Jewelry j = new Jewelry();
            j.weight = Integer.parseInt(st.nextToken());
            j.value = Integer.parseInt(st.nextToken());
            jewelries.add(j);
        }

        Collections.sort(jewelries);

//        for(Jewelry j : jewelries){
//            System.out.println(j.weight + " " + j.value);
//        }



        ArrayList<Long> bags = new ArrayList<>();
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            bags.add(Long.parseLong(st.nextToken()));
        }

        // 수용 무게가 낮은 가방 순으로 정렬 -> 가장 낮은 가방부터 가장 높은 가치의 보석을 챙길 수 있도록
        Collections.sort(bags);

        long total = 0;

        PriorityQueue<Jewelry> queue = new PriorityQueue<>(new Comparator<Jewelry>() {
            @Override
            public int compare(Jewelry o1, Jewelry o2) {
                if(o2.value == o1.value){
                    return o1.weight - o2.weight;
                }
                return o2.value - o1.value;
            }
        });


        int index = 0;
        for(int i = 0; i < k; i++){
            // 현재 가방의 무게보다 가벼운 보석을 가격이 높은 순서로 우선순위 큐에 넣음
            for(int l = index; l < n; l++){
                Jewelry j = jewelries.get(index);
                if(j.weight > bags.get(i)){
                    break;
                }
                queue.add(j);
                index++;
            }
            // 우선순위 큐의 맨 첫번째 -> 가방이 수용 가능한 것 중 가격이 제일 높은 것
            if(!queue.isEmpty()){
                Jewelry j = queue.poll();
                total += j.value;
            }
        }

        System.out.println(total);
    }
}
