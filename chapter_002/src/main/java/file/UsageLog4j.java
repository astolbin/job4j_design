package file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 1;
        LOG.debug("debug message for byte var: {}", b);

        short s = 2;
        LOG.debug("debug message for byte var: {}", s);

        int i = 3;
        LOG.debug("debug message for byte var: {}", i);

        long l = 4;
        LOG.debug("debug message for byte var: {}", l);

        float f = 5.1f;
        LOG.debug("debug message for byte var: {}", f);

        double d = 6.5;
        LOG.debug("debug message for byte var: {}", d);

        char c = 'a';
        LOG.debug("debug message for byte var: {}", c);

        boolean bool = true;
        LOG.debug("debug message for byte var: {}", bool);
    }
}
