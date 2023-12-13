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
        double mo = (this.getInfluenzaCases()* 50.000) / ((double) this.getPopulation()) ; //methodos twn triwn
        System.out.println(this.getPopulation());
        double factor = Math.pow(10, 2);
        return Math.round(mo*factor)/factor;
        //return Double.parseDouble(String.format("%.2f", mo));
    }

    public int compareTo(City c) {
        if (this.calculateDensity() == c.calculateDensity()) {
            if (this.getName().compareToIgnoreCase(c.getName()) == 0) {
                if (this.getID() < c.getID()) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (this.getName().compareToIgnoreCase(c.getName()) == -1) {
                return -1;
            } else {
                return 1;
            }

        } else if (this.calculateDensity() < c.calculateDensity()) {
            return -1;
        } else {
            return 1;
        }

    }
}
