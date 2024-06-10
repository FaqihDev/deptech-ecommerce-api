package com.jamsirat.atmapi.model;


import com.jamsirat.atmapi.model.Base.BaseMasterData;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "progress")
@Entity
@Builder
@Setter
@Getter
public class Progress extends BaseMasterData implements Serializable {


}