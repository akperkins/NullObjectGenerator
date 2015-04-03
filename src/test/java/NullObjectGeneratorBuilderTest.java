import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by andreperkins on 3/31/15.
 */
public class NullObjectGeneratorBuilderTest {
    @Test
    public void test_build_buildWithFailHardSetting() throws Exception {
        NullObjectGenerator nullObjectGenerator = new NullObjectGenerator.Builder().setFailHard().build();
        Assert.assertTrue(nullObjectGenerator.isSetToFailHard());
    }

    @Test
    public void test_build_buildWithLogger() throws Exception {
        NullObjectGenerator nullObjectGenerator = new NullObjectGenerator.Builder()
                .setLogger(new NullObjectGenerator.Logger() {
                    @Override
                    public void logNullOccurrence() {
                        //no-op
                    }
                }).build();
        assertThat(nullObjectGenerator.getLogger()).isNotNull();
    }
}
