package isp;

import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public interface Menu {
    String addElement(String name, Optional<String> parentNumber, String inputKey, Action action);
}
