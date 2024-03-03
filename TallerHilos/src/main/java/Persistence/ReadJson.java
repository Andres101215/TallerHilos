package Persistence;

import Logic.Player;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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

    public void creararchivoJson(ArrayList<Player> p) {
        try {
            FileWriter writer = new FileWriter("src//main//java//Persistence//Player.json");
            JsonWriter jsonWriter = new JsonWriter(writer);

            jsonWriter.setIndent("  ");

            jsonWriter.beginArray();

            for (Player producto : p) {
                Gson gson = new Gson();
                String json = gson.toJson(producto);
                jsonWriter.jsonValue(json);
            }
            jsonWriter.endArray();

            jsonWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Player> loadPlayer() {
        ArrayList<Player> Players1 = new ArrayList<>();

        try {
            JsonReader reader = new JsonReader(new FileReader("src//main//java//Persistence//Player.json"));
            Gson gson = new Gson();

            reader.beginArray();
            while (reader.hasNext()) {
                Player player = gson.fromJson(reader, Player.class);
                Players1.add(player);
            }
            reader.endArray();
            reader.close();
        } catch (IOException e) {
            // e.printStackTrace();
        }

        return Players1;
    }
}

