package Panel.Error;

import Data.Type.Collection.DataTypeCollectionInterface;
import Panel.AbstractPanel;

public abstract class AbstractErrorPanel extends AbstractPanel {
    protected DataTypeCollectionInterface types;

    public void setTypes (DataTypeCollectionInterface types) {
        this.types = types;
    }

    public abstract void refresh();
}
