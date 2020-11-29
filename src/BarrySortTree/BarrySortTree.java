package BarrySortTree;

import java.nio.file.NotDirectoryException;

public class BarrySortTree {


    public static void main(String[] args) {
//        int[] arr = {7, 3, 10, 12, 5, 1, 9};
//        BarrySortTree barrySortTree = new BarrySortTree(null);
//        for (int item : arr) {
//            barrySortTree.add(new Node(item));
//        }
//        barrySortTree.mid();
//        barrySortTree.delNode(2);
//        barrySortTree.delNode(5);
//        barrySortTree.delNode(9);
//        barrySortTree.delNode(12);
//        barrySortTree.delNode(7);
//        barrySortTree.delNode(3);
//        barrySortTree.delNode(10);
//        barrySortTree.delNode(1);
//        System.out.println("==");
//        barrySortTree.mid();

        int[] arr = {10, 1};
        BarrySortTree barrySortTree = new BarrySortTree(null);
        for (int item : arr) {
            barrySortTree.add(new Node(item));
        }
        barrySortTree.mid();
        barrySortTree.delNode(10);
        System.out.println("==");
        barrySortTree.mid();
    }

    private Node root;

    Node search(int value) {
        return root.search(value);
    }

    Node getPrents(int value) {
        return root.getParent(value);
    }

    BarrySortTree(Node root) {
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

    Node(int value) {
        this.value = value;
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
    }
}