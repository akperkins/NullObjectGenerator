package test;

import com.overnightapps.NullObjectGenerator;
import junit.framework.TestCase;

/**
 * Created by andreperkins on 3/31/15.
 */
public class NullObjectGeneratorBuilderTest extends TestCase {
    public void test_build_buildWithFailHardSetting() throws Exception {
        NullObjectGenerator nullObjectGenerator = new NullObjectGenerator.Builder().setFailHard().build();
        assertTrue(nullObjectGenerator.isSetToFailHard());
    }
}
