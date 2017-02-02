/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritma;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import struktur.Cluster;
import struktur.Point;

/**
 *
 * @author Zaiross
 */
public class Denormalisasi {

    private static List<List<Point>> allData;
    private static List<Point> noise;
    private double readrate;
    private double readnoise;
    private final Normalisasi norm = new Normalisasi();
    private final Cluster clus = new Cluster();
    double nBrightness, nBright_t31, nFrp;

    String pattern = "#.#";
    DecimalFormat decimalFormat = new DecimalFormat(pattern);

    public Denormalisasi(List<List<Point>> allData, List<Point> noise) {
        this.allData = allData;
        this.noise = noise;
        denormalisasi();
        readnoise();
        rate(allData);
        clus.addData(allData);
        clus.addNoise(noise);
    }

    private void denormalisasi() {
        for (int i = 0; i < allData.size(); i++) {
            for (int j = 0; j < allData.get(i).size(); j++) {
                Point t = new Point();
                nBrightness = allData.get(i).get(j).getBrightness() * (norm.maxBrightness() - norm.minBrightness()) + norm.minBrightness();
                nBright_t31 = allData.get(i).get(j).getBright_t31() * (norm.maxBright_t31() - norm.minBright_t31()) + norm.minBright_t31();
                nFrp = allData.get(i).get(j).getFrp() * (norm.maxFrp() - norm.minFrp()) + norm.minFrp();
                t.setBrightness(Double.parseDouble(decimalFormat.format(nBrightness)));
                t.setBright_t31(Double.parseDouble(decimalFormat.format(nBright_t31)));
                t.setFrp(Double.parseDouble(decimalFormat.format(nFrp)));
                dataUpdate(i, j, t);
            }
        }
    }

    private void readnoise() {
        for (int i = 0; i < noise.size(); i++) {
            Point t = new Point();
            nBrightness = noise.get(i).getBrightness() * (norm.maxBrightness() - norm.minBrightness()) + norm.minBrightness();
            nBright_t31 = noise.get(i).getBright_t31() * (norm.maxBright_t31() - norm.minBright_t31()) + norm.minBright_t31();
            nFrp = noise.get(i).getFrp() * (norm.maxFrp() - norm.minFrp()) + norm.minFrp();
            t.setBrightness(Double.parseDouble(decimalFormat.format(nBrightness)));
            t.setBright_t31(Double.parseDouble(decimalFormat.format(nBright_t31)));
            t.setFrp(Double.parseDouble(decimalFormat.format(nFrp)));
            noiseUpdate(i, t);
        }
    }

    public void dataUpdate(int i, int j, Point point) {
        allData.get(i).set(j, point);
    }

    public void noiseUpdate(int index, Point point) {
        noise.set(index, point);
    }

    public void rate(List<List<Point>> allData) {
        for (int i = 0; i < allData.size(); i++) {
            double brightness = 0;
            double bright_t31 = 0;
            double frp = 0;
            double ratab = 0;
            double ratab31 = 0;
            double rataf = 0;
            for (int j = 0; j < allData.get(i).size(); j++) {
                brightness += allData.get(i).get(j).getBrightness();
                bright_t31 += allData.get(i).get(j).getBright_t31();
                frp += allData.get(i).get(j).getFrp();
            }
            ratab = brightness / allData.get(i).size();
            ratab31 = bright_t31 / allData.get(i).size();
            rataf = frp / allData.get(i).size();
            System.out.println("Rata Brightness Cluster "+i+" : "+Double.parseDouble(decimalFormat.format(ratab)));
            System.out.println("Rata Bright_t31 Cluster "+i+" : "+Double.parseDouble(decimalFormat.format(ratab31)));
            System.out.println("Rata Frp Cluster "+i+" : "+Double.parseDouble(decimalFormat.format(rataf)));
        }
    }

}
