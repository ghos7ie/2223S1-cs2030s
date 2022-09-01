import java.util.Random;
/**
 * CS2030S Lab 0: RandomPoint.java
 * Semester 1, 2022/23
 *
 * @author Lewis [Group 14A]
 */

  class RandomPoint extends Point{
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;
    private static Random rng = new Random(1);

    public RandomPoint(double minX, double  maxX, double minY, double maxY){
      super(generateValue(minX, maxX), generateValue(minY, maxY));
    }

    public static double generateValue(double min, double max){
      double value = (rng.nextDouble() * (max - min )) + min;
      return value;
    }

    public static void setSeed(long seed){
      rng.setSeed(seed);
    }
  }
