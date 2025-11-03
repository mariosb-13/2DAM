package tarea_4;

import java.util.List;

public class PoblacionGson {
    public boolean error;
    public String msg;
    public Data data;

    public static class Data {
        public String city;
        public String country;
        public List<PopulationCount> populationCounts;
    }

    public static class PopulationCount {
        public String year;
        public int value;
        public String sex;
        public String reliability;
    }
}
