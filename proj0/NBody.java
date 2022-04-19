public class NBody {
    //ReadRadius
    public static double readRadius(String Path){
        In in=new In(Path);
        int NumberOfPlanets=in.readInt();
        double CosmicRadius=in.readDouble();
        return CosmicRadius;
    }
    //ReadPlanets
    public static Planet[] readPlanets(String Path){
        In in=new In(Path);
        int num=in.readInt();
        double CosmicRadius=in.readDouble();
        Planet[] AllPlanet =new Planet[num];
        for (int n=0;n<num;n++){
            double xP=in.readDouble();
            double yP=in.readDouble();
            double xV=in.readDouble();
            double yV=in.readDouble();
            double m=in.readDouble();
            String img=in.readString();
            AllPlanet[n]=new Planet(xP,yP,xV,yV,m,img);
        }
        return AllPlanet;
    }


    public static void main(String[] args) {
        double T=new Double(args[0]);
        double dt=new Double(args[1]);
        String filename =args[2];
        double r = readRadius(filename);
        Planet[] AllPlanets = readPlanets(filename);
    /*Draw the Background*/
        //Sets up the scale
/*      StdDraw.setXscale(-r, r);
        StdDraw.setYscale(-r, r);*/
        StdDraw.setScale(-r,r);
        StdDraw.enableDoubleBuffering();

        //Clear the drawing window
        StdDraw.clear();

        //Creating an Animation
        double time=0;
        int num=AllPlanets.length;

        while(time<=T){
            double[] xForces=new double[num];
            double[] yForces=new double[num];

            /*Important: For each time through the main loop, do not make any calls to update until all forces have been
             calculated and safely stored in xForces and yForces. For example, don’t call planets[0].update() until after
             the entire xForces and yForces arrays are done! The difference is subtle, but the autograder will be upset
             if you call planets[0].update before you calculate xForces[1] and yForces[1].*/
            for (int k=0;k<num;k++){
                xForces[k]=AllPlanets[k].calcNetForceExertedByX(AllPlanets);
                yForces[k]=AllPlanets[k].calcNetForceExertedByY(AllPlanets);
            }
            for (int k=0;k<num;k++){
                AllPlanets[k].update(dt,xForces[k],yForces[k]);
            }



            //Drew the image
            StdDraw.picture(0,0,"./images/starfield.jpg");
            //Drew the planets
            for (Planet p :AllPlanets){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time+=dt;


        }

        //  Printing the Universe
        StdOut.printf("%d\n", AllPlanets.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < num; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    AllPlanets[i].xxPos, AllPlanets[i].yyPos, AllPlanets[i].xxVel,
                    AllPlanets[i].yyVel, AllPlanets[i].mass, AllPlanets[i].imgFileName);
        }
    }
}