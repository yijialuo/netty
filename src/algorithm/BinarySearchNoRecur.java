package algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class BinarySearchNoRecur {


    public static void main(String[] args) {
//        Random random=new Random();
//        int[] arr=new int[20];
//        for (int i = 0; i < 20; i++) {
//            arr[i]=random.nextInt(30)+1;
//        }
//        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(binarySearch(arr,3));

        int[] nums={2,2};
        System.out.println(Arrays.toString(searchRange(nums,2)));
    }



    static public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int ringht =nums.length-1;
        int targetIndex=-1;
        while (left<=ringht){
            int mid=(left+ringht)/2;
            if(nums[mid]==target){
                targetIndex=mid;
                break;
            }else if(target<nums[mid]){
                ringht=mid-1;
            }else {
                left=mid+1;
            }
        }
        if(targetIndex==-1)
            return new int[]{-1,-1};
        int q=targetIndex;
        int h=targetIndex;
        //前一个也是，往前面移动
        while (q>0&&nums[q-1]==target){
            q--;
        }
        while (h<nums.length-1&&nums[h+1]==target){
            h++;
        }
        return new int[]{q,h};
    }









    static int binarySearch(int[] arr, int target){
        int left=0;
        int right=arr.length-1;
        while (left<=right){
            int mid=(left+right)/2;
            if(arr[mid]==target){
                return mid;
            }else if(arr[mid]<target){
                left=mid+1;
            }else {
                right=mid-1;
            }
        }
        return -1;
    }
}
