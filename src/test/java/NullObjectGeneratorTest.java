import junit.framework.Assert;
import junit.framework.TestCase;
import testClasses.IllegalAccessClass;
import testClasses.TestInterface;

/**
 * Created by andreperkins on 3/31/15.
 */
public class NullObjectGeneratorTest extends TestCase {
    private NullObjectGenerator nullObjectGenerator;

    public void setUp() throws Exception {
        super.setUp();
        nullObjectGenerator = new NullObjectGenerator();
    }

    public void test_generate_passTestInterfaceWithAMethodThatReturnsAString_shouldReceiveANNullObject(){
        TestInterface testInterface = nullObjectGenerator.generate(TestInterface.class);
        Assert.assertNotNull(testInterface);
        testInterface.getMoney();
    }

    public void test_generate_passTestInterface_shouldReceiveANNullObject(){
        TestInterface testInterface = nullObjectGenerator.generate(TestInterface.class);
        Assert.assertNotNull(testInterface);
        testInterface.doNotReturn();
    }

    public void test_generate_passTestClassWithAMethodThatReturnsAString_shouldReceiveANNullObject(){
        TestInterface testInterface = nullObjectGenerator.generate(IllegalAccessClass.class);
        Assert.assertNotNull(testInterface);
        testInterface.getMoney();
    }

    public void test_generate_passTestClass_shouldReceiveANNullObject(){
        TestInterface testInterface = nullObjectGenerator.generate(IllegalAccessClass.class);
        Assert.assertNotNull(testInterface);
        testInterface.doNotReturn();
    }

    public void test_generate_passTestClassWithHardFailSet_anExceptionShouldBeThrown(){ //todo replace these junit 3 tests with junit 4
        nullObjectGenerator = new NullObjectGenerator(true, null);
        TestInterface testInterface = nullObjectGenerator.generate(IllegalAccessClass.class);
        Assert.assertNotNull(testInterface);
        boolean isExceptionThrown;

        try {
            testInterface.doNotReturn();
            isExceptionThrown = false;
        } catch (AssertionError e){
            isExceptionThrown = true;
        }
        TestCase.assertTrue(isExceptionThrown);//todo replace with assertj
    }
}
