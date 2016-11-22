package id.sch.smktelkom_mlg.project.xirpl406152433.part1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Jamu jamu = (Jamu) getIntent().getSerializableExtra(MainActivity.JAMU);
        setTitle(jamu.getName());
        ImageView ivFoto = (ImageView) findViewById(R.id.imageFoto);
        ivFoto.setImageResource(jamu.thumbnail);
        TextView tvDeskripsi = (TextView) findViewById(R.id.jamu_deskripsi);
        tvDeskripsi.setText(jamu.deskripsi);
        TextView tvBahan = (TextView) findViewById(R.id.jamu_bahan);
        tvBahan.setText(jamu.bahan);
        TextView tvCara = (TextView) findViewById(R.id.jamu_cara);
        tvCara.setText(jamu.cara);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
