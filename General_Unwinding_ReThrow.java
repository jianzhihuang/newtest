public class General_Unwinding_ReThrow { 

public static void main( String[] args ) {
    try {
         method();
    }catch ( Exception e ) { 
       System.out.println("3-Exception handling in main()");
    }
}


public static void method() throws Exception {
    try {
       System.out.println("1-in method()");
       throw new Exception();
    } catch( Exception e ) {
       System.out.println("2-Exception handling in method()");
    }
}            


/*
public static void method() throws Exception {
    try {
       System.out.println("1-in method()");
       throw new Exception();
    } catch( RuntimeException e ) {
       System.out.println("2-Exception handling in method()");
    }
}
*/  

/*
public static void method() throws Exception {
    try {
        System.out.println("1-in method()");
        throw new Exception();
    } catch( Exception e ) {
        System.out.println("2-Exception handling in method()");
        throw e;  
    }
}
*/

}
