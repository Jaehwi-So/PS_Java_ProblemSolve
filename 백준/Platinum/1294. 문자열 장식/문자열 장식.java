import java.io.*;
import java.util.*;

class W{
    char ch;
    int index;
    int sequence;
    public W(char ch, int index, int sequence){
        this.ch = ch;
        this.index = index;
        this.sequence = sequence;
    }
}

public class Main {
    static List<char[]> words = new ArrayList<>();
    static boolean[][] comp;
    static int comp(W w1, W w2){
        char c1 = w1.ch;
        char c2 = w2.ch;
        int seq1 = w1.sequence;
        int seq2 = w2.sequence;
        
        while(Character.compare(c1, c2) == 0){
            if(seq1 == words.get(w1.index).length - 1 && seq2 == words.get(w2.index).length - 1) break;
            else if(seq1 == words.get(w1.index).length - 1){
                return 1;
            }
            else if(seq2 == words.get(w2.index).length - 1){
                return -1;
            }
            else{
                c1 = words.get(w1.index)[seq1+1];
                seq1++;
                c2 = words.get(w2.index)[seq2+1];
                seq2++;
            }
        }

        /**
         * BBBA
         * BBB
         */
        return Character.compare(c1, c2);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            words.add(st.nextToken().toCharArray());
        }

        PriorityQueue<W> pq = new PriorityQueue(new Comparator<W>(){
            public int compare(W w1, W w2){
                return comp(w1, w2);
            }
        });

        for(int i = 0; i < n; i++){
            pq.offer(new W(words.get(i)[0], i, 0));
        }

        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()){
            W w = pq.poll();
            sb.append(w.ch);
            if(w.sequence < words.get(w.index).length - 1){
                pq.offer(new W(words.get(w.index)[w.sequence+1], w.index, w.sequence+1));
            }
        }

        System.out.println(sb);

    }
}