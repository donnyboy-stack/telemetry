/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package App.Profile.Writer;

import App.Profile.Loader.ProfileLoaderInterface;
import App.Profile.ProfileInterface;
import Data.Type.Collection.DataTypeCollectionInterface;
import Data.Type.DataTypeInterface;

import java.io.File;
import java.io.Writer;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Map;

public class ProfileWriter implements ProfileWriterInterface {
    public boolean writeProfile (ProfileInterface profile) {
        File file = profile.getFile();

        if (file != null) {
            try {
                FileWriter writer = new FileWriter(profile.getFile(), false);

                writer.write(ProfileLoaderInterface.FIELD_DATA_SOURCE + "," + profile.getDataSource().getName() + ProfileLoaderInterface.LINE_DELIMITER);

                DataTypeCollectionInterface types = profile.getDataSource().getTypes();

                for (Map.Entry<String, DataTypeInterface> entry : types.entrySet())
                    writeDataType(writer, entry.getKey(), entry.getValue());

                writer.close();
            } catch (IOException e) {
                return false;
            }

            return true;
        }

        return false;
    }

    protected void writeDataType (Writer writer, String id, DataTypeInterface type) throws IOException {
        writer.write(ProfileLoaderInterface.FIELD_DATA_TYPE + ","
            + id + ","
            + type.getDisplayName() + ","
            + type.getDisplayUnits() + ","
            + type.getColor().getRGB()
            + ProfileLoaderInterface.LINE_DELIMITER);
    }
}
