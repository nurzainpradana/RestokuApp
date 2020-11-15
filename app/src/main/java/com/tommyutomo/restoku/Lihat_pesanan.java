package com.tommyutomo.restoku;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lihat_pesanan extends AppCompatActivity {
    SharedPrefPrefences spm;
    DBHelper dbHelper;
    SQLiteDatabase db;
    private TextView d_nama,d_meja,d_tanggal,d_pesanan,d_harga;
    private Button back,ubah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_pesanan);
        d_nama = findViewById( R.id.d_nama );
        d_meja = findViewById( R.id.d_meja );
        d_tanggal = findViewById( R.id.d_tanggal );
        d_harga = findViewById( R.id.d_harga );
        d_pesanan = findViewById( R.id.d_pesanan );
        dbHelper = new DBHelper( this );
        spm = new SharedPrefPrefences( this );

        db = dbHelper.getReadableDatabase();
        final Cursor cursor = db.rawQuery( "SELECT * FROM db_pesanan WHERE no_meja='"+getIntent().getStringExtra( "no_meja" )+"'", null );
        cursor.moveToFirst();
        d_meja.setText("No meja     : "+ cursor.getString( 1 )+"" );
        d_nama.setText("Nama         :  "+ cursor.getString(3)+"" );
        d_tanggal.setText("Tanggal      : "+ cursor.getString( 4 )+"" );
        d_harga.setText( "Total harga: Rp."+cursor.getString(5)+"");
        d_pesanan.setText( cursor.getString(6)+"" );

        back = findViewById( R.id.back );
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( Lihat_pesanan.this,MainActivity.class ) );
            }
        } );
        ubah = findViewById( R.id.ubah );
        ubah.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( Lihat_pesanan.this, Menu.class )
                        .putExtra( "no_meja", cursor.getString( 1 ) )
                        .putExtra( "nama", cursor.getString( 3 ) )
                        .putExtra( "total_harga", cursor.getString( 5 ) )
                        .putExtra( "status","update" )
                );
            }
        } );
    }
}
