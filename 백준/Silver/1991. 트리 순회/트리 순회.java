import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Node{
    char key;
    Node left;
    Node right;

    public Node(char key){
        this.key = key;
    }
}
class BTree{
    Node root;

    void preorder(Node n){
        if(n != null){
            preorder(n.left);
            System.out.print(n.key);
            preorder(n.right);
        }

    }

    void inorder(Node n){
        if(n != null){
            System.out.print(n.key);
            inorder(n.left);
            inorder(n.right);
        }

    }

    void postorder(Node n){
        if(n != null){
            postorder(n.left);
            postorder(n.right);
            System.out.print(n.key);
        }

    }


}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        BTree tree = new BTree();


        Map<Character, Node> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            Node n1;
            if(i == 0){
                n1 = new Node(st.nextToken().charAt(0));
                tree.root = n1;
            }
            else{
                n1 = map.get(st.nextToken().charAt(0));
            }
            char ch;
            if((ch = st.nextToken().charAt(0)) != '.'){
                Node n2 = new Node(ch);
                map.put(ch, n2);
                n1.left = n2;
            }
            if((ch = st.nextToken().charAt(0)) != '.'){
                Node n3 = new Node(ch);
                map.put(ch, n3);
                n1.right = n3;
            }

        }

        tree.inorder(tree.root);
        System.out.println();
        tree.preorder(tree.root);
        System.out.println();
        tree.postorder(tree.root);
    }
}
