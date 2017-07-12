package com.transport.routes.Services;

import com.transport.routes.Domain.Station;
import com.transport.routes.Domain.Route;
import com.transport.routes.Domain.TransportNetwork;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by msoula on 11/07/2017.
 */


@Service
public class DistanceCalculatorService {

    public void computePaths(Station source) {
        PriorityQueue<Station> stationQueue = new PriorityQueue<Station>();
        source.setMinDistance(0.);

        stationQueue.add(source);

        while (!stationQueue.isEmpty()) {
            Station u = stationQueue.poll();

            // Visit each edge exiting u
            System.out.println("station is "+u.getAdjacencies()+" "+u.getPrevious()+ " "+u.getName()+ " "+u.getMinDistance());

            for (Route e : u.getAdjacencies()) {
                Station v = e.getTarget();
                double weight = e.getDistance();
                double distanceThroughU = u.getMinDistance() + weight;
                if (distanceThroughU < v.getMinDistance()) {
                    stationQueue.remove(u);

                    v.setMinDistance(distanceThroughU);
                    v.setPrevious(u);
                    stationQueue.add(v);
                }
            }
        }

    }



    public TransportNetwork initializeStationNetwork(){
        Station A = new Station("A");
        Station B = new Station("B");
        Station C = new Station("C");
        Station D = new Station("D");
        Station E = new Station("E");

        // set the edges and weight

        A.setAdjacencies(Arrays.asList(new Route(B, 3)));

        B.setAdjacencies(Arrays.asList(new Route(A, 3),new Route(C, 7)));
        C.setAdjacencies(Arrays.asList(new Route(D, 8),new Route(E, 3)));

        D.setAdjacencies(Arrays.asList(new Route(B, 5),new Route(E, 9),new Route(C, 8) ));
        E.setAdjacencies(Arrays.asList(new Route(D, 9)));

        HashMap<String,Station> stations = new HashMap<>();
        stations.put("A",A);
        stations.put("B",B);
        stations.put("C",C);
        stations.put("D",D);
        stations.put("E",E);


        TransportNetwork network = new TransportNetwork();
        network.setStationList(stations);
        return network;
    }

    public List<Station> getShortestPathTo(Station target)
    {
        List<Station> path = new ArrayList<Station>();
        for (Station station = target; station != null; station = station.getPrevious())
            path.add(station);

        Collections.reverse(path);
        return path;
    }

}
