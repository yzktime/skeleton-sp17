public class Planet {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistanceSquare(Planet p) {
		double xDiff = p.xxPos - xxPos;
		double yDiff = p.yyPos - yyPos;
		return (xDiff*xDiff + yDiff*yDiff);
	}

	public double calcDistance(Planet p) {
		return Math.sqrt(calcDistanceSquare(p));
	}

	public double calcForceExertedBy(Planet p) {
		return 6.67e-11 * mass * p.mass / calcDistanceSquare(p);
	}

	public double calcForceExertedByX(Planet p) {
		return calcForceExertedBy(p)*(p.xxPos-xxPos)/calcDistance(p);
	}

	public double calcForceExertedByY(Planet p) {
		return calcForceExertedBy(p)*(p.yyPos-yyPos)/calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double total = 0;
		for (Planet p : allPlanets) {
			if (!this.equals(p)) {
				total += calcForceExertedByX(p);
			}
		}
		return total;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double total = 0;
		for (Planet p : allPlanets) {
			if (!this.equals(p)) {
				total += calcForceExertedByY(p);
			}
		}
		return total;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX / mass;
		double aY = fY / mass;
		xxVel = xxVel+dt*aX;
		yyVel = yyVel+dt*aY;
		xxPos = xxPos+dt*xxVel;
		yyPos = yyPos+dt*yyVel;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
	}

}
