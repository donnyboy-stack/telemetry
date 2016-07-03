/**
* Sunseeker Telemety
*
* @author Alec Carpenter <alecgunnar@gmail.com>
* @date July 3, 2016
*/

package sunseeker.telemetry;

import java.awt.Color;

class DataType {
    protected Color[] colors = {
        Color.BLUE,
        Color.RED,
        Color.ORANGE,
        Color.MAGENTA,
        Color.YELLOW,
        Color.CYAN
    };

    protected String name;

    protected static int colorCount = 0;
    protected Color color;

    protected DataCollectionInterface data;

    protected boolean enabled  = false;
    protected boolean provided = false;

    public DataType (String name, DataCollectionInterface data) {
        this.name = name;
        this.data = data;

        /*
         * Determine which color we'll be using for this line
         */
        color = colors[colorCount % colors.length];
        colorCount++;
    }

    public String getName () {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public DataCollectionInterface getData () {
        return data;
    }

    public void setEnabled (boolean enabled) {
        /*
         * We cannot enable this type it is not provided
         */
        if (enabled && !provided) return;

        this.enabled = enabled;
    }

    public boolean isEnabled () {
        return enabled;
    }

    public void setProvided (boolean provided) {
        this.provided = provided;

        /*
         * If this value is not provided, we cannot show it
         */
        if (!provided) setEnabled(false);
    }

    public boolean isProvided () {
        return provided;
    }
}
