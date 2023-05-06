package flik;


import org.junit.Assert;
import org.junit.Test;

import static flik.Flik.isSameNumber;

public class test {

    @Test
    public void test1(){
        int a = 128;
        int b = 128;
        int c = 127;
        int d = 12;
        Assert.assertTrue(isSameNumber(a,b));
        Assert.assertTrue(!isSameNumber(a,c));
        Assert.assertTrue(!isSameNumber(a,d));
    }
}
