import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import testClasses.IllegalAccessClass;
import testClasses.TestAbstractClass;
import testClasses.TestInterface;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by andreperkins on 3/31/15.
 */
public class NullObjectGeneratorTest {
    private NullObjectGenerator nullObjectGenerator;

    @Before
    public void setUp() throws Exception {
        nullObjectGenerator = new NullObjectGenerator();
    }

    @Test
    public void test_generate_passTestInterfaceWithAMethodThatReturnsAString_shouldReceiveANNullObject(){
        TestInterface testInterface = nullObjectGenerator.generate(TestInterface.class);
        assertThat(testInterface.getString()).isEqualTo("");
    }

    @Test
    public void test_generate_passTestInterface_shouldReceiveANNullObject(){
        TestInterface testInterface = nullObjectGenerator.generate(TestInterface.class);
        testInterface.doNotReturn();
    }

    @Test
    public void test_generate_passTestClassWithAMethodThatReturnsAString_shouldReceiveANNullObject(){
        TestInterface testInterface = nullObjectGenerator.generate(IllegalAccessClass.class);
        assertThat(testInterface.getString()).isEqualTo("");
    }

    @Test
    public void test_generate_passTestClass_shouldReceiveANNullObject(){
        TestInterface testInterface = nullObjectGenerator.generate(IllegalAccessClass.class);
        testInterface.doNotReturn();
    }

    @Test(expected = AssertionError.class)
    public void test_generate_passTestClassWithHardFailSet_anExceptionShouldBeThrown(){
        nullObjectGenerator = new NullObjectGenerator(true, null);
        TestInterface testInterface = nullObjectGenerator.generate(IllegalAccessClass.class);
        testInterface.doNotReturn();
    }

    @Test
    public void test_generate_passInALogger_theNullOccurrenceShouldBeLogged(){
        NullObjectGenerator.Logger mock = mock(NullObjectGenerator.Logger.class);
        nullObjectGenerator = new NullObjectGenerator(false, mock);
        IllegalAccessClass illegalAccessClass = nullObjectGenerator.generate(IllegalAccessClass.class);
        illegalAccessClass.doNotReturn();
        verify(mock).logNullOccurence();
    }

    @Test
    public void test_generate_abstractClassWithOneParamConstructor_shouldBeNulled(){
        TestAbstractClass testAbstractClass = nullObjectGenerator.generate(TestAbstractClass.class);
        assertThat(testAbstractClass.getTransformedMessage()).isEqualTo("");
    }

    @Test
    public void test_generate_ListAbstractClassAndNullaMethodThatReturnsABoolean(){
        List list = nullObjectGenerator.generate(List.class);
        assertThat(list.add(any())).isFalse();
    }
}