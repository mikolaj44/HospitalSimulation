package Person;

import Utils.ColorCodes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class LifeStats<T> {

    private T physical;
    private T internal;
    private T infection;

    public LifeStats(T physical, T internal, T infection) {
        this.physical = physical;
        this.internal = internal;
        this.infection = infection;
    }

    public LifeStats(List<Integer> list) {
    }

    public int getStatAmount() {

        Field[] fields = getClass().getDeclaredFields();

        return fields.length;
    }

    public T getStatByIndex(int index) {

        return switch (index) {
            case 0 -> physical;
            case 1 -> internal;
            case 2 -> infection;
            default -> throw new IndexOutOfBoundsException();
        };

    }

    public String toString() {
        return "Obrażenia fizyczne: " + ColorCodes.YELLOW + physical + ColorCodes.RESET + "\n" + "Obrażenia wewnętrzne: " + ColorCodes.YELLOW + internal + ColorCodes.RESET + "\n" + "Obrażenia od infekcji: " + ColorCodes.YELLOW + infection + ColorCodes.RESET;
    }

    public T getPhysical() {
        return physical;
    }

    public void setPhysical(T physical) {
        this.physical = physical;
    }

    public T getInternal() {
        return internal;
    }

    public void setInternal(T internal) {
        this.internal = internal;
    }

    public T getInfection() {
        return infection;
    }

    public void setInfection(T infection) {
        this.infection = infection;
    }
}
