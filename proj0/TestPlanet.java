public class TestPlanet {
    public static void main(String[] args) {
        Planet p1=new Planet(10,10,10,10,10,"jupiter.gif");
        Planet p2=new Planet(5,5,5,5,5,"jupiter.gif");
        System.out.println(TestPlanet(p1,p2));
    }

    private static double TestPlanet(Planet p1,Planet p2){
        return p1.calcForceExertedBy(p2);
    }
}