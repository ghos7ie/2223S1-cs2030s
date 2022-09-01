import java.lang.Math;

/**
 * CS2030S Lab 0: Point.java
 * Semester 1, 2022/23
 *
 * <p>The Point class encapsulates a point on a 2D plane.
 *
 * @author Lewis [Group 14A]
 */
class Point {
  // TODO
  private double x;
  private double y;

  public Point (double x, double y){
    this.x = x;
    this.y = y;
  }
 
  public double getX(){
    return this.x;
  }

  public double getY(){
    return this.y;
  }
  
  public double distanceTo (Point p){
    return Math.sqrt(Math.pow((p.x-this.x), 2) + Math.pow((p.y - this.y), 2));
  }

  @Override
  public String toString(){
    return "(" + this.x + ", " + this.y + ")";
  }
}
