import java.util.Scanner;

class Node {
    String data;
    Node parent;
    Node left;
    Node right;
    int height;
    int color; //0 ->black //1-> red
    Node(String d ,int col){
        data=d;
        color=col;
    }

}

public class Red_Black_Trees {
    String maxStr(String data1,String data2) {
        int length,samenum=0;
        String maxstr="";
        if(data1.length()>data2.length()) {
            maxstr=data1;
            length=data2.length();
        } else if(data1.length()<data2.length()){
            maxstr=data2;
            length=data1.length();
        }else {
            samenum=1;
            length=data1.length();
        }

        for(int i=0;i<length;i++) {
            if(data1.charAt(i)>data2.charAt(i)) {
                return data1 ;

            }else if(data2.charAt(i)>data1.charAt(i)) {
                return data2;
            }
        }

        //in case the word already exists
        if(samenum==1) {
            return "sameword";
        }else {
            return maxstr;

        }

    }
    int max(int a, int b) {
        if(a>b)
            return a;
        return b;
    }
    int height(Node n) {
        if (n == null)
            return -1;

        return n.height;
    }

    Node leftRotate(Node n) {


        Node rchild=n.right;

        n.right=rchild.left;

        rchild.left=n;


        n.height = max(height(n.left), height(n.right)) + 1;
        rchild.height = max(height(rchild.left), height(rchild.right)) + 1;



        return rchild ;
    }
    Node rightRotate(Node n) {

        Node lchild=n.left;
        n.left=lchild.right;

        lchild.right=n;


        n.height = max(height(n.left), height(n.right)) + 1;
        lchild.height = max(height(lchild.left), height(lchild.right)) + 1;



        return lchild;
    }
    Node root;
    // Balance the node after insertion
    private void InsertPreparation(Node k) {
        Node uncle;
        while (k.parent.color == 1) {
            if (k.parent == k.parent.parent.right) {
                uncle = k.parent.parent.left;
                if (uncle.color == 1) {
                    uncle.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    leftRotate(k.parent.parent);
                }
            } else {
                uncle = k.parent.parent.right;

                if (uncle.color == 1) {
                    uncle.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = 0;
    }
    Node insertnode(Node n,String data) {
        Node node = new Node(data,1);
        node.parent = null;


        Node y =null;
        while (n != null) {
            //System.out.print("jjj");
            y = n;
            String biggerStr =maxStr(n.data,node.data);
            if(biggerStr==n.data){ //root is greater
                n = n.left;
            } else {
                n= n.right;
            }
        }

        node.parent = y;

        if (y == null) {
            root = node;

        }
        else if(maxStr(node.data,y.data)==y.data){
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = 0;
            return null;
        }

        if (node.parent.parent == null) {
            return null;
        }
            InsertPreparation(node);

        System.out.println("hvhblk" +  n);
        System.out.println(node.data);
        return node;
    }
    // Preorder
    private void preOrderHelper(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }
    private void printHelper(Node root, String indent, boolean last) {

        if (root != null) {

            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            String sColor = root.color == 1 ? "RED" : "BLACK";
            System.out.println(root.data + "(" + sColor + ")");
            printHelper(root.left, indent, false);
            printHelper(root.right, indent, true);
        }
    }
    public void printTree() {
        printHelper(this.root, "", true);
    }
    public String getRoot() {
        return this.root.data;
    }
    Boolean isEmpty(){
        if(this.root!= null){
            return false;
        }
        return true;
    }
    void  Clearall(){
        this.root=null;
    }
    public static void main(String[] args) {
       // Scanner sc= new Scanner(System.in);

        Red_Black_Trees bst = new Red_Black_Trees();
        System.out.println(bst.isEmpty());
        bst.insertnode(bst.root,"55");
        bst.insertnode(bst.root,"40");
        bst.insertnode(bst.root,"65");
        bst.insertnode(bst.root,"60");
        bst.insertnode(bst.root,"75");
        bst.insertnode(bst.root,"57");
        bst.printTree();
        System.out.println(bst.getRoot());
        System.out.println("isEmpty ? " + bst.isEmpty());
        bst.Clearall();
        System.out.println(bst.isEmpty());


    }


    }


