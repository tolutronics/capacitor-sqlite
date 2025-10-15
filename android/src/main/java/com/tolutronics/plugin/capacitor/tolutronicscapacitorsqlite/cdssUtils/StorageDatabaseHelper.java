package com.tolutronics.plugin.capacitor.tolutronicscapacitorsqlite.cdssUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.getcapacitor.JSObject;
import java.util.ArrayList;
import java.util.List;

public class StorageDatabaseHelper extends SQLiteOpenHelper {
    public boolean isOpen;
    private SQLiteDatabase db;
    private String tableName;
    private String storeName;
    private Boolean encrypted;
    private String mode;
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_VALUE = "value";

    public StorageDatabaseHelper(
        Context context,
        String databaseName,
        String tableName,
        Boolean encrypted,
        String mode,
        int version
    ) {
        super(context, databaseName, null, version);
        this.tableName = tableName;
        this.storeName = databaseName;
        this.encrypted = encrypted;
        this.mode = mode;
        this.isOpen = false;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table if it doesn't exist
        createTable(db, this.tableName);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade
        // For now, we'll keep the existing data
    }

    public void open() throws Exception {
        try {
            this.db = this.getWritableDatabase();
            this.isOpen = true;
            // Ensure the initial table exists
            createTable(this.db, this.tableName);
        } catch (Exception e) {
            this.isOpen = false;
            throw new Exception("Failed to open database: " + e.getMessage());
        }
    }

    @Override
    public void close() {
        if (this.db != null && this.db.isOpen()) {
            this.db.close();
            this.isOpen = false;
        }
    }

    public String getStoreName() {
        return this.storeName;
    }

    private void createTable(SQLiteDatabase database, String table) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + table + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT UNIQUE NOT NULL, " +
            COLUMN_VALUE + " TEXT NOT NULL)";
        database.execSQL(createTableSQL);

