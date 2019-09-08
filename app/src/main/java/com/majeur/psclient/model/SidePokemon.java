package com.majeur.psclient.model;

import com.majeur.psclient.util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SidePokemon extends BasePokemon {

    public static SidePokemon fromJson(JSONObject jsonObject, int index) throws JSONException {
        String ident = jsonObject.getString("ident");
        String name = ident.substring(ident.indexOf(':') + 1);
        Condition condition = new Condition(jsonObject.getString("condition"));
        boolean active = jsonObject.getBoolean("active");
        Stats stats = new Stats(jsonObject.getJSONObject("stats"));
        List<String> moves = Utils.getList(jsonObject.getJSONArray("moves"));
        String baseAbility = jsonObject.getString("baseAbility");
        String item = jsonObject.getString("item");
        String pokeBall = jsonObject.getString("pokeball");
        String ability = jsonObject.getString("ability");

        String[] detailsArray = jsonObject.getString("details").toLowerCase().split(", ");
        String species = detailsArray[0];
        boolean shiny = false;
        String gender = "";
        int level = 100;
        for (int i = 1; i < detailsArray.length; i++) {
            switch (detailsArray[i].charAt(0)){
                case 's':
                    shiny = true;
                    break;
                case 'm':
                    gender = "♂";
                    break;
                case 'f':
                    gender = "♀";
                    break;
                case 'l':
                    level = Integer.parseInt(detailsArray[i].substring(1));
                    break;
            }
        }

        return new SidePokemon(species, index, name, gender, shiny, level, condition, active, stats,
                moves, baseAbility, item, pokeBall, ability);
    }

    public int index;
    public String name;
    public String gender;
    public boolean shiny;
    public int level;
    public Condition condition;
    public boolean active;
    public Stats stats;
    public List<String> moves;
    public String baseAbility;
    public String item;
    public String pokeBall;
    public String ability;

    private StatModifiers statsModifiers;

    public SidePokemon(String species, int index, String name, String gender, boolean shiny, int level,
                       Condition condition, boolean active, Stats stats, List<String> moves,
                       String baseAbility, String item, String pokeBall, String ability) {
        super(species);
        this.index = index;
        this.name = name;
        this.gender = gender;
        this.shiny = shiny;
        this.level = level;
        this.condition = condition;
        this.active = active;
        this.stats = stats;
        this.moves = moves;
        this.baseAbility = baseAbility;
        this.item = item;
        this.pokeBall = pokeBall;
        this.ability = ability;
        this.statsModifiers = new StatModifiers();
    }
}