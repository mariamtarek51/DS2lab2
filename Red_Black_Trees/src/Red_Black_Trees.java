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
        Node insertedNode = new Node(data ,1);

        if(n==null) { //in case root is empty
     //the new node will be inserted as the root
            insertedNode.color=0;
            return (insertedNode); //black root
        }
        String biggerStr =maxStr(n.data,data);
        if(biggerStr==n.data) {

            n.left=insertnode(n.left,data);
        }else if (biggerStr==data) {

            n.right=insertnode(n.right,data);

        }else {
            return n;
        }
        /*if (insertedNode.parent == null) {
            insertedNode.color = 0;
            return ;
        }

        if (insertedNode.parent.parent == null) {
            return;
        }*/

        InsertPreparation(n);


        //System.out.println(n.data);
        return n;
    }
}


