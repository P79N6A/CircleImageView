package com.example.zhangzhixin.test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyJunitTest {

    @Test
    public void testJunit(){
        assertEquals("6 + -2 must be 4",4,Person.add(6,-2));
        assertEquals("2 + -5 must be -3",-3,Person.add(2,-5));

    }

}
