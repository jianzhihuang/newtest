/*
java     AssertionExample 2.0   //0
java -ea AssertionExample 2.0   //0

*/


public class AssertionExample {
	
    private static double calc(double d) {
    	double ans;
    	ans = 1.0/d + 100;
	System.out.println("AA");

	System.out.println("BB");

	System.out.println("BB");
	System.out.println("CC");

    	//assert ans != Double.POSITIVE_INFINITY : "�p����~! " + ans;
    	return ans;
    }
	
    public static void main(String args[])	{
	    double data1 = Double.parseDouble(args[0]);
	    double data2 = calc(data1);
	    System.out.println("�p�⵲�G= " + data2);
	    System.out.println("here");
   }
}