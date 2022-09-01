import java.util.Scanner;

/**
 * CS2030S Lab 0: Estimating Pi with Monte Carlo
 * Semester 1, 2022/23
 *
 * <p>This program takes in two command line arguments: the 
 * number of points and a random seed.  It runs the
 * Monte Carlo simulation with the given argument and print
 * out the estimated pi value.
 *
 * @author XXX 
 */

class Lab0 {
  private static final double X = 0.5;
  private static final double Y = 0.5;
  private static final double RADIUS = 0.5;

   public static double estimatePi(int numOfPoints, int seed) {
    // step 1: create circle
    Point centre = new Point(X, Y);
     Circle circle = new Circle(centre, RADIUS);
    int countInCircle = 0;
    RandomPoint.setSeed(seed);
    // step 2: random point generation + checking if they fall in the circle
    for (int i = 0; i < numOfPoints; i++){
      // step 3: generate points (within square)
      Point p = new  RandomPoint(X -  RADIUS, X + RADIUS, Y - RADIUS, Y + RADIUS);
      // step 4: check if point generated falls out of circle
      boolean inCircle = circle.contains(p);
      // step 5: update n, points that are in of circle
      if (inCircle){
        countInCircle++;
      }
    }
    // step 6: return 4n/k, where k is numOfPoints
    return (4*countInCircle)/(double)numOfPoints;
   }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numOfPoints = sc.nextInt();
    int seed = sc.nextInt();

    double pi = estimatePi(numOfPoints, seed);

    System.out.println(pi);
    sc.close();
  }
}
