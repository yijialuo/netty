package algorithm;

import java.util.Arrays;

/**
 * 最短路径
 */
public class Zdlj {
    public static void main(String[] args) {
        char[] jd = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] jz = new int[jd.length][jd.length];
        final int N = 65535;//表示不可连接
        jz[0] = new int[]{N, 5, 7, N, N, N, 2};
        jz[1] = new int[]{5, N, N, 9, N, N, 3};
        jz[2] = new int[]{7, N, N, N, 8, N, N};
        jz[3] = new int[]{N, 9, N, N, N, 4, N};
        jz[4] = new int[]{N, N, 8, N, N, 5, 4};
        jz[5] = new int[]{N, N, N, 4, 5, N, 6};
        jz[6] = new int[]{2, 3, N, N, 4, 6, N};
        Grape grape = new Grape(jd, jz);
        grape.show();
        grape.dsj(2);
        grape.resShoe();
    }

}

class VisitedVertex {
    //记录各个顶点是否访问过  1表示访问过、0未访问，会动态更新
    public int[] already_arr;
    //每个下标对应的值为前一个顶点的下标，会动态更新
    public int[] pre_visited;
    //记录出发顶点到其他所有顶点的距离，
    public int[] dis;

    /**
     * @param length 表示顶点的个数
     * @param index  出发顶点
     */
    VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化dis数组
        Arrays.fill(dis, 65535);
        //出发点自己被访问
        this.already_arr[index] = 1;
        this.dis[index] = 0;//设置出发顶点到自己的距离为0
    }

    /**
     * 判断index顶点是否被访问过
     *
     * @param index
     * @return
     */
    boolean in(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 更新出发顶点到index这个顶点的距离
     *
     * @param index
     * @param len
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新pre顶点的前驱为index节点
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     *
     * @param index
     */
    public int getDis(int index) {
        return dis[index];
    }

    //选择新的访问点
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        //更新index顶点被访问
        already_arr[index] = 1;
        return index;
    }

    //显示最后的结果
    public void resShow(){
        System.out.println("============");
        for(int i: already_arr){
            System.out.print(i+" ");
        }
        System.out.println();
        for(int i: pre_visited){
            System.out.print(i+" ");
        }
        System.out.println();
        for(int i: dis){
            System.out.print(i+" ");
        }
        System.out.println();
        //好看
        char[] jd = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count=0;
        for(int i:dis){
            if( i!=65535){
                System.out.print(jd[count]+"("+i+")");
            }else {
                System.out.print("N");
            }
            count++;
        }
        System.out.println();
    }

}

//图
class Grape {
    // 节点
    char[] jd;
    // 矩阵
    int[][] jz;

    VisitedVertex vv;

    Grape(char[] jd, int[][] jz) {
        this.jd = jd;
        this.jz = jz;
    }

    void resShoe(){
        vv.resShow();
    }

    void show() {
        for (int[] item : jz) {
            System.out.println(Arrays.toString(item));
        }
    }

    public void dsj(int index) {
        vv = new VisitedVertex(jd.length, index);
        update(index);//更新index顶点到周围顶点的距离和前驱节点
        for (int j = 1; j < jd.length; j++) {
            index=vv.updateArr();//选择并返回新的访问顶点
            update(index);
        }
    }



    private void update(int index) {
        int len = 0;
        //根据遍历矩阵，更新
        for (int j = 0; j < jz[index].length; j++) {
            //顶点到index的距离，加上index到j的距离，就是j到顶点的距离
            len = vv.getDis(index) + jz[index][j];
            //如果j这个顶点没被访问过，并且len小于出发顶点到j顶点的距离，就需要更新
            if (!vv.in(j) && len < vv.getDis(j)) {
                //更新j顶点的前驱顶点为index
                vv.updatePre(j, index);
                //更新顶点到j的距离
                vv.updateDis(j, len);
            }
        }
    }


}