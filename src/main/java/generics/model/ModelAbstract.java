package generics.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by panov on 23.11.15.
 */
public abstract class ModelAbstract {

    protected BufferedReader reader;
    protected String path;

    public  ModelAbstract setPath(String path){
        this.path = path;
        return this;
    }

    public  ModelAbstract initReader() throws IOException {
        reader = Files.newBufferedReader(
                Paths.get( path ), StandardCharsets.UTF_8
        );

        return this;
    }

    // Развернуть "таблицу" пользователей из файла
    public abstract boolean deployTable() throws Exception;

}
