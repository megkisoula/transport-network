package com.transport.routes.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by msoula on 11/07/2017.
 */

@Data
@ToString
@NoArgsConstructor
public class Route {

    private Station target;
    private double distance;

    public Route(Station argTarget, double distance){
        this.target = argTarget;
        this.distance = distance;
    }
}
