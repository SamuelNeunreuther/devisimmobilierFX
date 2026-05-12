
package com.devisimmobilierfx.utilitaire;

public class Point {
    private float x, y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public float distance(){
        float distance;
        distance = (float)Math.sqrt(getX()*getX()+getY()*getY());
        return distance;
    }
    
    public float distance(Point point){
        float distance;
        distance = (float)Math.sqrt((this.x-point.getX())*(this.x-point.getX())+(this.y-point.getY())*(this.y-point.getY()));
        return distance;
    }
    
    public void deplacer(float dx, float dy){
        this.setX(this.x+dx);
        this.setY(this.y+dy);
    }
    
    public Point pointMilieu(Point point){
        float xMil, yMil;
        xMil=(this.x+point.getX())/2;
        yMil=(this.y+point.getY())/2;
        Point milieu = new Point(xMil, yMil);
        return milieu;
    }
    
    public Point[] voisinsProches(Point[] points){
        Point[] voisins = new Point[2];
        voisins[0]=new Point(Float.MAX_VALUE, Float.MAX_VALUE);
        voisins[1]=new Point(Float.MAX_VALUE, Float.MAX_VALUE);
        
        for(Point point : points){
            if (this.distance(point)<this.distance(voisins[0])){
                voisins[0]=point;
            }
            else if(this.distance(point)<this.distance(voisins[1])){
                voisins[1]=point;
            }
        }
        
        return voisins;
    }
    
    public boolean equals(Point point){
        return (this.x==point.getX() && this.y==point.getY());
    }
    
    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
