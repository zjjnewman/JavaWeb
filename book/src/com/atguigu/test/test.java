package com.atguigu.test;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

    }
    public void mod(o o){
        o.v1 = 2;
    }
}
