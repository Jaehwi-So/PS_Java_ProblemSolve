import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Book implements Comparable<Book>{
    int start;
    int end;
    public Book(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Book o) {
        return this.start - o.start;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int test = Integer.parseInt(st.nextToken());

        for(int t = 0; t < test; t++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            Book[] books = new Book[m];
            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                books[i] = new Book(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            Arrays.sort(books);
            PriorityQueue<Book> queue = new PriorityQueue<>(new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.end - o2.end;
                }
            });

            int index = 0;
            int count = 0;
            for(int i = 1; i <= n; i++){

                while(index < m){
                    Book b = books[index];
                    if(b.start == i){
                        queue.offer(b);
//                        System.out.println("Offer" + b.start + " " + b.end);
                        index++;
                    }
                    else{
                        break;
                    }
                }

                while(!queue.isEmpty()){
                    Book b = queue.poll();
                    if(b.end >= i){
//                        System.out.println(b.start + " " + b.end);
                        count++;
                        break;
                    }
                }
            }

            System.out.println(count);

        }

    }
}