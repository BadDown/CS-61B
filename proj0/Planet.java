public class Planet {
	//Its current x position
	public double xxPos;
	//Its current y position
	public double yyPos;
	//Its current velocity in the x direction
	public double xxVel;
	//Its current velocity in the y direction
	public double yyVel;
	//Its mass
	public double mass;
	//The name of the file that corresponds to the image that depicts the planet
	public String imgFileName;
	//the gravitational constant
	private static final double G = 6.67e-11;

	//Initialize a planet
	public Planet(double xP, double yP, double xV,double yV, double m, String img){
		this.xxPos=xP;
		this.yyPos=yP;
		this.xxVel=xV;
		this.yyVel=yV;
		this.mass=m;
		this.imgFileName=img;
	}

	//Copy Planet p
	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	//Calculate distance
	public double calcDistance(Planet p){
		return Math.sqrt((xxPos - p.xxPos)*(xxPos - p.xxPos)+(yyPos - p.yyPos)*(yyPos - p.yyPos));
	}

	//Calculate ForceExerted
	public double calcForceExertedBy(Planet p){
		return (G*this.mass*p.mass)/((this.calcDistance(p))*(this.calcDistance(p)));
	}

	//Calculate the net X and Y force exerted
	public double calcForceExertedByX(Planet[] allPlanets) {
		double NetForceExertedByX=0;
		for (Planet p:allPlanets){
			if(!this.equals(p)){
				double Distance_xy=p.xxPos-this.xxPos;
				NetForceExertedByX+=(this.calcForceExertedBy(p)*Distance_xy)/this.calcDistance(p);
			}
		}
		return NetForceExertedByX;
	}
	public double calcForceExertedByY(Planet[] allPlanets) {
		double NetForceExertedByY=0;
		for (Planet p:allPlanets){
			if(!this.equals(p)){
				double Distance_xy=p.yyPos-this.yyPos;
				NetForceExertedByY+=(this.calcForceExertedBy(p)*Distance_xy)/this.calcDistance(p);
			}
		}
		return NetForceExertedByY;
	}

	//Update the rate and the pos
	public void update(double time,double NetForceExertedByX,double NetForceExertedByY){
		double NetAccelerationByX=NetForceExertedByX/this.mass;
		double NetAccelerationByY=NetForceExertedByY/this.mass;
		this.xxVel=this.xxVel+time*NetAccelerationByX;
		this.yyVel=this.yyVel+time*NetAccelerationByY;
		this.xxPos=this.xxPos+time*this.xxVel;
		this.yyPos=this.yyPos+time*this.yyVel;
	}

	public void draw(){
		StdDraw.picture(xxPos,yyPos,"./images/"+imgFileName);
	}
}
