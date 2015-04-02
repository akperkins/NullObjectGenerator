import testClasses.TestInterface;

public class Main {
    public static void main(String[] args) {
	    TestInterface stub = new NullObjectGenerator().generate(TestInterface.class);
        System.out.println(stub.getMoney());
        System.out.print("application terminating!");
    }
}