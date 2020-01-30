package pn.utils.impl;

import org.springframework.stereotype.Component;
import pn.utils.api.RandomUtils;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomUtilsImpl implements RandomUtils {
    @Override
    public int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
