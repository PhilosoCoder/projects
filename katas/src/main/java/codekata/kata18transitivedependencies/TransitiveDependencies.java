package codekata.kata18transitivedependencies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransitiveDependencies {
    Map<String, List<String>> dependencyMap;

    public TransitiveDependencies() {
        this.dependencyMap = new HashMap<>();
    }

    public void addDirect(String key, List<String> values) {
        dependencyMap.put(key, values);
    }

    public List<String> dependenciesFor(String key) {
        List<String> result = new ArrayList<>();

        if (dependencyMap.containsKey(key)) {
            depthFirstSearch(key, result);
            Collections.sort(result);
        }

        return result;
    }

    private void depthFirstSearch(String item, List<String> result) {
        if (dependencyMap.containsKey(item)) {
            for (String dependency : dependencyMap.get(item)) {
                if (!result.contains(dependency)) {
                    result.add(dependency);
                    depthFirstSearch(dependency, result);
                }
            }
        }
    }

}
