package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String s = "tralivali";
        int i = 33;
        float f = 13.4F;
        long l = 54L;
        boolean b = true;
        byte bt = 34;
        short sh = 320;
        double d = 145.78;
        char ch = 'c';
        LOG.debug("String : {}, int : {}, float : {}, long : {}, boolean : {}, byte : {}, short : {}, double : {}, char : {}",
                s, i, f, l, b, bt, sh, d, ch);
    }
}
