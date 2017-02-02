/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritma;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import struktur.Cluster;
import struktur.Point;

/**
 *
 * @author Anavel
 */
public class Silhouette {

    Distance distance = new Distance();
    Cluster clus = new Cluster();

    private List<List<Point>> allData;

    private enum PointStatus {
        VISITED
    }

    private int size;
    private double silhouette;
    private double meanA;
    private double meanB;

    public Silhouette(List<List<Point>> allData) {
        this.allData = allData;
        jumlahData();
        SilhouetteIndex();
        clus.setSilhouette(silhouette);
    }

    private int jumlahData() {
        for (List<Point> p : allData) {
            size += p.size();
        }
        return size;
    }

    public double SilhouetteIndex() {

        if (allData.size() <= 1) {
            return silhouette = 0;
        }
        for (int i = 0; i < allData.size(); i++) {
            for (int o = 0; o < allData.get(i).size(); o++) {
                double a = 0;
                if (allData.get(i).size() <= 1) {
                    a = 0;
                } else {
                    for (Point p : allData.get(i)) {
                        if (allData.get(i).get(o) != p) {
                            a += distance.hitung(allData.get(i).get(o), p);
                        }
                    }
                    a = a / (allData.get(i).size() - 1);
                }
                double b = 0;
                double min = Double.MAX_VALUE;
                double s = 0;
                for (List<Point> l : allData) {
                    if (allData.get(i) != l) {
                        for (Point z : l) {
                            b += distance.hitung(allData.get(i).get(o), z);
                        }
                        b = b / l.size();

                        if (b <= min) {
                            min = b;
                        }
                    }
                }
                s = (min - a) / (Math.max(a, min));
                silhouette = silhouette + s;
                meanA = meanA + a;
                meanB = meanB + min;
            }

        }
        silhouette = silhouette / size;
        meanA = meanA / size;
        meanB = meanB / size;
        System.out.println("Jarak Intra Cluster : " + meanA);
        System.out.println("Jarak Inter Cluster : " + meanB);
        System.out.println("Silhouette Index : " + silhouette);
        return silhouette;
    }
}
