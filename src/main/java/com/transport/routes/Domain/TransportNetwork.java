package com.transport.routes.Domain;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by msoula on 11/07/2017.
 */
@Data
public class TransportNetwork {

    private HashMap<String,Station> stationList;

}
