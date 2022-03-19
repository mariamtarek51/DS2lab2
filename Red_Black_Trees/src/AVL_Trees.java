import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
class NodeAVL{
    String data;
    int height;
    NodeAVL right; //right child
    NodeAVL left; //left child
    NodeAVL(String d){

        data=d;
    }
}
public class AVL_Trees {
    void preOrder(Node n) {
        if (n != null) {
            System.out.print(n.data + " ");
            preOrder(n.left);
            preOrder(n.right);
        }
    }

    Node root;

    int max(int a, int b) {
        if (a > b)
            return a;
        return b;
    }

    int height(NodeAVL n) {
        if (n == null)
            return -1;

        return n.height;
    }

    int balancedfactor(NodeAVL n) {
        if (n == null)
            return 0;


        return height(n.right) - height(n.left);
    }

    NodeAVL leftRotate(NodeAVL n) {


        NodeAVL rchild = n.right;

        n.right = rchild.left;

        rchild.left = n;


        n.height = max(height(n.left), height(n.right)) + 1;
        rchild.height = max(height(rchild.left), height(rchild.right)) + 1;


        return rchild;
    }

    NodeAVL rightRotate(NodeAVL n) {

        NodeAVL lchild = n.left;
        n.left = lchild.right;

        lchild.right = n;


        n.height = max(height(n.left), height(n.right)) + 1;
        lchild.height = max(height(lchild.left), height(lchild.right)) + 1;


        return lchild;
    }

    NodeAVL searchnode(NodeAVL n, String data) {


        if (n == null) {

            return null;
        }

        if (data.equals(n.data)) {

            return n;
        }
        String biggerStr = maxStr(n.data, data);
        if (biggerStr.equals(data)) {
            return searchnode(n.right, data);
        } else if (biggerStr.equals("sameword")) {
            return n;
        } else {
            return searchnode(n.left, data);
        }


    }

    NodeAVL insertnode(NodeAVL n, String data) {


        if (n == null) { //in case its the first element

            return (new NodeAVL(data));
        }
        String biggerStr = maxStr(n.data, data);
        if (biggerStr == n.data) {

            n.left = insertnode(n.left, data);
        } else if (biggerStr == data) {

            n.right = insertnode(n.right, data);

        } else {
            return n;
        }
        n.height = 1 + max(height(n.left), height(n.right));
        int bf = balancedfactor(n);
        //System.out.println("bf is"+bf);
        if (bf != 1 && bf != -1 && bf != 0) {
            if (bf < -1) {
                //RR case

                biggerStr = maxStr(data, n.left.data);
                if (biggerStr == n.left.data) {
                    return rightRotate(n);
                }
                //LR case
                if (biggerStr == data) {
                    n.left = leftRotate(n.left);
                    return rightRotate(n);
                }
            }

            if (bf > 1) {
                //LL case
                biggerStr = maxStr(data, n.right.data);
                if (biggerStr == data) {
                    return leftRotate(n);
                }
                //RL case

                if (biggerStr == n.right.data) {
                    n.right = rightRotate(n.right);
                    return leftRotate(n);
                }
            }

        }

        //System.out.println(n.data);
        return n;
    }

    NodeAVL DeleteNode(NodeAVL n, String data) {
        if (n == null) {
            return n;
        } else if (maxStr(data, n.data) == data) {
            n.right = DeleteNode(n.right, data);
        } else if (maxStr(data, n.data) == n.data) {
            n.left = DeleteNode(n.left, data);
        } else {
            if ((n.right == null || n.left == null)) {
                NodeAVL newnode = null;
                if (n.right != null) {
                    newnode = n.right;
                } else if (n.left != null) {
                    newnode = n.left;
                }
                if (newnode == null) {
                    newnode = n;
                    n = null;
                } else {
                    n = newnode;
                }
                newnode = null;
            } else {
                NodeAVL t = minimumNode(n.right);
                n.data = t.data;
                n.right = DeleteNode(n.right, t.data);
            }
        }
        if (n == null) {
            return n;
        }
        n.height = max(height(n.right), height(n.left)) + 1;
        if (balancedfactor(n) > 1) {
            if (balancedfactor(n.left) >= 0) {
                return rightRotate(n);
            } else {
                n.left = leftRotate(n.left);
                return rightRotate(n);
            }
        } else if (balancedfactor(n) < -1) {
            if (balancedfactor(n.right) <= 0) {
                return leftRotate(n);
            } else {
                n.right = rightRotate(n.right);
                return rightRotate(n);
            }
        }
        return n;
    }

    // Find the minimum node on left side
    public NodeAVL minimumNode(NodeAVL node) {
        NodeAVL result = node;
        while (result.left != null) {
            result = result.left;
        }
        return result;
    }

    String maxStr(String data1, String data2) {
        int length, samenum = 0;
        String maxstr = "";
        if (data1.length() > data2.length()) {
            maxstr = data1;
            length = data2.length();
        } else if (data1.length() < data2.length()) {
            maxstr = data2;
            length = data1.length();
        } else {
            samenum = 1;
            length = data1.length();
        }

        for (int i = 0; i < length; i++) {
            if (data1.charAt(i) > data2.charAt(i)) {
                return data1;

            } else if (data2.charAt(i) > data1.charAt(i)) {
                return data2;
            }
        }

        //in case the word already exists
        if (samenum == 1) {
            return "sameword";
        } else {
            return maxstr;

        }

    }
}
