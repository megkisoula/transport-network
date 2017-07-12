package com.transport.routes.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by msoula on 11/07/2017.
 */

@Data
@ToString(of = {"name"})
@NoArgsConstructor
@AllArgsConstructor
public class Station implements Comparable<Station>{

    private String name;

    private List<Route> adjacencies;
    private double minDistance = Double.POSITIVE_INFINITY;
    private Station previous;

    public Station(String name) { this.name = name; }

    public int compareTo(Station other)
    {
        return Double.compare(minDistance, other.minDistance);
    }

}
