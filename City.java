public class City implements CityInterface, Comparable<City> {
    protected int id, population, inf_cases;
    protected String name;

    public City(int id, String name, int population, int inf_cases) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.inf_cases = inf_cases;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getPopulation() {
        return this.population;
    }

    public int getInfluenzaCases() {
        return this.inf_cases;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setInfluenzaCases(int influenzaCases) {
        this.inf_cases = influenzaCases;
    }

    public double calculateDensity() {
        double mo = (double)this.getInfluenzaCases() * 50000 / (double)this.getPopulation(); // methodos twn triwn
        double factor = Math.pow(10, 2);
        return (double)Math.round((double)mo * (double)factor) / (double)factor;
    }

    public int compareTo(City c) {
        int dens_comp = Double.compare(this.calculateDensity(), c.calculateDensity());
        if (dens_comp != 0) {
            return dens_comp;
        }
        int name_comp = this.getName().compareToIgnoreCase(c.getName());
        if (name_comp != 0) {
            return name_comp;
        }
        return Integer.compare(this.getID(), c.getID());
    }

    // bohthhtikh thodwera tha sbhstei meta
    public String toString() {
        return this.id + "\t" + this.name + "\t" + this.population + "\t" + this.inf_cases;
    }
}