        // Create index on name column
        String createIndexSQL = "CREATE INDEX IF NOT EXISTS idx_" + table +
            "_name ON " + table + "(" + COLUMN_NAME + ")";
        database.execSQL(createIndexSQL);
    }

    public void setTable(String table, boolean create) throws Exception {
        if (!this.isOpen) {
            throw new Exception("Database is not open");
        }

        if (create) {
            createTable(this.db, table);
        }
        this.tableName = table;
    }

    public boolean isTable(String table) throws Exception {
        if (!this.isOpen) {
            throw new Exception("Database is not open");
        }

        Cursor cursor = null;
        try {
            cursor = this.db.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name=?",
                new String[]{table}
            );
            return cursor.getCount() > 0;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public List<String> tables() throws Exception {
        if (!this.isOpen) {
            throw new Exception("Database is not open");
        }

        List<String> tablesList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = this.db.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name NOT LIKE 'sqlite_%' AND name NOT LIKE 'android_%'",
                null
            );

            while (cursor.moveToNext()) {
                tablesList.add(cursor.getString(0));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return tablesList;
    }

    public void deleteTable(String table) throws Exception {
        if (!this.isOpen) {
            throw new Exception("Database is not open");
        }

        try {
            this.db.execSQL("DROP TABLE IF EXISTS " + table);
        } catch (SQLException e) {
            throw new Exception("Failed to delete table: " + e.getMessage());
        }
    }

    public void set(Data data) throws Exception {
        if (!this.isOpen) {
            throw new Exception("Database is not open");
        }

        if (data.name == null) {
            throw new Exception("Data name cannot be null");
        }

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, data.name);
        values.put(COLUMN_VALUE, data.value);

        // Check if key exists
        if (iskey(data.name)) {
            // Update existing record
            int updated = this.db.update(
                this.tableName,
                values,
                COLUMN_NAME + "=?",
                new String[]{data.name}
            );
            if (updated == 0) {
                throw new Exception("Failed to update data");
            }
        } else {
            // Insert new record
            long result = this.db.insert(this.tableName, null, values);
            if (result == -1) {
                throw new Exception("Failed to insert data");
            }
        }
    }

    public Data get(String name) throws Exception {
        if (!this.isOpen) {
            throw new Exception("Database is not open");
        }

        Cursor cursor = null;
        Data data = new Data();

        try {
            cursor = this.db.query(
                this.tableName,
                new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_VALUE},
                COLUMN_NAME + "=?",
                new String[]{name},
                null, null, null
            );

            if (cursor.moveToFirst()) {
                data.id = cursor.getInt(0);
                data.name = cursor.getString(1);
                data.value = cursor.getString(2);
            } else {
                data.id = null;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return data;
    }

    public void remove(String name) throws Exception {
        if (!this.isOpen) {
            throw new Exception("Database is not open");
        }

        int deleted = this.db.delete(
            this.tableName,
            COLUMN_NAME + "=?",
            new String[]{name}
        );
    }

    public void clear() throws Exception {
        if (!this.isOpen) {
            throw new Exception("Database is not open");
        }

        this.db.delete(this.tableName, null, null);
        // Reset the auto-increment counter
        this.db.execSQL("DELETE FROM sqlite_sequence WHERE name=?", new String[]{this.tableName});
    }

    public boolean iskey(String name) throws Exception {
        if (!this.isOpen) {
            throw new Exception("Database is not open");
        }

        Cursor cursor = null;
        try {
            cursor = this.db.query(
                this.tableName,
                new String[]{COLUMN_ID},
                COLUMN_NAME + "=?",
                new String[]{name},
                null, null, null
            );
            return cursor.getCount() > 0;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public List<String> keys() throws Exception {
        if (!this.isOpen) {
            throw new Exception("Database is not open");
        }

        List<String> keysList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = this.db.query(
                this.tableName,
                new String[]{COLUMN_NAME},
                null, null, null, null,
                COLUMN_NAME + " ASC"
            );

            while (cursor.moveToNext()) {
                keysList.add(cursor.getString(0));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return keysList;
    }

    public List<String> values() throws Exception {
        if (!this.isOpen) {
            throw new Exception("Database is not open");
        }

        List<String> valuesList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = this.db.query(
                this.tableName,
                new String[]{COLUMN_VALUE},
                null, null, null, null,
                COLUMN_NAME + " ASC"
            );

            while (cursor.moveToNext()) {
                valuesList.add(cursor.getString(0));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return valuesList;
    }

    public List<String> filtervalues(String filter) throws Exception {
        if (!this.isOpen) {
            throw new Exception("Database is not open");
        }

        List<String> valuesList = new ArrayList<>();
        Cursor cursor = null;

        // Prepare the filter pattern
        String pattern = filter;
        if (!pattern.startsWith("%") && !pattern.endsWith("%")) {
            pattern = "%" + pattern + "%";
        }

        try {
            cursor = this.db.query(
                this.tableName,
                new String[]{COLUMN_VALUE},
                COLUMN_NAME + " LIKE ?",
                new String[]{pattern},
                null, null, null
            );

            while (cursor.moveToNext()) {
                valuesList.add(cursor.getString(0));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return valuesList;
    }

    public List<Data> keysvalues() throws Exception {
        if (!this.isOpen) {
            throw new Exception("Database is not open");
        }

        List<Data> dataList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = this.db.query(
                this.tableName,
                new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_VALUE},
                null, null, null, null,
                COLUMN_NAME + " ASC"
            );

            while (cursor.moveToNext()) {
                Data data = new Data();
                data.id = cursor.getInt(0);
                data.name = cursor.getString(1);
                data.value = cursor.getString(2);
                dataList.add(data);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return dataList;
    }

    public int importFromJson(ArrayList<JSObject> values) throws Exception {
        if (!this.isOpen) {
            throw new Exception("Database is not open");
        }

        int changes = 0;

        for (JSObject value : values) {
            String key = value.getString("key");
            String val = value.getString("value");

            Data data = new Data();
            data.name = key;
            data.value = val;

            set(data);
            changes++;
        }

        return changes;
    }

    public JSObject exportToJson() throws Exception {
        if (!this.isOpen) {
            throw new Exception("Database is not open");
        }

        JSObject result = new JSObject();

        // Remove "SQLite.db" suffix from database name
        String dbName = this.storeName;
        if (dbName.endsWith("SQLite.db")) {
            dbName = dbName.substring(0, dbName.length() - 9);
        }

        result.put("database", dbName);
        result.put("encrypted", this.encrypted);

        List<JSObject> tablesArray = new ArrayList<>();
        List<String> tablesList = tables();

        String currentTable = this.tableName;

        for (String table : tablesList) {
            this.tableName = table;
            JSObject tableObj = new JSObject();
            tableObj.put("name", table);

            List<Data> dataList = keysvalues();
            List<JSObject> valuesArray = new ArrayList<>();

            for (Data data : dataList) {
                JSObject valueObj = new JSObject();
                valueObj.put("key", data.name);
                valueObj.put("value", data.value);
                valuesArray.add(valueObj);
            }

            tableObj.put("values", valuesArray);
            tablesArray.add(tableObj);
        }

        this.tableName = currentTable;
        result.put("tables", tablesArray);

        return result;
    }
}
