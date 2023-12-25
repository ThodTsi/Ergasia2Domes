public class PQmed {
    protected double[] med;
    protected int size;
    protected double median;
    public static final int def_cap = 4;
    public static final int auto_grow = 4;

    public PQmed() {
        this.med = new double[def_cap + 1];
        this.size = -1;
        this.median = 0.0;
    }

    protected void resize() {
        double[] newMed = new double[med.length + auto_grow];
        System.arraycopy(med, 1, newMed, 1, size);

        med = newMed;
    }

    protected void exchange(int i, int j) {
        double temp = med[i];
        med[i] = med[j];
        med[j] = temp;
    }

    protected void insert(double d) {
        double lim = Math.ceil(0.75 * size);
        if (size >= lim) {
            resize();
        }
        size++;
        med[size] = d;
        sort_med();
    }

    protected void sort_med() {
        for (int i = 0; i < med.length - 1; i++) {
            if (med[i] > med[i + 1]) {
                exchange(i, i + 1);
            }
        }
        if (size % 5 == 0) {
            median = med[size / 2];
        }
    }

    protected double getMed() {
        return this.median;
    }

    protected int size() {
        return this.size;
    }

}
