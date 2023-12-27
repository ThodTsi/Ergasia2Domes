public class PQmed {
    protected double[] med;
    protected int size;
    public static final int def_cap = 4;
    public static final int auto_grow = 4;

    public PQmed() {
        this.med = new double[def_cap + 1];
        this.size = -1;
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
            for (int j = 0; j < med.length - i - 1; j++) {
                if (med[j] > med[j + 1]) {
                    exchange(j, j + 1);
                }
            }
        }
    }

    protected int size() {
        return this.size;
    }

}
