package wulei.modelpublic;

public class Value<T> {
    private T value;

    public Value(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
