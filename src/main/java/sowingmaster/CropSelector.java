package sowingmaster;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

import java.util.Map;
import java.util.HashMap;

public class CropSelector {
    CropSelector() {
        String filename = "src/main/resources/sowingmaster/crop_selector.fcl";
        this.fis = FIS.load(filename,true);
    }

    public Map<String, Double> evaluate(float type, float acidity, float seedTime, float cropTime) {
        Map<String, Double> map = new HashMap<>();

        fis.setVariable("type", type);
        fis.setVariable("acidity", acidity);
        fis.setVariable("seed_time", seedTime);
        fis.setVariable("crop_time", cropTime);
        fis.evaluate();
        map.put("af", fis.getVariable("grow_time").getValue());
        map.put("d", fis.getVariable("density").getValue());
        map.put("pt", fis.getVariable("plant_time").getValue());
        map.put("gt", fis.getVariable("grow_time").getValue());
        return map;
    }

    public void showCharts() {
        FunctionBlock functionBlock = fis.getFunctionBlock("crop_selector");
        Variable acidityFactor = functionBlock.getVariable("acidity_factor");
        Variable density = functionBlock.getVariable("density");
        Variable plantTime = functionBlock.getVariable("plant_time");
        Variable growTime = functionBlock.getVariable("grow_time");

        JFuzzyChart.get().chart(functionBlock);
        JFuzzyChart.get().chart(acidityFactor, acidityFactor.getDefuzzifier(), true);
        JFuzzyChart.get().chart(density, density.getDefuzzifier(), true);
        JFuzzyChart.get().chart(plantTime, plantTime.getDefuzzifier(), true);
        JFuzzyChart.get().chart(growTime, growTime.getDefuzzifier(), true);
    }

    private final FIS fis;
}
