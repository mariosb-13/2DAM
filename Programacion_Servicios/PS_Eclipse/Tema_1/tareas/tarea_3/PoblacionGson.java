package tarea_3;

import java.util.List;

public class PoblacionGson {
    boolean error;
    String msg;
    Data data;

    static class Data {
        String city;
        String country;
        List<PopulationCount> populationCounts;
    }

    static class PopulationCount {
        int year;
        int value;
        String sex;
        String reliability;
    }
}