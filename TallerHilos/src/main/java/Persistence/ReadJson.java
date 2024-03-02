package Persistence;

import Logic.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ReadJson {

    private ArrayList<Player> players;


    public ReadJson() {
        players = loadPlayer();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    private String readFile() {
        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/Player.json"), Charset.defaultCharset()))) {
            String line = null;
            StringBuilder output = new StringBuilder();

            while ((line = input.readLine()) != null) {
                output.append(line);
            }
            return output.toString();
        } catch (
                IOException e) {
            throw new RuntimeException("Error al leer el archivo", e);
        }
    }

    public ArrayList<Player> loadPlayer() {
        String content = readFile();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Player>>() {
        }.getType();
        ArrayList<Player> result = gson.fromJson(content, type);
        return result;
    }

}

