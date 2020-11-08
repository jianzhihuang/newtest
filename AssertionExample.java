/*
java     AssertionExample 2.0   //0
java -ea AssertionExample 2.0   //0

*/


public class AssertionExample {
	
    private static double calc(double d) {
    	double ans;
    	ans = 1.0/d + 100;
    	//assert ans != Double.POSITIVE_INFINITY : "計算錯誤! " + ans;
    	return ans;
    }
	
    public static void main(String args[])	{
	    double data1 = Double.parseDouble(args[0]);
	    double data2 = calc(data1);
	    System.out.println("計算結果= " + data2);
	    System.out.println("here");
   }
}