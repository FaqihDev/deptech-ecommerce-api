package com.jamsirat.atmapi.BaseMapper;

import lombok.Getter;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;


@Getter
public abstract class ADATAMapper<SOURCE,TARGET> implements IDATAMapper<SOURCE,TARGET> {

    protected boolean isBatch = false;

    protected void prepareData(List<SOURCE> sources) {

    }

    @Override
    public List<TARGET> entitiesIntoDTOs(Iterable<SOURCE> entities) {
        List<SOURCE> sources = new ArrayList<SOURCE>();
        entities.forEach(sources::add);
        prepareData(sources);
        this.isBatch = true;
        List<TARGET> targets = new ArrayList<>();
        for (SOURCE source : sources) {
            targets.add(convert(source));
        }
        this.isBatch = false;
        return targets;
    }


    @Override
    public Page<TARGET> entitiesIntoDTOsPage(Pageable pageRequest, Page<SOURCE> entities) {
        List<TARGET> targetList = entitiesIntoDTOs(entities.getContent());
        return new PageImpl<>(targetList,pageRequest,entities.getTotalElements());
    }

    @Override
    public Slice<TARGET> entitiesIntoDTOSlices(Slice<SOURCE> entities) {
        List<TARGET> targets = entitiesIntoDTOs(entities.getContent());
        return new SliceImpl<>(targets,entities.getPageable(),entities.hasNext());
    }

    @Override
    public abstract TARGET convert(SOURCE source);

    @Override
    public Page<TARGET> entitiesPageIntoDTOPage(Page<SOURCE> data) {
        return new PageImpl<>(
                entitiesIntoDTOs(data.getContent()),
                data.getPageable(), data.getTotalElements()
        );
    }

    @Override
    public ConvertResponseEntity convertWithResponseEntity(SOURCE source) {
        return null;
    }

}