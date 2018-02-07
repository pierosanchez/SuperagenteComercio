package com.example.administrador.superagentecomercio.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;

import com.example.administrador.superagentecomercio.utils.Constante;

/**
 * Created by Administrador on 06/02/2018.
 */

public class SuperAgenteComercioBD extends SQLiteOpenHelper {
    private static int VERSION = 1;
    private static String NAME = Constante.BDSUPERAGENTECOMERCIO;
    private static SQLiteDatabase.CursorFactory FACTORY = null;
    private static String TABLE = Constante.TB_NAME;

    String sql = "CREATE TABLE " + TABLE + " (movil TEXT)";

    public SuperAgenteComercioBD(Context context) {
        super(context, NAME, FACTORY, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
