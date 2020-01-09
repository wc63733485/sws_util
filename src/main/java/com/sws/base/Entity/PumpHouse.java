package com.sws.base.Entity;

import com.sws.base.annotations.Entity;
import lombok.Data;

@Data
@Entity("pump_house")
public class PumpHouse extends BaseEntity {

    public String address;
}
