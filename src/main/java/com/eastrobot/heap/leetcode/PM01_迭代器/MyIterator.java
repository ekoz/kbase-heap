/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.leetcode.PM01_迭代器;

/**
 * 二维数组迭代器，可自动过滤空数组
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/1/20 15:13
 */
public class MyIterator {

    int[][] arr;
    int row = 0;
    int col = 0;


    /**
     * Constructor
     * @param arr
     */
    MyIterator(int[][] arr){
        this.arr = arr;
    }

    boolean hasNext(){
        int maxLen = arr.length;
        if (row==maxLen){
            return false;
        }
        int maxColLen = arr[row].length;
        if (col<maxColLen){
            return true;
        }
        while (row<maxLen){
            row++;
            col = 0;
            return hasNext();
        }
        return false;
    }

    int next(){
        int maxLen = arr.length;
        if (row==maxLen){
            throw new RuntimeException("数组 row 溢出");
        }
        int maxColLen = arr[row].length;
        if (col<maxColLen){
            col++;
            return arr[row][col-1];
        }
        return arr[row-1][col];
    }

    public static void main(String[] args) {
        MyIterator it = new MyIterator(new int[][]{
                {1, 2, 3},
                {4, 5},
                {},
                {},
                {7},
                {}
        });
        while (it.hasNext()){
            System.out.println(it.next());
        }

//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        list.add(6);
//        Iterator<Integer> it = list.iterator();
//        while (it.hasNext()){
//            System.out.println(it.next());
//        }



    }
}
