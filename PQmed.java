import java.util.Arrays;

public class PQmed {
    protected double[] med;
    protected int size;
    public static final int def_cap = 4;
    public static final int auto_grow = 4;

    public PQmed() {
        this.med = new double[def_cap + 1];
        this.size = 0;
    }

    protected void resize() {
        double[] newMed = new double[med.length + auto_grow];
        System.arraycopy(med, 0, newMed, 0, size);
        med = newMed;
    }

    protected void exchange(int i, int j) {
        double temp = med[i];
        med[i] = med[j];
        med[j] = temp;
    }

    protected void insert(double d) {
        if (size + 1 >= med.length) {
            resize();
        }
        med[size] = d;
        size++;
    }

    protected int size() {
        return this.size;
    }

    protected double getMed() {
        return med[med.length / 2];

    }

    /*
     * protected void swim(int i) {
     * if (i == 0)
     * return;
     * 
     * while ((i > 1) & (i / 2 < i)) {
     * if (med[i] < med[i / 2]) {
     * double temp = med[i / 2];
     * med[i / 2] = med[i];
     * med[i] = temp;
     * }
     * i /= 2;
     * }
     * }
     */
}
