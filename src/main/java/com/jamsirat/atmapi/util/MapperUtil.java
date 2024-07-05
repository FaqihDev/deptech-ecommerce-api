package com.jamsirat.atmapi.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MatchingStrategy;

public class MapperUtil {

    public static <T> T parse(Object source, Class<T> target, MatchingStrategy strategy){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(strategy);
        return mapper.map(source, target);
    }

}
