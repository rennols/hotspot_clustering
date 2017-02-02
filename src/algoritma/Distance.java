/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritma;

import struktur.Point;

/**
 *
 * @author Anavel
 */
public class Distance {

    //mencari jarak (euclidian)
    public double hitung(Point neighbor, Point point) {
        
        double brightness = point.getBrightness() - neighbor.getBrightness();
        double bright_t31 = point.getBright_t31() - neighbor.getBright_t31();
        double frp = point.getFrp() - neighbor.getFrp();
        final double distance = Math.pow(brightness, 2) + Math.pow(bright_t31, 2) + Math.pow(frp, 2);
        return Math.sqrt(distance);
    }
}
