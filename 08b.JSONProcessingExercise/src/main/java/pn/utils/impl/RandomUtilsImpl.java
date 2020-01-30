package pn.utils.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pn.utils.api.RandomUtils;

import java.util.Random;

@Component
public class RandomUtilsImpl implements RandomUtils {

    private final Random random;

    @Autowired
    public RandomUtilsImpl(Random random) {
        this.random = random;
    }

    @Override
    public int randomInt(int min, int max) {
        return this.random.nextInt((max - min) + 1) + min;
    }
}
