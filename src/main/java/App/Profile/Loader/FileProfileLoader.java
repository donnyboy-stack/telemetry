/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 19, 2016
 */

package App.Profile.Loader;

import App.Profile.ProfileInterface;
import Data.Source.Collection.DataSourceCollectionInterface;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileProfileLoader extends AbstractProfileLoader {
    public FileProfileLoader (DataSourceCollectionInterface dataSources) {
        super(dataSources);
    }

    public ProfileInterface loadProfile (String fileName) {
        return loadProfile(new File(fileName));
    }

    public ProfileInterface loadProfile (File file) {
        reset();

        try {
            /*
             * Initialize the reader
             */
            FileReader fileReader     = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(fileReader);
            String line;

            /*
             * Read in and process each line
             */
            while ((line = buffReader.readLine()) != null)
                if (!processLine(line))
                    return null;
        } catch (FileNotFoundException e) {
            System.out.println("Could not open profile from file: " + file.getName());
            return null;
        } catch (IOException e) {
            System.out.println("Could not read profile from file: " + file.getName());
            return null;
        }

        /*
         * Get the completed profile
         */
        return buildProfile();
    }
}
