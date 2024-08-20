import java.util.*;



class Tree{
    Node root;
    
    public Tree(Node root){
        this.root = root;
    }
    
    public void insert(Node n){
        Node current = root;
        while(true){
            if(n.value > current.value){
                if(current.right == null){
                    current.right = n;
                    break;
                } 
                current = current.right;
            }
            else{
                if(current.left == null){
                    current.left = n;
                    break;
                } 
                current = current.left;
            }
        }
    }
    
    public void preorder(){
        Solution.idx = 0;
        this.root.preorder();
    }
    
    public void postorder(){
        Solution.idx = 0;
        this.root.postorder();
    }
    
}

class Node{
    int index;
    int value;
    int height;
    
    Node left;
    Node right;
    
    public Node(int index, int value, int height){
        this.index = index;
        this.value = value;
        this.height = height;
    }
    
    public void preorder(){
        Solution.result[0][Solution.idx++] = this.index;
        if(left != null){
            this.left.preorder();
        }
        if(right != null){
            this.right.preorder();
        }
    }
    public void postorder(){
        if(left != null){
            this.left.postorder();
        }
        if(right != null){
            this.right.postorder();
        }
        Solution.result[1][Solution.idx++] = this.index;
    }
}


class Solution {
    public static int[][] result;
    public static int idx;
    
    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;
        Node[] nodes = new Node[n];
        result = new int[2][n];
        for(int i = 0; i < n; i++){
            nodes[i] = new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]);
        }
        
        Arrays.sort(nodes, new Comparator<Node>(){
            public int compare(Node n1, Node n2){
                if(n1.height == n2.height){
                    return n1.value- n2.value;
                }
                return n2.height - n1.height;
            }
        });
        
        
        Tree tree = new Tree(nodes[0]);
        for(int i = 1; i < n; i++){
            tree.insert(nodes[i]);
        }
        
        tree.preorder();
        tree.postorder();

        return result;
    }
}