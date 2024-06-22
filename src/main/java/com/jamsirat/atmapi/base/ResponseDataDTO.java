package com.jamsirat.atmapi.base;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class ResponseDataDTO extends AResponseDataDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public ResponseDataDTO(Integer responseCode, String responseMsg) {
        super(responseCode, responseMsg);
    }

    public ResponseDataDTO(int responseCode, String responseMsg) {
        this.setResponseCode(responseCode);
        this.setResponseMsg(responseMsg);
    }

}