package com.zj.datastruct.sort.util;

/**
 * @author zhoujun07 <zhoujun07@kuaishou.com>
 * Created on 2021-07-01
 */
public abstract class Sort<T extends Comparable<T>>{

    public abstract void sort(T[] arr);

    protected boolean less(T v, T w){
        return v.compareTo(w) < 0;
    }

    protected void swap(T[] arr, int i, int j){
        T t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
