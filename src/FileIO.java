import java.io.IOException;
import com.google.gson.Gson;

import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIO {
    public void writePlumbingsToFile(String filename, PlumbingStorage storage) throws IOException {
        Gson gson = new Gson();
        try {
            Path path = Paths.get(filename);
            String plumbingStr = gson.toJson(storage);
            Files.write(path, plumbingStr.getBytes());
        } catch (FileNotFoundException ex) {
            throw new IOException("Error: file not found!");
        } catch (IOException e) {
            throw new IOException("Error: unable to write in file!");
        }
    }

    public PlumbingStorage readPlumbingsFromFile (String filename) throws IOException {
        Gson gson = new Gson();
        PlumbingStorage storage = null;
        try {
            Path path = Paths.get(filename);
            storage = gson.fromJson(Files.readString(path), PlumbingStorage.class);
        } catch (FileNotFoundException e) {
            throw new IOException("Error: file not found!");
        } catch (IOException e) {
            throw new IOException("Error: unable to read from file!");
        } catch (JsonSyntaxException e) {
            throw new IOException("Error: input file has broken syntax!");
        }
        return storage;
    }

}


