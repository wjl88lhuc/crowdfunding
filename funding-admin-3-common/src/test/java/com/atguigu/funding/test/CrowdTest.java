package com.atguigu.funding.test;

import com.atguigu.funding.util.CrowdFundingUtils;
import org.junit.Test;

public class CrowdTest {
    @Test
    public void testMd5(){
        String output = CrowdFundingUtils.md5("123123");
        System.out.println(output);
    }
}
