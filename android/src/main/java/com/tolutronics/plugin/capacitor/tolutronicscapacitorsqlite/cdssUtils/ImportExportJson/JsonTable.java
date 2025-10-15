package com.tolutronics.plugin.capacitor.tolutronicscapacitorsqlite.cdssUtils.ImportExportJson;

import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonTable {
    private String name;
    private ArrayList<JSObject> values;

    public JsonTable() {
        this.name = "";
        this.values = new ArrayList<>();
    }

    public Boolean isJsonTable(JSONObject obj) {
        try {
            // Check if required fields exist
            if (!obj.has("name")) return false;

            // Parse the JSON
            this.name = obj.getString("name");

            // Parse values array (optional)
            this.values = new ArrayList<>();
            if (obj.has("values")) {
                JSArray valuesArray = JSArray.from(obj.getJSONArray("values"));
                if (valuesArray != null) {
                    for (int i = 0; i < valuesArray.length(); i++) {
                        JSONObject valueObj = valuesArray.getJSONObject(i);
                        // Check if value has key and value fields
                        if (valueObj.has("key") && valueObj.has("value")) {
                            JSObject jsValue = new JSObject();
                            jsValue.put("key", valueObj.getString("key"));
                            jsValue.put("value", valueObj.getString("value"));
                            this.values.add(jsValue);
                        } else {
                            return false;
                        }
                    }
                }
            }

            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<JSObject> getValues() {
        return this.values;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValues(ArrayList<JSObject> values) {
        this.values = values;
    }
}
