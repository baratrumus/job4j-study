package isp;

import java.util.Optional;

public interface Menu {
    public String addElement(String name, Optional<String> parentNumber, String inputKey, Action action);
}
