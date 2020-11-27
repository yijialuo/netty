package BarrySortTree;

import java.nio.file.NotDirectoryException;

public class BarrySortTree {


    public static void main(String[] args) {
        int[] arr={7,3,10,12,5,1,9};
        BarrySortTree barrySortTree=new BarrySortTree(null);
        for (int item:arr) {
            barrySortTree.add(new Node(item));
        }
        barrySortTree.mid();
        System.out.println("======");
        System.out.println(barrySortTree.getPrents(7));
        System.out.println(barrySortTree.getPrents(3));
        System.out.println(barrySortTree.getPrents(10));
        System.out.println(barrySortTree.getPrents(12));
        System.out.println(barrySortTree.getPrents(5));
        System.out.println(barrySortTree.getPrents(1));
        System.out.println(barrySortTree.getPrents(9));
    }

    private Node root;

    Node search(int value){
        return root.search(value);
    }

    Node getPrents(int value){
        return root.getPrents(value);
    }

    BarrySortTree(Node root) {
        this.root = root;
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
     *
     * @param value 查询的节点
     * @return  返回查询节点的父节点
     */
    Node getPrents(int value){
        if((this.left!=null&&this.left.value==value)||(this.right!=null&&this.right.value==value)){
            return this;
        }
        if(this.left!=null&&value<this.value){
            return this.left.getPrents(value);
        }else if(this.right!=null&&value>=this.value){
            return this.right.getPrents(value);
        }else {
            return null;
        }
    }

    // 查询
    Node search(int value){
        if(value==this.value){
            return this;
        }else if (value<this.value){
            if(this.left!=null){
                return this.left.search(value);
            }else {
                return null;
            }
        }else {// 右边去找
            if(this.right!=null){
                return this.right.search(value);
            }else {
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