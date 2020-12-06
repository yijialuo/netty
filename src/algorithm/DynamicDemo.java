package algorithm;

public class DynamicDemo {

    public static void main(String[] args) {
        int[] arr={1,2,4,1,7,8,3};
        System.out.println(getBigNumberNoNib(arr,arr.length-1));
        System.out.println(noDGgetBigNumberNoNib(arr,arr.length-1));
        int[]  arr2={3,34,4,12,5,2};
        System.out.println("========");
        System.out.println(arrChoicSumEqs(arr2,9));
        System.out.println(arrChoicSumEqs(arr2,10));
        System.out.println(arrChoicSumEqs(arr2,11));
        System.out.println(arrChoicSumEqs(arr2,12));
        System.out.println(arrChoicSumEqs(arr2,15));
    }

    //背包问题，

    

    //在数组中选取数字组合，看能不能加起来等于一个数
    static boolean arrChoicSumEqs(int[] arr,int target){
        return arrChoicSumEqs(arr,arr.length-1,target);
    }

    //从后往前选
    static boolean arrChoicSumEqs(int[] arr,int len,int target){
        if(target==0)
            return true;
        if(len==0)
            return arr[0]==target;
        //已经比目标大，不能选
        if(arr[len]>target){
            return arrChoicSumEqs(arr,len-1,target);
        }
        //小的话，选或不选都可以   (不选)||（选）  有一个可以就行
        return arrChoicSumEqs(arr,len-1,target)||arrChoicSumEqs(arr,len-1,target-arr[len]);
    }

    //非递归
    static int noDGgetBigNumberNoNib(int[] arr,int length){
        int[] opt=new int[arr.length];
        opt[0]=arr[0];
        opt[1]=Math.max(arr[0],arr[1]);
        for (int i = 2; i < arr.length; i++) {
            // 选，隔了一个最好的+本次
            int A=opt[i-2]+arr[i];
            //不选，前一个最好的
            int B=opt[i-1];
            opt[i]=Math.max(A,B);
        }
        return opt[length];
    }

    // 获取不相邻的数使得和最大数
    static int getBigNumberNoNib(int[] arr,int length){
        if(length==0)
            return arr[0];
        if(length==1)
            return Math.max(arr[0],arr[1]);
        //选,前面的最好的（隔了一个）+本次
        int A=getBigNumberNoNib(arr,length-2)+arr[length];
        //不选， 前面的最好的（没有隔）
        int B=getBigNumberNoNib(arr,length-1);
        return Math.max(A,B);
    }
}
