package com.tolutronics.plugin.capacitor.tolutronicscapacitorsqlite.cdssUtils.ImportExportJson;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonStore {
    private String database;
    private Boolean encrypted;
    private ArrayList<JsonTable> tables;

    public JsonStore() {
        this.database = "";
        this.encrypted = false;
        this.tables = new ArrayList<>();
    }

    public Boolean isJsonStore(JSObject obj) {
        try {
            // Check if required fields exist
            if (!obj.has("database")) return false;
            if (!obj.has("encrypted")) return false;
            if (!obj.has("tables")) return false;

            // Parse the JSON
            this.database = obj.getString("database");
            this.encrypted = obj.has("encrypted") ? obj.getBool("encrypted") : false;

            // Parse tables array
            this.tables = new ArrayList<>();
            org.json.JSONArray tablesArray = obj.getJSONArray("tables");
            if (tablesArray != null) {
                for (int i = 0; i < tablesArray.length(); i++) {
                    JSONObject tableObj = tablesArray.getJSONObject(i);
                    JsonTable table = new JsonTable();
                    if (table.isJsonTable(tableObj)) {
                        this.tables.add(table);
                    } else {
                        return false;
                    }
                }
            }

            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public String getDatabase() {
        return this.database;
    }

    public Boolean getEncrypted() {
        return this.encrypted;
    }

    public ArrayList<JsonTable> getTables() {
        return this.tables;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setEncrypted(Boolean encrypted) {
        this.encrypted = encrypted;
    }

    public void setTables(ArrayList<JsonTable> tables) {
        this.tables = tables;
    }
}
