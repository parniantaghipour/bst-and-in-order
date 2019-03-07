import sun.reflect.generics.tree.Tree;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

class BinarySearchTree {

    class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    static Node root;

    BinarySearchTree() {
        root = null;
    }


    void insert(int key) {
        root = insertRec(root, key);
    }


    Node insertRec(Node root, int key) {


        if (root == null) {
            root = new Node(key);
            return root;
        }


        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);


        return root;
    }

    void inorder()  {
        inorderRec(root);
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.key);
            inorderRec(root.right);
        }
    }
    void printLevelOrder()
    {
        int h = height(root);
        int i;
        for (i=1; i<=h; i++)
            printGivenLevel(root, i);
    }
    int height(Node root)
    {
        if (root == null)
            return 0;
        else
        {
            int lheight = height(root.left);
            int rheight = height(root.right);

            if (lheight > rheight)
                return(lheight+1);
            else return(rheight+1);
        }
    }

      void printGivenLevel (Node root ,int level)
    {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.key + " ");
        else if (level > 1)
        {
            printGivenLevel(root.left, level-1);
            printGivenLevel(root.right, level-1);
        }
    }
    void deleteKey(int key)
    {
        root = deleteRec(root, key);
    }

    Node deleteRec(Node root, int key)
    {

        if (root == null)  return root;

        if (key < root.key)
            root.left = deleteRec(root.left, key);
        else if (key > root.key)
            root.right = deleteRec(root.right, key);


        else
        {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.key = minValue(root.right);
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    int minValue(Node root)
    {
        int minv = root.key;
        while (root.left != null)
        {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }
    public static void search(int key)
    {
        root=searchRoot(root, key);
        if (root==null)
            System.out.println("the number "+key +"is not found");
        else
            System.out.println("we found the "+key);

    }
    public static Node searchRoot(Node root, int key)
    {
        if (root==null || root.key==key)
            return root;

        if (root.key > key)
            return searchRoot(root.left, key);

        return searchRoot(root.right, key);
    }
    public class ShowTree extends JPanel {

        Node tree;
        private int canvasWidth = 800;
        private int canvasHeight = 700;
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(5));
            this.print(this.tree.key, g, g2);



        }

        public void print(Node current, Graphics g, Graphics2D g2) {
            Node temp = current;
            if(current != null) {
                g2.setColor(new Color(153, 63, 0));
                if(current.left != null) {
                    g2.drawLine(this.position_x(current.depth, current.index) + 15,
                            this.position_y(current.depth, current.index) + 15,
                            this.position_x(current.left.depth, current.left.index) + 15,
                            this.position_y(current.left.depth, current.left.index) + 15);
                    this.print(current.left,g, g2);
                }
                g2.setColor(new Color(153, 63, 0));
                if(current.right !=  null) {
                    g2.drawLine(this.position_x(current.depth, current.index) + 15,
                            this.position_y(current.depth, current.index) + 15,
                            this.position_x(current.right.depth, current.right.index) + 15,
                            this.position_y(current.right.depth, current.right.index)+ 15) ;
                    this.print(current.right, g, g2);
                }

                g.setColor(new Color(0, 153,0));
                g.fillOval(this.position_x(temp.depth, temp.index), this.position_y(temp.depth, temp.index),30,30);

                g.setColor(Color.WHITE);
                g.drawString(String.valueOf(temp.value),this.position_x(temp.depth, temp.index) + 10, this.position_y(current.depth, temp.index) + 20);

            }
        }

        public int position_x (int depth, int index) {
            if(index == 1) {
                return (int) ((index * canvasWidth) / Math.pow(2, depth + 1)) - 15;
            } else {
                return ((int) (((2 * canvasWidth) / Math.pow(2, depth + 1)) * (index - 1)) + (int) ((canvasWidth) / Math.pow(2, depth + 1))) - 15;
            }
        }

        public int position_y(int depth, int index) {
            return (int) ((depth * canvasHeight) / (tree.height + 1));
        }


        public Node getTree() {
            return tree;
        }

        public void setTree(Node tree) {
            this.tree = tree;
        }

        public int getCanvasWidth() {
            return canvasWidth;
        }

        public static void setCanvasWidth(int canvasWidth) {
            ShowTree.canva sWidth = canvasWidth;
        }

        public static int getCanvasHeight() {
            return canvasHeight;
        }

        public static void setCanvasHeight(int canvasHeight) {
            ShowTree.canvasHeight = canvasHeight;
        }

        public ShowTree(Tree tree){
            this.tree = tree;
        }
    }


    public static void main(String[] args) {
        int press;
        BinarySearchTree tree = new BinarySearchTree();
        do {
            System.out.println("\n press 0 if you want to insert a num\n press 1 if you want to see the patern in order \n " +
                    "press 2 if you want to search a number \n press 3 if you want to see the patern in bfs order" +
                    "\n press 4 to delete a num \n press 5 to quit ");
            Scanner scn = new Scanner(System.in);
             press = scn.nextInt();
            if (press== 0){
                System.out.println("press 0 to finish giving input");
                int newnum;
                while(true) {
                    newnum = scn.nextInt();
                    if (newnum==0)
                        break;
                    tree.insert(newnum);

                }
            }
            if (press== 1) {
                tree.inorder();
            }
            if (press== 2){
                System.out.println("put the num that you want to check if we have that in our tree or not!?");
                int searchKey=scn.nextInt();
                search(searchKey);

            }

            if (press== 3){
                System.out.println("Level order traversal of binary tree is ");
                tree.printLevelOrder();
            }
            if (press== 4){
                System.out.println("put in the number that you want to delete");
                int number= scn.nextInt();
                tree.deleteKey(number);
            }
        }while(press!= 5);
    }
}