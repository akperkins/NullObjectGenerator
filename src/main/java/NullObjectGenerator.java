import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;

/**
 * Created by andreperkins on 3/31/15.
 */
public class NullObjectGenerator {

    private final boolean isSetToFailHard;
    private final Logger logger;

    public NullObjectGenerator(boolean isSetToFailHard, Logger logger) {
        this.isSetToFailHard = isSetToFailHard;
        this.logger = logger;
    }

    public NullObjectGenerator() {
        this.isSetToFailHard = false;
        this.logger = null;
    }

    public <T extends Object> T generate(Class<T> testInterfaceClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(testInterfaceClass);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                if(isSetToFailHard){
                    throw new AssertionError("A null object was not expected here.");
                }
                return "Hello cglib!";
            }
        });
        T proxy = (T) enhancer.create();
        return proxy;
    }

    public boolean isSetToFailHard() {
        return isSetToFailHard;
    }

    public Logger getLogger() {
        return logger;
    }

    public static class Builder {
        private boolean isSetToFailHard;
        private Logger logger;

        public Builder setFailHard() {
            isSetToFailHard = true;
            return this;
        }

        public NullObjectGenerator build() {
            return new NullObjectGenerator(isSetToFailHard, logger);
        }

        public Builder setLogger(Logger logger) {
            this.logger = logger;
            return this;
        }
    }

    public static interface Logger {
        public void logNullOccurence(String tag, String message);
    }
}