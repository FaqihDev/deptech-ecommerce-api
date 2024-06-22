package com.jamsirat.atmapi.base;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResultDTO <T> extends BaseResultDTO<T, ResponseDataDTO> {
    private static final long serialVersionUUID = 1L;
}