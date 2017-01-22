public class NBody {

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radius = readRadius(filename);
		Planet[] allPlanets = readPlanets(filename);


		StdDraw.setXscale(-radius, radius);
		StdDraw.setYscale(-radius, radius);

		double time = 0;
		int numPlants = allPlanets.length;

		StdAudio.play("./audio/2001.mid");

		while (time < T) {
			double[] xForces = new double[numPlants];
			double[] yForces = new double[numPlants];

			int counter = 0;
			for (Planet p : allPlanets) {
				xForces[counter] = p.calcNetForceExertedByX(allPlanets);
				yForces[counter] = p.calcNetForceExertedByY(allPlanets);
				counter += 1;
			}

			for (int i = 0; i < numPlants; i++) {
				allPlanets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.clear();
			StdDraw.picture(0, 0, "./images/starfield.jpg");

			for (Planet p : allPlanets) {
				p.draw();
			}

			StdDraw.show(10);		

			time = time + dt;
		}

		StdOut.printf("%d\n", numPlants);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < numPlants; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		allPlanets[i].xxPos, allPlanets[i].yyPos, 
		   		allPlanets[i].xxVel, allPlanets[i].yyVel, 
		   		allPlanets[i].mass, allPlanets[i].imgFileName);
		}




	}


	public static double readRadius(String filename) {
		In in = new In(filename);
		in.readInt();
		return in.readDouble();
	}

	public static Planet[] readPlanets(String filename) {

		In in = new In(filename);
		int numLines = in.readInt();
		Planet[] allPlanets = new Planet[numLines];
		in.readDouble();
		for (int i = 0; i < numLines; i++) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double mass = in.readDouble();
			String name = in.readString();
			allPlanets[i] = new Planet(xP, yP, xV, yV, mass, name);
		}
		return allPlanets;
	}


}	

