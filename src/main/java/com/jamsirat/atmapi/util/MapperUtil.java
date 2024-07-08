package com.jamsirat.atmapi.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;

public class MapperUtil {

    private static final ModelMapper mapper = new ModelMapper();

    public static <T> T parse(Object source, Class<T> target, MatchingStrategy strategy) {
        if (source == null) {
            throw new IllegalArgumentException("Source object cannot be null");
        }

        mapper.getConfiguration().setMatchingStrategy(strategy);
        return mapper.map(source, target);
    }

    public static <T> T parse(Object source, Class<T> target) {
        return parse(source, target, MatchingStrategies.STANDARD); // Default strategy
    }
}

