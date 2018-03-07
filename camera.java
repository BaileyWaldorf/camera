// Bailey Waldorf
// Camera
// 3/2/18

import java.util.*;
import java.io.*;
import java.util.Scanner;

class Particle {
    public double x, y, max;

    // setting all values in constructor
    public Particle(double x, double y, double max) {
        this.x = x;
        this.y = y;
        this.max = max;
    }
}

// sorts my objects based on their max x value on their interval
class Sorter implements Comparator<Particle> {
     public int compare(Particle obj1, Particle obj2) {
         if(obj1.max > obj2.max)
               return 1;
         if(obj1.max < obj2.max)
               return -1;
         else
               return 0;
     }
}

public class camera {
     public static void main(String[] args) throws FileNotFoundException {
          Scanner sc = new Scanner(new File("case025.in"));

          // read in values
          int numParticles = sc.nextInt();
          double distance = sc.nextInt();

          // incase I get bad values
          if(numParticles < 1 || distance < 1) {
               System.out.println(0);
               return;
          }

          double glassBeg = sc.nextInt();
          double glassEnd = sc.nextInt();

          // create array of Particles
          Particle[] Particle = new Particle[numParticles];
          PriorityQueue<Particle> pq = new PriorityQueue<Particle>(numParticles, new Sorter());
          LinkedList<Integer> pqe = new LinkedList<>();

          // creates Particles with x and y values
          for(int i = 0; i < numParticles; i++) {
               double x = sc.nextInt();
               double y = sc.nextInt();

               // creates Particles and defines right side of interval where it can be seen
               makeParticles(x, y, glassEnd, distance, Particle, i);
               if(Particle[i].y > distance)
                    pq.add(Particle[i]);
          }

          // checks if there are no values behind glass
          if(pq.size() == 0) {
               System.out.println(1);
               return;
          }

          // starts my loop to test each object
          int pictures = 0;
          while(pq.size() > 0) {

               // if pq has at least 1 element you have to take a pic
               int numParticlesInPicture = 1;
               pictures++;

               // find camera location (x intercept of rightmost object) to move camera to
               double cameraLoc = pq.peek().max;

               // remove that object we dont need it anymore, we just need the x intercept
               pq.remove();

               // continue finding visible objects in frame and remove them from pq
               while(pq.size() > 0) {

                    // find slope from x-intercept to beginning of glass (leftmost view)
                    double wallSlope = slope(cameraLoc, 0, glassBeg, distance);

                    // find slope from x-intercept to the next object in pq (to see if its visible)
                    double pointSlope = slope(cameraLoc, 0, pq.peek().x, pq.peek().y);

                    // case if there is a verticle slope (NaN)
                    if(wallSlope == 0 && pointSlope > 0)
                         pq.remove();
                    // case 1; -/+; automatically in view
                    if(wallSlope < 0 && pointSlope >= 0)
                         pq.remove();
                    // case 2; -/- or +/+; check if slope point <= wall
                    else if(pointSlope <= wallSlope && wallSlope != 0)
                         pq.remove();
                    // point is out of viewable area, all points to the left of that point will be too; stop.
                    else
                         break;
               }
          }
          // print out the result!
          System.out.println(pictures);
     }

     public static void makeParticles(double x, double y, double glassEnd, double distance, Particle[] Particle, int i) {
          // calculate right x on interval
          double max;
          double slope = slope(x, y, glassEnd, distance);
          double yIntercept = yIntercept(x, y, glassEnd, distance);

          // check if its a verticle slope
          if(Double.isNaN(yIntercept))
               max = x;
          else
               max = ((-1) * yIntercept) / slope;

          // creates the particle with its coordinates and max x it can be seen from
          Particle[i] = new Particle(x, y, max);
     }

     private static double slope(double x1, double y1, double x2, double y2) {
          double slope = (y2 - y1)/(x2 - x1);
          if(Double.isInfinite(slope))
               return 0;
          else
               return slope;
     }

     private static double yIntercept(double x1, double y1, double x2, double y2) {
          double slope = slope(x1, y1, x2, y2);
          if(slope == 0)
               return Double.NaN;
          return y1 - (slope * x1);
     }
}
