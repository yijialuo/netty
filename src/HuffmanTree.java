import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
//    前序
    static void showQXTree(Node node){
        //中
        if(node!=null){
            System.out.println(node);
        }
        //左
        if(node.leftNode!=null){
            showQXTree(node.leftNode);
        }
        //右
        if(node.rightNode!=null){
            showQXTree(node.rightNode);
        }
    }

    static Node getHuffmanTree(int[] arr) {
        List<Node> nodeList = new ArrayList<>();
        for (int item : arr) {
            nodeList.add(new Node(item));
        }
        while (nodeList.size()>1){
            //排序
            Collections.sort(nodeList);
            //拿前两个
            Node leftNode=nodeList.get(0);
            Node rightNode=nodeList.get(1);
            Node prents=new Node(leftNode.value+rightNode.value);
            prents.leftNode=leftNode;
            prents.rightNode=rightNode;
            //不能这样删除，删了一个后面顶了上来，remove(1)出错
//            nodeList.remove(0);
//            nodeList.remove(1);
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            nodeList.add(prents);

        }
        return nodeList.get(0);
    }

    public static void main(String[] args) {
        int[] arr= {13,7,8,3,29,6,1};
        Node node=getHuffmanTree(arr);
        showQXTree(node);
    }
}

class Node implements Comparable<Node>{
    int value;
    Node leftNode;
    Node rightNode;

    Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" + "value=" + value+"}";
    }

    @Override
    public int compareTo(Node o) {
        //从小到大
        return value-o.value;
    }
}