/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 2, 2016
 */

package Data.Type;

import java.awt.Color;
import java.util.List;

public interface DataTypeInterface {
    final public static int MAX_DATA_POINTS = 50;
    
    public String getName();
    public void setDisplayName(String name);
    public String getDisplayName();

    public String getUnits();
    public void setDisplayUnits(String units);
    public String getDisplayUnits();

    public Color getColor();
    public void setColor(Color color);

    public void setEnabled(boolean enabled);

    public boolean isEnabled();

    public void putValue(double value);

    public List<Double> getData();

    public double getMinimumValue();

    public double getCurrentValue();

    public double getMaximumValue();
}
