import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

class Layer implements Comparable<Layer>{
    int length;
    int width;
    int height;
    public Layer(int length, int width, int height){
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    public int compareTo(Layer o) {
        return Math.min(Math.min(o.length, o.width), o.height) -  Math.min(Math.min(this.length, this.width), this.height);
    }

    @Override
    public String toString() {
        return this.length + " " + this.width + " " + this.height;
    }
}


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int l = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<int[]> cube = new ArrayList<>();

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int[] array = new int[2];
            array[0] = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
            array[1] = Integer.parseInt(st.nextToken());
            cube.add(array);
        }

        Collections.reverse(cube);
        PriorityQueue<Layer> queue = new PriorityQueue();

        queue.offer(new Layer(l, w, h));
        boolean success = true;
        int count = 0;
        for(int[] c : cube){
            int quantity = c[1];
            while(!queue.isEmpty() && quantity > 0){
                boolean flag = false;
                Layer la = queue.poll();
                int height = la.height;
                while(height >= c[0] && quantity > 0){
                    int width = la.width;
                    while(width >= c[0] && quantity > 0){
                        int length = la.length;
                        while(length >= c[0] && quantity > 0){
                            length -= c[0];
                            quantity--;
                            count++;
                            flag = true;
                        }
                        if(length > 0){
                            queue.offer(new Layer(length, c[0], c[0]));
                        }
                        width -= c[0];
                        if(flag == false || quantity == 0){
                            break;
                        }
                    }
                    if(width > 0) {
                        queue.offer(new Layer(la.length, width, c[0]));
                    }
                    height -= c[0];
                    if(flag == false || quantity == 0){
                        break;
                    }

                }


                if(height > 0){
                    queue.offer(new Layer(la.length, la.width, height));
                }

                if(quantity == 0){
                    break;
                }

                if(flag == false){
                    break;
                }
            }
//            System.out.println(queue);
        }
        if(!queue.isEmpty()){
            success = false;
        }
        if(success == false){
            System.out.println(-1);
        }
        else{
            System.out.println(count);
        }
//        System.out.println(count);
    }
}
