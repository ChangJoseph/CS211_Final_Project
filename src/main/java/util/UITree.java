package util;

import java.util.ArrayList;
import java.util.List;

public class UITree<C> {
    private C parent;
    private List<C> children;
    public UITree() {
        this(null);
    }
    public UITree(C parent) {
        children = new ArrayList<>(3);
        this.parent = parent;
    }
    public List<C> getChildren() {
        return children;
    }
    public C getParent() {
        return parent;
    }

}