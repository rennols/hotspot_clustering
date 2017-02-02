/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struktur;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anavel
 * @param <Point>
 */
public class Cluster {

    private List<Point> cluster = new ArrayList<Point>();
    private static List<List<Point>> allData = new ArrayList<>();
    private static List<Point> noise = new ArrayList<>();

    private static double silhouette;
    private static int clusterSize;
    private static int noiseSize;
    
    public void addData (List<List<Point>> p) {
        this.allData.addAll(p);
    }
    
    public List<List<Point>> getData() {
        return allData;
    }
    
    public void addNoise(List<Point> p) {
        this.noise.addAll(p);
    }
    
    public List<Point> getN() {
        return noise;
    }

    public void add(Point p) {
        cluster.add(p);
    }

    public List<Point> get() {
        return cluster;
    }

    public double getSilhouette() {
        return silhouette;
    }

    public void setSilhouette(double silhouette) {
        this.silhouette = silhouette;
    }

    public int getSize() {
        return clusterSize;
    }

    public void setSize(int size) {
        this.clusterSize = size;
    }
    
    public int getNoise() {
        return noiseSize;
    }
    
    public void setNoise(int noise) {
        this.noiseSize = noise;
    }
}
