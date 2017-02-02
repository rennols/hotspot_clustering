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
 */
public class Point {

    private double brightness;
    private double bright_t31;
    private double frp;

    private static List<Point> points = new ArrayList<Point>();

    public double getBrightness() {
        return brightness;
    }

    public void setBrightness(double brightness) {
        this.brightness = brightness;
    }

    public double getBright_t31() {
        return bright_t31;
    }

    public void setBright_t31(double bright_t31) {
        this.bright_t31 = bright_t31;
    }

    public double getFrp() {
        return frp;
    }

    public void setFrp(double frp) {
        this.frp = frp;
    }
    
    public void add(Point point) {
        points.add(point);
    }
    
    public List<Point> get() {
        return points;
    }
    
    public void clear() {
        points.clear();
    }
    
    public int size() {
        return points.size();
    }
    
    public void update(int index, Point point) {
        points.set(index, point);
    }


}
