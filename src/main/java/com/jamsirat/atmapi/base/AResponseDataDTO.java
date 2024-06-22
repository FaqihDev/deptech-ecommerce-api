package com.jamsirat.atmapi.base;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.StdConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AResponseDataDTO {

    @Setter
    @JsonDeserialize(converter = ResponseCodeConverter.class)
    private Integer responseCode;
    private String responseMsg;


    public void setResponseCode(HttpStatus status) {
        this.responseCode = status.value();
    }

    private static class ResponseCodeConverter extends StdConverter<String, Integer> {

        @Override
        public Integer convert(String value) {
            return Integer.valueOf(value.replaceAll("\\D+",""));
        }
    }

}