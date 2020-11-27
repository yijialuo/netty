import sun.util.resources.cldr.ha.CalendarData_ha_Latn_GH;

import java.io.*;
import java.util.*;

public class StringHuffmanTree {

    static Map<Byte, String> hfCode = new HashMap<>();

    static StringBuilder stringBuilder = new StringBuilder();

    static Map hftreeTohfcode(HFNode hfNode) {
        if (hfNode == null)
            return null;
        hftreeTohfcode(hfNode.left, "0", stringBuilder);
        hftreeTohfcode(hfNode.right, "1", stringBuilder);
        return hfCode;
    }

    static void hftreeTohfcode(HFNode hfNode, String code, StringBuilder stringBuilder) {
        if (hfNode == null)
            return;
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (hfNode.data == null) {// 不是叶子节点
            hftreeTohfcode(hfNode.left, "0", stringBuilder2);
            hftreeTohfcode(hfNode.right, "1", stringBuilder2);
        } else {
            // 叶子节点，递归结束
            hfCode.put(hfNode.data, stringBuilder2.toString());
        }
    }

    //需要变为霍夫曼的数组，
    static List stringToListByteAndInt(byte[] Bytes) {
        List<HFNode> res = new ArrayList<>();
        Map<Byte, Integer> map = new HashMap();
        for (Byte b : Bytes) {
            Integer count = map.get(b);
            if (count == null) {
                map.put(b, 1);
            } else {
                map.put(b, count + 1);
            }
        }
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            HFNode hfNode = new HFNode(entry.getKey(), entry.getValue());
            res.add(hfNode);
        }
        return res;
    }

    static String byteToBitString(boolean flag,byte b){
        int temp=b;
        if(flag){
            temp|=256;
        }
        String str=Integer.toBinaryString(temp);
        if(flag){
            return str.substring(str.length()-8);
        }else {
            return str;
        }
    }

    static byte[] decode(Map<Byte,String> hfCode,byte[] huffmanBytes){
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < huffmanBytes.length ; i++) {
            stringBuilder.append(byteToBitString(!(i==(huffmanBytes.length-1)),huffmanBytes[i]));
        }
        //翻转map
        Map<String,Byte> map=new HashMap<>();
        for (Map.Entry<Byte,String> entry: hfCode.entrySet()) {
            map.put(entry.getValue(),entry.getKey());
        }
        List<Byte> bytes=new ArrayList<>();
        //查询
        for (int i = 0; i < stringBuilder.length(); ) {
            String a;
            int count=i+1;
            while (true){
                a=stringBuilder.substring(i,count);
                if(map.get(a)==null){
                    count++;
                }else {
                    bytes.add(map.get(a));
                    i=count;
                    break;
                }
            }
        }
        byte[] res=new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            res[i]=bytes.get(i);
        }
        return res;
    }

    // 将数组变为霍夫曼树
    static HFNode arrToHFtree(List<HFNode> arr) {
        while (arr.size() > 1) {
            Collections.sort(arr);
            HFNode leftnode = arr.get(0);
            HFNode rightnode = arr.get(1);
            HFNode prents = new HFNode(null, leftnode.weight + rightnode.weight);
            prents.left = leftnode;
            prents.right = rightnode;
            arr.remove(leftnode);
            arr.remove(rightnode);
            arr.add(prents);
        }
        return arr.get(0);
    }

    //压缩后的byte[]
    static byte[] getHfByte(byte[] s, Map<Byte, String> hfCode) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : s) {
            // 变为霍夫曼字符串
            stringBuilder.append(hfCode.get(b));
        }
        System.out.println("传输对应的二进制");
        System.out.println(stringBuilder);
        //将霍夫曼字符串变为 byte数组
        //一个字节占8位
        int indexs = (stringBuilder.length() + 7) / 8;
        byte[] res = new byte[indexs];
        int indx = 0;
        for (int i = 0; i < stringBuilder.length(); i = i + 8) {
            if (i + 8 > stringBuilder.length()) {
                //i+8超了字符长
                res[indx] = (byte) Integer.parseInt(stringBuilder.substring(i),2); //转为二进制

            } else {
                res[indx] = (byte) Integer.parseInt(stringBuilder.substring(i, i + 8),2);
            }
            indx++;
        }
        return res;
    }

    static void unzip(String zipFile,String dstFile){
        InputStream is=null;
        ObjectInputStream ois=null;
        OutputStream os=null;
        try{
            is=new FileInputStream(zipFile);
            ois=new ObjectInputStream(is);

            byte[] huffmanBytes=(byte[])ois.readObject();

            Map<Byte,String> map=(Map)ois.readObject();
            byte[] bytes=decode(map,huffmanBytes);
            os=new FileOutputStream(dstFile);
            os.write(bytes);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                is.close();
                ois.close();
                os.close();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    static byte[] zip(byte[] srcBytes){
        List<HFNode> list = stringToListByteAndInt(srcBytes);
        HFNode hfNode = arrToHFtree(list);
        Map map=hftreeTohfcode(hfNode);
        return getHfByte(srcBytes, map);
    }

    static void zipFile(String srcFile,String dstFile){
        OutputStream os=null;
        ObjectOutputStream oos=null;
        FileInputStream is=null;
        try {
            is = new FileInputStream(srcFile);
            byte[] b=new byte[is.available()];
            //读文件
            is.read(b);
            //压缩
            byte[] huffmanBytes=zip(b);
            //创建文件输出流，存放压缩文件
            os=new FileOutputStream(dstFile);
            oos=new ObjectOutputStream(os);

            oos.writeObject(huffmanBytes);
            oos.writeObject(hfCode);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                os.close();
                oos.close();
                is.close();
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }


    public static void main(String[] args) {

//        zipFile("d://a.png","d://a.zip");
 //           unzip("d://a.zip","d://aa.png");
  //      System.out.println("ok");
        String s = "i like like like java do you like a java";
        System.out.println("传输过去的字符串：");
        System.out.println(s);
        System.out.println("原始字节数组：");
        System.out.println(Arrays.toString(s.getBytes()));
        List<HFNode> list = stringToListByteAndInt(s.getBytes());
        HFNode hfNode = arrToHFtree(list);
        System.out.println("生成的霍夫曼树：");
        hfNode.showQX(hfNode);
        Map map=hftreeTohfcode(hfNode);
        System.out.println("对应的霍夫曼编码");
        System.out.println(map);
        byte[] hfByte = getHfByte(s.getBytes(), map);
        System.out.println("传输过去的字节数组");
        System.out.println(Arrays.toString(hfByte));
        System.out.println(new String(decode(map,hfByte)));
    }

}


class HFNode implements Comparable<HFNode> {
    Byte data;// 数据
    int weight;// 权重
    HFNode left;
    HFNode right;

    // 前序
    void showQX(HFNode hfNode) {
        System.out.println(hfNode);
        if (hfNode.left != null) {
            showQX(hfNode.left);
        }
        if (hfNode.right != null) {
            showQX(hfNode.right);
        }
    }

    HFNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "HFNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(HFNode o) {
        // 从小到大
        return this.weight - o.weight;
    }
}