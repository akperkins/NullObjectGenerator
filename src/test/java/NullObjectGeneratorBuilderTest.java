import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by andreperkins on 3/31/15.
 */
public class NullObjectGeneratorBuilderTest extends TestCase {
    public void test_build_buildWithFailHardSetting() throws Exception {
        NullObjectGenerator nullObjectGenerator = new NullObjectGenerator.Builder().setFailHard().build();
        Assert.assertTrue(nullObjectGenerator.isSetToFailHard());
    }

    public void test_build_buildWithLogger() throws Exception {
        NullObjectGenerator nullObjectGenerator = new NullObjectGenerator.Builder()
                .setLogger(new NullObjectGenerator.Logger() {
                    @Override
                    public void logNullOccurence(String tag, String message) {

                    }
                }).build();
        assertNotNull(nullObjectGenerator.getLogger());
    }
}
