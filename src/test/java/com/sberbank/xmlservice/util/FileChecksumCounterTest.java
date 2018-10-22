package com.sberbank.xmlservice.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FileChecksumCounterTest implements XmlFileUtilsTest {

    @Parameterized.Parameters()
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"files/1.xml", "4297f44b13955235245b2497399d7a93"},
                {"files/2.xml", "79b7cdcd14db14e9cb498f1793817d69"},
                {"files/3.xml", "15de21c670ae7c3f6f3f1f37029303c9"}
        });
    }

    @Before
    public void setUp() throws Exception {
        sut = new FileChecksumCounter();
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
    }

    @Test
    public void getChecksumTest() throws IOException, NoSuchAlgorithmException {
        sut = new FileChecksumCounter();
        Assert.assertEquals(expectedMd5CheckSum, sut.getChecksum(getAbsolutePath(testPath), "MD5"));
    }

    @Parameterized.Parameter
    public String testPath;
    @Parameterized.Parameter(1)
    public String expectedMd5CheckSum;
    private CheckSumCounter sut;
}
