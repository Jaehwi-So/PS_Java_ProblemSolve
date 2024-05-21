import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Flower implements Comparable<Flower>{
    int start;
    int end;
    public Flower(int start, int end){
        this.start = start;
        this.end = end;
    }


    @Override
    public int compareTo(Flower o) {
        int comp = this.start - o.start;
        if(comp == 0){
            comp = this.end - o.end;
        }
        return comp;
    }

    public String toString(){
        return String.format("(%d - %d)", start, end);
    }
}
public class Main {
    static int[] monthStartDay = {0, 1, 32, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
    static int getNumericDate(int type, int month, int day){
        int n = monthStartDay[month] + day - 1;
        if(type == 0 && n <= startCondition){
            n = startCondition;
        }
        else if(type == 1 && n >= endCondition){
            n = endCondition;
        }
        return n;
    }

    static int startCondition = getNumericDate(2, 3, 1);
    static int endCondition = getNumericDate(2, 12, 1);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Flower[] flowers = new Flower[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            flowers[i] = new Flower(getNumericDate(0, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())),
                    getNumericDate(1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Arrays.sort(flowers);
//        System.out.println(Arrays.toString(flowers));

        PriorityQueue<Flower> queue = new PriorityQueue<>(new Comparator<Flower>() {
            @Override
            public int compare(Flower o1, Flower o2) {
                return o2.end - o1.end;
            }
        });


        if(flowers[0].start > startCondition){
            System.out.println(0);
            return;
        }

        queue.offer(flowers[0]);

        for(int i = 1; i < n; i++){
            if(flowers[i].end < startCondition || flowers[i].start > endCondition){
                continue;
            }
            Flower f = queue.peek();
            if(flowers[i].end > f.end){
                while(!queue.isEmpty()){
                    Flower p = queue.poll();
                    if(queue.isEmpty()){
                        if(p.start < flowers[i].start){
                            queue.offer(p);
                        }
                        break;
                    }
                    else if(flowers[i].start > queue.peek().end){
                        queue.offer(p);
                        break;
                    }
                }

                queue.offer(flowers[i]);
            }
        }

        int size = queue.size();
        if(size > 0){
            if(queue.peek().end < endCondition){
                System.out.println(0);
                return;
            }
            int last = queue.poll().start;
            while(!queue.isEmpty()){
                Flower f = queue.poll();
                if(f.end < last){
                    System.out.println(0);
                    return;
                }
                else{
                    last = f.start;
                }
            }
        }

        System.out.println(size);
    }
}
