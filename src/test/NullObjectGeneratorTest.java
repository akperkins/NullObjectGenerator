package test;

import com.overnightapps.NullObjectGenerator;
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

    public void test_generate_passTestInterface_shouldReceiveANNullObject(){
        TestInterface testInterface = nullObjectGenerator.generate(TestInterface.class);
        assertNotNull(testInterface);
    }
}
