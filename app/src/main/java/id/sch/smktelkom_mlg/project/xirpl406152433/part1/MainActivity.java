package id.sch.smktelkom_mlg.project.xirpl406152433.part1;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JamuAdapter.IJamuAdapter {

    public static final String JAMU = "jamu";
    RecyclerView recyclerView;
    JamuAdapter adapter;
    List<Jamu> jamuList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        jamuList = new ArrayList<>();
        adapter = new JamuAdapter(this, jamuList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareJamu();

        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * Adding few albums for testing
     */
    private void prepareJamu() {
        int[] covers = new int[]{
                R.drawable.jamu1,
                R.drawable.jamu2,
                R.drawable.jamu3,
                R.drawable.jamu4,
                R.drawable.jamu5,
                R.drawable.jamu6,
                R.drawable.jamu7,
                R.drawable.jamu8,
                R.drawable.jamu9,
                R.drawable.jamu10};

        Jamu a = new Jamu("Beras Kencur", 2, covers[0], "Dapat menghilangkan pegal-pegal pada tubuh dan " +
                "sebagai tonikom atau penyegar saat habis bekerja",
                "Dua bahan dasar pokok yang selalu dipakai, yaitu beras dan kencur.\n" +
                        "Bahan-bahan lain yang biasa dicampurkan ke dalam racikan jamu beras kencur adalah\n" +
                        "biji kedawung, rimpang jahe, biji kapulogo, buah asam, kayu keningar, kunir.\n" +
                        "Sebagai pemanis digunakan gula merah dicampur gula putih.",
                "");
        jamuList.add(a);

        a = new Jamu("Cabe Puyang", 2, covers[1], "Menghilangkan cikalen, pegal, dan linu-linu di tubuh", "", "");
        jamuList.add(a);

        a = new Jamu("Kudu Laos", 9, covers[2], "Menurunkan tekanan darah,melancarkan peredaran darah," +
                " menghangatkan badan, membuat perut terasa nyaman, menambah nafsu makan, melancarkan haid, dan menyegarkan badan",
                "", "");
        jamuList.add(a);

        a = new Jamu("Kunyit", 2, covers[3], "Menyegarkan tubuh atau dapat membuat tubuh menjadi dingin", "", "");
        jamuList.add(a);

        a = new Jamu("Pahitan", 1, covers[4], "Untuk gatal-gatal dan kencing manis", "", "");
        jamuList.add(a);

        a = new Jamu("Kunci Suruh", 2, covers[5], "Mengobati keluhan keputihan (fluor albus)," +
                "merapatkan bagian intim wanita (vagina), menghilangkan bau badan, " +
                "mengecilkan rahim dan perut, serta dikatakan dapat menguatkan gigi", "", "");
        jamuList.add(a);

        a = new Jamu("Uyup-uyup", 9, covers[6], "Meningkatkan produksi air susu ibu pada ibu yang sedang menyusui",
                "", "");
        jamuList.add(a);

        a = new Jamu("Sinom", 1, covers[7], "Menambah nafsu makan, mengatasi peradangan lambung " +
                "atau maag dan mengatasi masalah keputihan pada wanita", "", "");
        jamuList.add(a);

        a = new Jamu("Temulawak", 8, covers[8], "Mengatasi gangguan pencernaan, " +
                "meringankan osteoarthritis dan mengatasi kanker", "", "");
        jamuList.add(a);

        a = new Jamu("Daun Pepaya", 5, covers[9], "Mencegah sekaligus menyembuhkan penyakit malaria", "", "");
        jamuList.add(a);

        adapter.notifyDataSetChanged();
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public void doClick(int pos) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(JAMU, jamuList.get(pos));
        startActivity(intent);
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}
