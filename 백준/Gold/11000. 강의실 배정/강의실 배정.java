import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Class implements Comparable<Class>{
    int start;
    int end;

    public Class(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Class o) {
        if(o.start == this.start){
            return this.end - o.end;
        }
        return this.start - o.start;
    }

    public String toString(){
        return this.start + " " + this.end;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Class[] array = new Class[n];


        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            array[i] = new Class(start, end);
        }

        Arrays.sort(array);

        PriorityQueue<Class> queue = new PriorityQueue<>(new Comparator<Class>() {
            @Override
            public int compare(Class o1, Class o2) {
                if(o1.end == o2.end){
                    return o1.start - o2.start;
                }
                return o1.end - o2.end;
            }
        });

        queue.offer(array[0]);

        for(int i = 1; i < n; i++){
            Class c = queue.peek();
            if(c.end <= array[i].start){
                queue.poll();
                queue.offer(new Class(c.start, array[i].end));
            }
            else{
                queue.offer(array[i]);
            }
        }

//        System.out.println(queue);

        System.out.println(queue.size());
    }
}