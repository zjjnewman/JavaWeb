package com.atguigu.test;

import org.junit.jupiter.api.Test;

import java.util.*;

public class test {

    class o{
        int v1;
        o(int v1){
            this.v1 = v1;
        }
    }



    @Test
    public void test(){
        Object o = 1;
        System.out.println((Integer) o);

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(null);
        set.add(8);
        set.add(5);
        System.out.println(set);
        int[] a = new int[]{1};
        System.out.print(Arrays.toString(a));
    }
    public void mod(o o){
        o.v1 = 2;
    }
}
