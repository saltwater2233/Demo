package com.saltwater.junitdempo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/03/16
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class TestClassTest {

    private TestClass testClass;
    @Before
    public void setUp() throws Exception {
        testClass = new TestClass();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void jia() throws Exception {
        assertEquals(2,testClass.jia(1,1),0);
    }

    @Test
    public void cheng() throws Exception {
        assertEquals(2,testClass.cheng(1,2),0);
    }

}