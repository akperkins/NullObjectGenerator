package test;

import com.overnightapps.NullObjectGenerator;
import com.overnightapps.TestClass;
import com.overnightapps.TestInterface;
import junit.framework.TestCase;

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
        assertNotNull(testInterface);
        testInterface.getMoney();
    }

    public void test_generate_passTestInterface_shouldReceiveANNullObject(){
        TestInterface testInterface = nullObjectGenerator.generate(TestInterface.class);
        assertNotNull(testInterface);
        testInterface.doNotReturn();
    }

    public void test_generate_passTestClassWithAMethodThatReturnsAString_shouldReceiveANNullObject(){
        TestInterface testInterface = nullObjectGenerator.generate(TestClass.class);
        assertNotNull(testInterface);
        testInterface.getMoney();
    }

    public void test_generate_passTestClass_shouldReceiveANNullObject(){
        TestInterface testInterface = nullObjectGenerator.generate(TestClass.class);
        assertNotNull(testInterface);
        testInterface.doNotReturn();
    }

    public void test_generate_passTestClassWithHardFailSet_anExceptionShouldBeThrown(){ //todo replace these junit 3 tests with junit 4
        nullObjectGenerator = new NullObjectGenerator(true);
        TestInterface testInterface = nullObjectGenerator.generate(TestClass.class);
        assertNotNull(testInterface);
        boolean isExceptionThrown;

        try {
            testInterface.doNotReturn();
            isExceptionThrown = false;
        } catch (AssertionError e){
            isExceptionThrown = true;
        }
        assertTrue(isExceptionThrown);//todo replace with assertj
    }
}
