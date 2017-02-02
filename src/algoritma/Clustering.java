/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritma;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import struktur.Cluster;
import struktur.Point;

/**
 *
 * @author Anavel
 */
public class Clustering {

    Distance distance = new Distance();
    Cluster clus = new Cluster();

    private final double eps;

    private final int minPts;

    private enum PointStatus {
        NOISE,
        PART_OF_CLUSTER
    }

    final List<List<Point>> allData = new ArrayList<>();
    List<Point> noise = new ArrayList<>();

    final JPanel panel = new JPanel();

    public Clustering(double eps, int minPts, List<Point> titikPanas) {

        allData.clear();

        this.eps = eps;
        this.minPts = minPts;

        if (eps >= 0.0d && minPts >= 0) {
            cluster(titikPanas);
            new Silhouette(allData);
            clus.setSize(allData.size());
            clus.setNoise(noise.size());
            new Denormalisasi(allData, noise);
        }

    }

    // proses utama clustering
    public List<List<Point>> cluster(final Collection<Point> points) {
        int index = 0;
        final Map<Point, PointStatus> visited = new HashMap<>();
        for (final Point point : points) {
            if (visited.get(point) != null) { //jika titik sudah dikunjungi
                continue;
            }
            final List<Point> neighbors = getNeighbors(point, points);
            if (neighbors.size() < minPts - 1) {
                visited.put(point, PointStatus.NOISE);
                noise.add(point);
            } else {
                final Cluster cluster = new Cluster();
                expandCluster(cluster, point, neighbors, points, visited); //cluster meluas
                allData.add(index, cluster.get());
                index++;
            }
        }
        System.out.println("Jumlah Cluster : " + allData.size());
        System.out.println("Jumlah Noise : " + noise.size());
        return allData;
    }

    // memperluas cluster
    private Cluster expandCluster(
            final Cluster cluster,
            final Point point,
            final List<Point> neighbors,
            final Collection<Point> points,
            final Map<Point, PointStatus> visited) {
        cluster.add(point);
        visited.put(point, PointStatus.PART_OF_CLUSTER);

        List<Point> seeds = new ArrayList<Point>(neighbors);
        int index = 0;
        while (index < seeds.size()) {
            final Point current = seeds.get(index);
            PointStatus pStatus = visited.get(current);

            if (pStatus == null) { //apabila titik belum dikunjungi
                final List<Point> currentNeighbors = getNeighbors(current, points);
                if (currentNeighbors.size() >= minPts - 1) {
                    seeds = merge(seeds, currentNeighbors); //kedua tetangga akan digabung
                }
            }

            if (pStatus != PointStatus.PART_OF_CLUSTER) { //apabila titik bukan bagian dari cluster
                visited.put(current, PointStatus.PART_OF_CLUSTER);
                noise.remove(current);
                cluster.add(current);
            }

            index++;
        }
        return cluster;
    }

    //mencari ketetanggan
    private List<Point> getNeighbors(final Point point, final Collection<Point> points) {
        final List<Point> neighbors = new ArrayList<Point>();
        for (final Point neighbor : points) {
            if (point != neighbor && distance.hitung(neighbor, point) <= eps) { //jika jarak titik kurang dari sama dengan epsilon
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    //penggabungan tetangga
    private List<Point> merge(final List<Point> one, final List<Point> two) {
        final Set<Point> oneSet = new HashSet<Point>(one);
        for (Point item : two) {
            if (!oneSet.contains(item)) {
                one.add(item);
            }
        }
        return one;
    }

}
