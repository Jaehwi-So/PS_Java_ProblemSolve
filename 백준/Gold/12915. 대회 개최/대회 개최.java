import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int E = Integer.parseInt(st.nextToken());
        int EM = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int MH = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int count = 0;
        while(true){
            //쉬운 문제
            if(E == 0){
                if(EM == 0) break;
                else EM--;
            }
            else{
                E--;
            }

            //어려운 문제
            if(H == 0){
                if(MH == 0) break;
                else MH--;
            }
            else{
                H--;
            }

            //중간 문제
            if(M == 0){
                if(EM != 0 && MH != 0){
                    int easy = E + EM;
                    int hard = MH + H;
                    if(easy > hard) EM--;
                    else MH--;
                }
                else if(EM == 0 && MH == 0){
                    break;
                }
                else if(EM == 0){
                    MH--;
                }
                else if(MH == 0){
                    EM--;
                }
            }
            else{
                M--;
            }

            count++;
        }
        System.out.println(count);

    }
}
