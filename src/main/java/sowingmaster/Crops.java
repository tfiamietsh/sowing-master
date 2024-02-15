package sowingmaster;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class Crops {
    public static Pair<String, Map<String, Double>> getNearest(Map<String, Double> params) {
        String result = "";
        Map<String, Double> map = new HashMap<>();
        for (Map.Entry<String, Map<String, Double>> crop : crops.entrySet()) {
            double se = 0.;
            for (Map.Entry<String, Double> param : crop.getValue().entrySet())
                se += Math.pow(params.get(param.getKey()) - param.getValue(), 2);
            map.put(crop.getKey(), se);
        }
        Map.Entry<String, Double> min = null;
        for (Map.Entry<String, Double> entry : map.entrySet())
            if (min == null || min.getValue() > entry.getValue()) {
                result = entry.getKey();
                min = entry;
            }
        return new Pair<>(result, crops.get(result));
    }

    private static final Map<String, Map<String, Double>> crops = new HashMap<>() {{
        put("Strawberry", new HashMap<>() {{
            put("af", 1.0);
            put("d", 0.2);
            put("pt", 3.0);
            put("gt", 6.0);
        }});
        put("Carrot", new HashMap<>() {{
            put("af", -0.02);
            put("d", 0.4);
            put("pt", 1.0);
            put("gt", 3.0);
        }});
        put("Apple tree", new HashMap<>() {{
            put("af", 0.1);
            put("d", 0.5);
            put("pt", 1.0);
            put("gt", 12.0);
        }});
        put("Gumbo", new HashMap<>() {{
            put("af", 0.8);
            put("d", 0.7);
            put("pt", 1.0);
            put("gt", 3.0);
        }});
        put("Cucumbers", new HashMap<>() {{
            put("af", 0.0);
            put("d", 0.1);
            put("pt", 4.0);
            put("gt", 8.0);
        }});
        put("Spinach", new HashMap<>() {{
            put("af", 0.8);
            put("d", 0.1);
            put("pt", 3.0);
            put("gt", 10.0);
        }});
        put("Corn", new HashMap<>() {{
            put("af", 0.9);
            put("d", 0.1);
            put("pt", 2.1);
            put("gt", 2.0);
        }});
    }};
}
