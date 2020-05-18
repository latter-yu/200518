package BinarySearchTree;

public class BinarySearchTree {
    // 二叉搜索树:
    // 若左子树不为空，则左子树上所有节点的值都小于根节点的值
    // 若右子树不为空，则右子树上所有节点的值都大于根节点的值
    // 其中序遍历是一串有序数列

    class Node {
        int key;
        int val;
        Node left;
        Node right;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", val=" + val +
                    '}';
        }
    }

    private Node root = null;
    public Node find(int key) {
        // 查找 key , 找到返回所在节点，否则返回 null
        Node cur = root;
        while (cur != null) {
            if (cur.key == key) {
                return cur;
            }else if (cur.key > key) {
                cur = cur.left;
            }else {
                cur = cur.right;
            }
        }
        return null;
    }

    public Node insert(int key, int val) {
        // 插入一个新节点
        // 1.如果是空树，root 直接指向新节点即可
        if (root == null) {
            root = new Node(key, val);
            return root;
        }
        // 2.若不是空树，先找到 key 所在的位置
        Node cur = root;
        Node prev = null;
        while (cur != null) {
            if (cur.key > key) {
                prev = cur;
                cur = cur.left;
            }else if (cur.key < key){
                prev = cur;
                cur = cur.right;
            }else {
                // 找到了 key 相同的元素.
                // 针对 key 重复的情况:
                // a) 让插入操作直接失败.
                // b) 不创建新节点, 把当前节点的 value 给改成新的 value [此处采取这种方案]
                //(相同的 key, 可以改变 原来的 value)
                cur.val = val;
                return cur;
            }
        }
        // 3.循环结束，将新节点插入到 prev 的下面
        Node newNode = new Node(key, val);
        if (newNode.key < prev.key) {
            prev.left = newNode;
        }else {
            prev.right = newNode;
        }
        return newNode;
    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(10, 100);
        binarySearchTree.insert(7, 70);
        binarySearchTree.insert(3, 30);
        binarySearchTree.insert(8, 80);
        binarySearchTree.insert(15,150);
        binarySearchTree.insert(12,120);
        binarySearchTree.insert(23,230);

        //通过 先序遍历 和 中序遍历 确定 root
        BinarySearchTree.preOrder(binarySearchTree.root);
        //Node{key=10, val=100} Node{key=7, val=70} Node{key=3, val=30} Node{key=8, val=80} Node{key=15, val=150} Node{key=12, val=120} Node{key=23, val=230}
        System.out.println();
        BinarySearchTree.inOrder(binarySearchTree.root);
        //Node{key=3, val=30} Node{key=7, val=70} Node{key=8, val=80} Node{key=10, val=100} Node{key=12, val=120} Node{key=15, val=150} Node{key=23, val=230}
        System.out.println();
        Node ret = binarySearchTree.find(3);
        System.out.println(ret);//Node{key=3, val=30}

    }
    public static void preOrder(Node root) {
        //先序遍历
        if (root == null) {
            return;
        }
        System.out.print(root + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void inOrder(Node root) {
        //中序遍历
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root + " ");
        inOrder(root.right);
    }
}
