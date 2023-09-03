package com.example.e_gouvernance.data.sqlLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.e_gouvernance.entity.sqlLite.Commande;

import java.util.ArrayList;
import java.util.List;

public class CommandeDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "commande_database";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "commande_table";
    public CommandeDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                "_id TEXT PRIMARY KEY, " +
                "produit TEXT, " +
                "user TEXT, " +
                "datecommande TEXT, " +
                "etat TEXT)";
        sqLiteDatabase.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public List<Commande> getAllCommandes() {
        List<Commande> commandes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Commande commande = new Commande();
                    commande.set_id(cursor.getString(cursor.getColumnIndex("_id")));
                    commande.setProduit(cursor.getString(cursor.getColumnIndex("produit")));
                    commande.setUser(cursor.getString(cursor.getColumnIndex("user")));
                    commande.setDatecommande(cursor.getString(cursor.getColumnIndex("datecommande")));
                    commande.setEtat(cursor.getString(cursor.getColumnIndex("etat")));
                    commandes.add(commande);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        db.close();
        return commandes;
    }
}
