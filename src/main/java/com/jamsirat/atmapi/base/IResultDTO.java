package com.jamsirat.atmapi.base;

import java.util.Map;

public interface IResultDTO <T>{

    T getResult();

    AResponseDataDTO getResponseData();

    Map<String, String> getMetadata();

}