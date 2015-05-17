import org.junit.Before;
import org.junit.Test;
import main.java.testClasses.IllegalAccessClass;
import main.java.testClasses.TestAbstractClass;
import main.java.testClasses.TestInterface;

import java.util.Date;
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
    public void test_generate_passTestInterfaceWithAMethodThatReturnsAString_shouldReceiveANNullObjectThatReturnsAnEmptyString(){
        TestInterface testInterface = nullObjectGenerator.generate(TestInterface.class);
        assertThat(testInterface.getString()).isEqualTo("");
    }

    @Test
    public void test_generate_passTestInterfaceWithAMethodThatReturnsAInt_shouldReceiveANNullObjectThatReturnsAnEmptyInt(){
        TestInterface testInterface = nullObjectGenerator.generate(TestInterface.class);
        assertThat(testInterface.getInt()).isEqualTo(0);
    }

    @Test
    public void test_generate_passTestInterfaceWithAMethodThatReturnsALong_shouldReceiveANNullObjectALong(){
        TestInterface testInterface = nullObjectGenerator.generate(TestInterface.class);
        assertThat(testInterface.getLong()).isEqualTo(0L);
    }

    @Test
    public void test_generate_passTestInterfaceWithAMethodThatReturnsADouble_shouldReceiveANNullObject(){
        TestInterface testInterface = nullObjectGenerator.generate(TestInterface.class);
        assertThat(testInterface.getDouble()).isEqualTo(0.0);
    }

    @Test
    public void test_generate_passTestInterfaceWithAMethodThatReturnsABoolean_shouldReceiveANNullObject(){
        TestInterface testInterface = nullObjectGenerator.generate(TestInterface.class);
        assertThat(testInterface.getBoolean()).isEqualTo(false);
    }

    @Test
    public void test_generate_passTestInterfaceWithAMethodThatReturnsARandomObject_shouldReceiveANNullObjectAndTheNulledMethodShouldReturnAnotherNullObject(){
        TestInterface testInterface = nullObjectGenerator.generate(TestInterface.class);
        Date randomObject = testInterface.getRandomObject();
        assertThat(randomObject).isNotNull();
        assertThat(randomObject.after(null)).isFalse();
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
        verify(mock).logNullOccurrence();
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