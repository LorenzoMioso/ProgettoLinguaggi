package value;

public class FunValue extends Value {

    private FunValue() { }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FunValue;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
