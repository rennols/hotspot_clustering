/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritma;

import java.util.List;
import struktur.Point;

/**
 *
 * @author Anavel
 */
public class Normalisasi {

    private static double maxBrightness, maxBright_t31, maxFrp;
    private static double minBrightness, minBright_t31, minFrp;

    //normalisasi menggunakan algoritma minmax dengan rentan 0-1
    public void minMax(List<Point> Titik) {
        maxBrightness = maxBright_t31 = maxFrp = Double.MIN_VALUE;
        minBrightness = minBright_t31 = minFrp = Double.MAX_VALUE;
        double nBrightness, nBright_t31, nFrp;

        double rMax = 1.0;
        double rMin = 0.0;

        //min max fitur brightness
        for (int i = 0; i < Titik.size(); i++) {
            if (Titik.get(i).getBrightness() > maxBrightness) {
                maxBrightness = Titik.get(i).getBrightness();
            }
        }
        for (int i = 0; i < Titik.size(); i++) {
            if (Titik.get(i).getBrightness() < minBrightness) {
                minBrightness = Titik.get(i).getBrightness();
            }
        }

        //min max fitur bright_t31
        for (int i = 0; i < Titik.size(); i++) {
            if (Titik.get(i).getBright_t31() > maxBright_t31) {
                maxBright_t31 = Titik.get(i).getBright_t31();
            }
        }
        for (int i = 0; i < Titik.size(); i++) {
            if (Titik.get(i).getBright_t31() < minBright_t31) {
                minBright_t31 = Titik.get(i).getBright_t31();
            }
        }

        //min max fitur frp
        for (int i = 0; i < Titik.size(); i++) {
            if (Titik.get(i).getFrp() > maxFrp) {
                maxFrp = Titik.get(i).getFrp();
            }
        }
        for (int i = 0; i < Titik.size(); i++) {
            if (Titik.get(i).getFrp() < minFrp) {
                minFrp = Titik.get(i).getFrp();
            }
        }

        // proses normalisasi
        for (int i = 0; i < Titik.size(); i++) {
            Point t = new Point();
            nBrightness = (Titik.get(i).getBrightness() - minBrightness) / (maxBrightness - minBrightness) * ((rMax - rMin) + rMin);
            nBright_t31 = (Titik.get(i).getBright_t31() - minBright_t31) / (maxBright_t31 - minBright_t31) * ((rMax - rMin) + rMin);
            nFrp = (Titik.get(i).getFrp() - minFrp) / (maxFrp - minFrp) * ((rMax - rMin) + rMin);
            t.setBrightness(nBrightness);
            t.setBright_t31(nBright_t31);
            t.setFrp(nFrp);
            new Point().update(i, t);
        }
    }
    public double minBrightness() {
        return minBrightness;
    }
    
    public double maxBrightness() {
        return maxBrightness;
    }
    
    public double minBright_t31() {
        return minBright_t31;
    }
    
    public double maxBright_t31() {
        return maxBright_t31;
    }
    
    public double minFrp() {
        return minFrp;
    }
    
    public double maxFrp() {
        return maxFrp;
    }
}
