import java.util.Arrays;

class PlumbingStorage {
    private Plumbing[] models;

    PlumbingStorage () {
        models = new Plumbing[0];
    }

    void getModelsFromDatabase() {
        models = new Plumbing[10];
        models[0] = new Plumbing(0, "toilet", "Jysk", "WALKER CLOSE-COUPLED TOILET DUAL-FLUSH", 0, 109.99, "527HG");
        models[1] = new Plumbing(1, "mixer", "Hygienic Bathrooms", "Hygienic Bathrooms Kitchen Mixer", 30, 14.24, "1121");
        models[2] = new Plumbing(2, "mixer", "Hygienic Bathrooms", "Hygienic Bathrooms Two Hole Kitchen Sink Mixer", 80, 42.22, "1018");
        models[3] = new Plumbing(3, "thermostat", "Nest", "STAND FOR NEST 3RD GENERATION DIGITAL LEARNING THERMOSTAT", 60, 35.00, "AT3000GB");
        models[4] = new Plumbing(4, "thermostat", "Nest", "NEST 3RD GENERATION BLACK DIGITAL LEARNING WI-FI THERMOSTAT", 2, 170.00, "T3029EX");
        models[5] = new Plumbing(5, "thermostat", "Nest", "NEST 3RD GENERATION STAINLESS STEEL DIGITAL LEARNING WI-FI THERMOSTAT", 5, 180.00, "T3028GB");
        models[6] = new Plumbing(6, "pipe", "FloPlast", "FLOPLAST OVERFLOW PIPE WHITE 21.5MM X 3M", 30, 2.59, "68593");
        models[7] = new Plumbing(7, "pipe", "FloPlast", "FLOPLAST SOLVENT WELD PIPES WHITE 50MM X 3M", 100, 10.68, "88188");
        models[8] = new Plumbing(8, "toilet", "AuthenticPlumbing", "TOILET-TO-GO CLOSE-COUPLED TOILET", 0, 300.99, "0A312");
        models[9] = new Plumbing(9, "toilet", "CASSELLIE", "MONTEGO COMFORT HEIGHT CLOSE-COUPLED TOILET DUAL-FLUSH", 0, 129.99, "892GV");
    }

    void add(Plumbing plumbing) {
        models = Arrays.copyOf(models, models.length + 1);
        models[models.length - 1] = plumbing;
    }

    Plumbing[] getModels() {
        return models;
    }

    int len() {return models.length;}

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("-".repeat(120));
        ret.append('\n');
        ret.append("Name").append(" ".repeat(48)).append(" Price ").append(" ".repeat(13)).append(" Model ")
                .append(" ".repeat(10)).append(" Kind ").append(" ".repeat(10)).append(" Manufacturer |");
        ret.append('\n');
        ret.append("-".repeat(120));
        ret.append('\n');

        for (Plumbing plumbing: models)
        {
            ret.append(plumbing.toString());
        }
        return ret.toString();
    }
}
