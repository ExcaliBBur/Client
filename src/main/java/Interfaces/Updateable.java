package Interfaces;

import java.util.Collection;;

public interface Updateable<T> {
    void updateContents(Collection<T> collection);
}
