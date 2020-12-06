package algorithm;

public class Hanotower {
    public static void main(String[] args) {
        hanoiTower(64,'A','B','C');
    }

    public static void hanoiTower(int num,char a,char b,char c){
        //如果只有一个盘
        if(num==1){
            System.out.println("第1个盘从"+a+"->"+c);
        }else{
            //如果有n>=2情况，我们总可以看成是两个盘，
            // 最下面一个盘，上面所以的为一个盘
            //1 ，把上面所有的盘从A->B,移动过程会使用c
            hanoiTower(num-1,a,c,b);
            //2 把最下面的盘A->C
            System.out.println("第"+num+"个盘从"+a+"->"+c);
            //3 把B塔的所有移动到C，B->,移动过程使用a
            hanoiTower(num-1,b,a,c);
        }
    }
}
