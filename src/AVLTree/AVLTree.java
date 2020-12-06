package AVLTree;

public class AVLTree {


    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
//        int[] arr =  {10, 12, 8 ,9 ,7, 6};
        int[] arr =  {10, 11, 7 ,6 ,8, 9};
        AVLTree avlTree=new AVLTree(null);
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        avlTree.mid();
        System.out.println("高："+avlTree.getHigh());
        System.out.println("左："+avlTree.getLeftHigh());
        System.out.println("右："+avlTree.getRightHigh());
        System.out.println("根："+avlTree.getRoot());
        System.out.println(avlTree.getRoot().right.right.left);
    }


    Node getRoot(){
        return root;
    }

    int getLeftHigh(){
        return root.getLeftHigh();
    }

    int getRightHigh(){
        return root.getRightHigh();
    }

    int getHigh(){
        return root.getHigh();
    }

    private Node root;

    Node search(int value) {
        return root.search(value);
    }

    Node getPrents(int value) {
        return root.getParent(value);
    }

    AVLTree(Node root) {
        this.root = root;
    }

    // 找树中，最小值，删除，并返回值
    int getMin(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        return target.value;
    }

    // 删除节点
    void delNode(int value) {
        Node targetNode = getTarget(value);
        Node parentNode = getParent(value);
        // 没找到需要删除的节点
        if (targetNode == null) {
            return;
        }
        // 只有一个节点，删除的就是根节点，这种情况也是根节点，但是没有父节点，所以单独拿出来，
        // 否按照下面根节点的删除，父节点会报空指针
        if (parentNode == null && targetNode.left == null && targetNode.right == null) {
            // 删除的是根节点，
            root = null;
            return;
        }
        // 如果需要删除的节点是叶子节点 ，这种叶子节点的情况下，必须要有父节点，
        if (targetNode.left == null && targetNode.right == null) {
            // 判断这个节点是父节点的左节点还是右节点
            // 如果这个节点是父节点的左节点
            if (parentNode.left != null && parentNode.left == targetNode) {
                // 左节点置空
                parentNode.left = null;
            } else if (parentNode.right != null && parentNode.right == targetNode) {
                // 右节点置空
                parentNode.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {
            // 删除的节点有两个子树
            int mid = getMin(targetNode.right); // 找到这个目标的节点右边子树的最小节点
            // 删除这个节点
            delNode(mid);
            // 将右边子树最小的值，填充为这个节点
            targetNode.value = mid;
        } else {
            //删除的节点有一个子树
            // 目标节点的左子树有东西
            if (targetNode.left != null) {
                if (parentNode != null) {
                    // 目标节点是父节点的左节点
                    if (parentNode.left == targetNode) {
                        parentNode.left = targetNode.left;
                    } else {
                        parentNode.right = targetNode.left;
                    }
                } else {
                    root = targetNode.left;
                }
            } else { // 目标节点的右边有东西
                // 目标节点是父节点的左节点
                if (parentNode != null) {
                    if (parentNode.left == targetNode) {
                        parentNode.left = targetNode.right;
                    } else {
                        parentNode.right = targetNode.right;
                    }
                } else {
                    root = targetNode.right;
                }
            }
        }
    }

    // 查询目标节点
    Node getTarget(int value) {
        if (root == null)
            return null;
        return root.search(value);
    }

    Node getParent(int value) {
        //如果是根节点，没有父节点
        if (root.value == value)
            return null;
        return root.getParent(value);
    }

    //添加
    void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序输出
    void mid() {
        if (root == null) {
            System.out.println("当前树为空！");
        } else {
            root.mid();
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    // 右旋
    private void RightTurn(){
        Node newNode=new Node(this.value);
        newNode.right=this.right;
        newNode.left=this.left.right;
        this.value=this.left.value;
        this.left=this.left.left;
        this.right=newNode;
    }

    // 左旋
    private void LeftTurn(){
        Node newNode=new Node(this.value);
        newNode.left=this.left;
        newNode.right=this.right.left;
        this.value=this.right.value;
        this.right=this.right.right;
        this.left=newNode;
    }

    Node(int value) {
        this.value = value;
    }

    int getLeftHigh(){
        if(this.left==null)
            return 0;
        return this.left.getHigh();
    }

    int getRightHigh(){
        if(this.right==null)
            return 0;
        return this.right.getHigh();
    }

    int getHigh() {
        // 下面子树的最大高度+1；
        return Math.max(this.left == null ? 0 : this.left.getHigh(), this.right == null ? 0 : this.right.getHigh()) + 1;
    }

    /**
     * @param value 查询的节点
     * @return 返回查询节点的父节点
     */
    Node getParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        }
        if (this.left != null && value < this.value) {
            return this.left.getParent(value);
        } else if (this.right != null && value >= this.value) {
            return this.right.getParent(value);
        } else {
            return null;
        }
    }

    // 查询
    Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left != null) {
                return this.left.search(value);
            } else {
                return null;
            }
        } else {// 右边去找
            if (this.right != null) {
                return this.right.search(value);
            } else {
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    // 中序遍历 ， 左、中、右
    void mid() {
        if (this.left != null) {
            this.left.mid();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.mid();
        }
    }

    //插入
    void add(Node node) {
        if (node == null) {
            return;
        }
        //左边小
        if (node.value < this.value) {
            // 比当前节点小，插入左边
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        // 左旋， 左边矮
        if(this.getRightHigh()-this.getLeftHigh()>1){
            //如果他的右子树的左子树的高度大于他的右子树的右子树的高度
            if(this.right!=null&&this.right.getLeftHigh()>this.right.getRightHigh()){
                //先多右子节点进行右旋转
                this.right.RightTurn();
            }
            this.LeftTurn();
            // 注意加return
            return;
        }
        // 右旋， 右边矮
        if(this.getLeftHigh()-this.getRightHigh()>1){
            if(this.left!=null&&this.left.right.getHigh()>this.left.left.getHigh()){
                this.left.LeftTurn();
            }
            this.RightTurn();
            return;
        }
    }
}