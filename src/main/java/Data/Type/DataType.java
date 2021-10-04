/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 2, 2016
 */

package Data.Type;

import java.util.ArrayList;
import java.util.List;

import java.awt.Color;

public class DataType implements DataTypeInterface {
    /*
     * Various attributes
     */
    protected String name;
    protected String displayName;

    protected String units;
    protected String displayUnits;

    protected Color color;

    /*
     * Should this type be shown
     */
    protected boolean enabled;

    /*
     * The data associated with this type
     */
    protected List<Double> data;

    /*
     * Over life of object
     */
    protected double min;
    protected double cur;
    protected double max;

    public DataType (String name, String units) {
        /*
         * Set the given attributes 
         */
        this.name  = name;
        this.units = units;

        /*
         * Use black as the default color
         */
        color = Color.BLACK;

        /*
         * Create a synconized data list
         */
        data = new ArrayList<Double>();

        /*
         * This value will root the data at the origin of the graph
         */
        data.add(0.0);

        /*
         * Initialize other values
         */
        enabled = true;
        min     = 0;
        cur     = 0;
        max     = 0;
    }

    public String getName () {
        return name;
    }

    public void setDisplayName (String name) {
        displayName = name;
    }

    public String getDisplayName () {
        return displayName != null ? displayName : name;
    }

    public String getUnits () {
        return units;
    }

    public void setDisplayUnits (String units) {
        displayUnits = units;
    }

    public String getDisplayUnits () {
        return displayUnits != null ? displayUnits : units;
    }

    public Color getColor() {
        return color;
    }

    public void setColor (Color color) {
        this.color = color;
    }

    public void setEnabled (boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled () {
        return enabled;
    }

    public void putValue (double value) {
        if (data.size() >= MAX_DATA_POINTS) // We only store a certain amount of data (however much fits on graph)
            data.remove(0);
        // Store current value for getCurrentValue() method.
        cur = value;

        if (data.size() == 1) {  // If we have few data points, set min and max to new value.
            min = value;
            max = value;
        } else {
            if (value < min)
                min = value;

            if (value > max)
                max = value;
        }

        data.add(value);
    }

    public List<Double> getData () {
        return data;
    }

    public double getMinimumValue () {
        return min;
    }

    public double getCurrentValue () {
        return cur;
    }

    public double getMaximumValue () {
        return max;
    }
}
