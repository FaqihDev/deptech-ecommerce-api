package com.jamsirat.atmapi.BaseMapper;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface IDATAMapper<SOURCE,TARGET> extends Converter<SOURCE,TARGET> {

    List<TARGET>entitiesIntoDTOs(Iterable<SOURCE> source);

    Page<TARGET> entitiesIntoDTOsPage(Pageable pageable, Page<SOURCE> source);

    Slice<TARGET> entitiesIntoDTOSlices(Slice<SOURCE> sources);

    ConvertResponseEntity convertWithResponseEntity(SOURCE source);

    Page<TARGET> entitiesPageIntoDTOPage(Page<SOURCE> data);

}