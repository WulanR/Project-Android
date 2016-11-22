package id.sch.smktelkom_mlg.project.xirpl406152433.part1;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
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
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PijatActivity.class);
                startActivity(i);
            }
        });

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
                "Pada umumnya tidak jauh berbeda, mula-mula beras disangan (disangrai), selanjutnya " +
                        "ditumbuk sampai halus. Bahan-bahan lain sesuai dengan komposisi racikan ditumbuk menggunakan lumpang dan alu besi atau batu." +
                        "Kedua bahan ini kemudian dicampur, ditungkan air mendidih untuk mengambil " +
                        "sarinya diperas dan disaring dengan saringan atau diperas melalui kain pembungkus bahan." +
                        "Selanjutnya dimasukkan ke dalam botol-botol atau termos.");
        jamuList.add(a);

        a = new Jamu("Cabe Puyang", 2, covers[1], "Menghilangkan cikalen, pegal, dan linu-linu di tubuh", "Pada umumnya tidak jauh berbeda, mula-mula beras disangan (disangrai), " +
                "selanjutnya ditumbuk sampai halus. " +
                "Bahan-bahan lain sesuai dengan komposisi racikan ditumbuk menggunakan lumpang dan alu besi atau batu. " +
                "Kedua bahan ini kemudian dicampur, ditungkan air mendidih untuk mengambil sarinya diperas dan disaring dengan saringan atau diperas melalui kain pembungkus bahan." +
                " Selanjutnya dimasukkan ke dalam botol-botol atau termos.",
                "Pada umumnya tidak jauh berbeda, yaitu pertama-tama air direbus sampai mendidih dan dibiarkan sehingga dingin, " +
                        "jumlahnya sesuai dengan kebutuhan. Bahan-bahan sesuai dengan komposisi racikan ditumbuk menggunakan lumpang dan alu besi atau batu. " +
                        "Seluruh bahan ini kemudian diperas melalui saringan ke dalam air matang yang sudah tersedia. " +
                        "Selanjutnya, ramuan yang diperoleh diaduk rata kemudian dimasukkan ke dalam botol-botol.");
        jamuList.add(a);

        a = new Jamu("Kudu Laos", 9, covers[2], "Menurunkan tekanan darah,melancarkan peredaran darah," +
                " menghangatkan badan, membuat perut terasa nyaman, menambah nafsu makan, melancarkan haid, dan menyegarkan badan",
                "Bahan utama kudu laos, " +
                        "adalah Buah mengkudu, rimpang laos, Merica, asam kawak, " +
                        "cabe jamu, bawang putih, kedawung, garam secukupnya, gula jawa bisa juga ditambah gula pasir.",
                "Cara pengolahan pada umumnya tidak jauh berbeda antar penjual jamu yaitu pertama-tama air direbus sampai mendidih sejumlah sesuai kebutuhan. " +
                        "Bahan-bahan sesuai dengan komposisi racikan ditumbuk secara kasar menggunakan lumpang dan alu besi atau batu kemudian diperas dan disaring dimasukkan ke dalam air matang yang sudah dingin. " +
                        "Selanjutnya ditambahkan gula sampai diperoleh rasa manis sesuai selera." +
                        " Ramuan selanjutnya dimasukkan ke dalam botol-botol dan siap untuk dijajakan.");
        jamuList.add(a);

        a = new Jamu("Kunyit", 2, covers[3], "Menyegarkan tubuh atau dapat membuat tubuh menjadi dingin",
                "Penggunaan bahan baku jamu kunir asam pada umumnya tidak jauh berbeda di antara pembuat. " +
                        "Perbedaan terlihat pada komposisi bahan penyusunnya. " +
                        "Jamu dibuat dengan bahan utama buah asam ditambah kunir/kunyit, namun beberapa pembuatnya ada yang mencampur dengan sinom (daun asam muda), " +
                        "temulawak, biji kedawung, dan air perasan buah jeruk nipis. " +
                        "Sebagai pemanis digunakan gula merah dicampur gula putih dan seringkali mereka juga mencampurkan gula buatan, " +
                        "serta dibubuhkan sedikit garam.",
                "Pada umumnya tidak jauh berbeda antar penjual jamu," +
                        " yaitu direbus sampai mendidih dan jumlahnya sesuai kebutuhan." +
                        " Bahan-bahan sesuai dengan komposisi racikan ditumbuk secara kasar menggunakan lumpang dan alu besi atau batu atau diiris tipis-tipis (kunyit), " +
                        "dimasukkan ke dalam air mendidih dan direbus sampai mendidih beberapa saat. " +
                        "Selanjutnya, ditambahkan gula sampai diperoleh rasa manis sesuai selera (dicicipi). " +
                        "Rebusan yang diperoleh dibiarkan sampai agak dingin, kemudian disaring dengan saringan. " +
                        "Rebusan yang sudah disaring dibiarkan dalam panci dan selanjutnya dimasukkan ke dalam botol-botol dan siap untuk dijajakan.");
        jamuList.add(a);

        a = new Jamu("Pahitan", 1, covers[4], "Untuk gatal-gatal dan kencing manis",
                "Bahan baku dasar dari jamu pahitan adalah sambiloto. " +
                        "Racikan pahitan sangat bervariasi, ada yang hanya terdiri dari sambiloto, " +
                        "tetapi ada pula yang menambahkan bahan-bahan lain yang rasanya juga pahit seperti brotowali, " +
                        "widoro laut, doro putih, dan babakan pule." +
                        " Ada pula yang mencampurkan bahan lain seperti adas dan atau empon-empon (bahan rimpang yang dipergunakan dalam bumbu masakan). " +
                        "Ramuan jamu pahitan sebaiknya dicampur dengan berbagai rempah-rempah dan empon-empon," +
                        " jika ramuan tidak dicampur dengan berbagai rempah-rempah dan empon-empon ada indikasi kurang baik untuk kesehatan.",
                "Pembuatan jamu pahitan adalah dengan merebus semua bahan ke dalam air sampai air rebusan menjadi tersisa sekitar separuhnya. " +
                        "Cara ini dimaksudkan agar semua zat berkhasiat yang terkandung dalam bahan dapat larut ke dalam air rebusan." +
                        " Sebagai hasil akhirnya, diperoleh rebusan dengan rasa sangat pahit. " +
                        "Khusus jamu pahitan, tidak diberikan gula atau bahan pemanis lain." +
                        " Sebagai penawar rasa pahit, " +
                        "konsumen minum jamu gendong lain yang mempunyai rasa manis dan segar seperti sinom atau kunir asam.");
        jamuList.add(a);

        a = new Jamu("Kunci Suruh", 2, covers[5], "Mengobati keluhan keputihan (fluor albus)," +
                "merapatkan bagian intim wanita (vagina), menghilangkan bau badan, " +
                "mengecilkan rahim dan perut, serta dikatakan dapat menguatkan gigi",
                "Bahan baku jamu ini sesuai dengan namanya, " +
                        "yaitu rimpang kunci dan daun sirih." +
                        " Biasanya selalu ditambahkan buah asam yang masak." +
                        " Beberapa penjual jamu menambahkan bahan-bahan lain yang biasa digunakan dalam ramuan jamu keputihan atau jamu sari rapat seperti buah delima," +
                        " buah pinang, kunci pepet, dan majakan. Dalam penelitian ini, ditemukan bahan lain yang ditambahkan, yaitu jambe, manis jangan, kayu legi, beluntas, dan kencur. " +
                        "Sebagai pemanis digunakan gula pasir, gula merah, dan dibubuhkan sedikit garam.",
                "Cara pengolahan pada umumnya tidak jauh berbeda antar penjual jamu, " +
                        "yaitu air direbus sampai mendidih sesuai dengan kebutuhan. " +
                        "Bahan-bahan sesuai dengan komposisi racikan ditumbuk secara kasar menggunakan lumpang dan alu besi atau batu atau diiris tipis-tipis (kunyit), " +
                        "diperas, disaring, dan dimasukkan ke dalam air matang yang sudah didinginkan." +
                        " Selanjutnya, ditambahkan gula sesuai kebutuhan, sampai diperoleh rasa manis sesuai selera dengan cara dicicipi." +
                        " Ramuan selanjutnya dimasukkan ke dalam botol-botol dan siap untuk dijajakan.");
        jamuList.add(a);

        a = new Jamu("Uyup-uyup", 9, covers[6], "Meningkatkan produksi air susu ibu pada ibu yang sedang menyusui",
                "Bahan baku jamu uyup-uyup sangat bervariasi antar pembuat jamu, " +
                        "namun pada umumnya selalu menggunakan bahan empon-empon yang terdiri dari kencur, jahe, bangle, laos, kunir, daun katu, " +
                        "temulawak, puyang, dan temugiring. ",
                "Cara pengolahan pada umumnya tidak jauh berbeda antar penjual jamu, yaitu semua bahan dicuci bersih tanpa dikupas, " +
                        "selanjutnya empon-empon dirajang (diiris tipis), ditambah bahan-bahan lain, ditumbuk kasar, lalu diperas serta disaring. " +
                        "Perasan dimasukkan ke dalam air matang yang sudah dingin. " +
                        "Selanjutnya ditambahkan gula sampai diperoleh rasa manis sesuai selera. " +
                        "Ramuan selanjutnya dimasukkan ke dalam botol-botol dan siap untuk diperjual belikan.");
        jamuList.add(a);

        a = new Jamu("Sinom", 1, covers[7], "Menambah nafsu makan, mengatasi peradangan lambung " +
                "atau maag dan mengatasi masalah keputihan pada wanita",
                "Jamu sinom ini hampir mirip dengan kunyit asam hanya saja bahan utamanya adalah sinom atau daun asam yang masih muda." +
                        " Dengan tambahan bahan biasanya temulawak, kunyit, kapulaga, kayu manis, pala, gula merah, serta gula pasir. ", "Cara pengolahan hampir sama dengan cara membuat jamu kunyit");
        jamuList.add(a);

        a = new Jamu("Temulawak", 8, covers[8], "Mengatasi gangguan pencernaan, " +
                "meringankan osteoarthritis dan mengatasi kanker", "100 gram temulawak\n" +
                "50 gram kencur\n" +
                "40 gram asem jawa kawak yang telah di keluarkan bijinya\n" +
                "200 mili air yang sudah masak\n" +
                "200 gram gula aren\n" +
                "4 helai daun pandan yang masi segar\n" +
                "20 gram jintan\n" +
                "2 liter air putih\n",
                "Langkah kesatu adalah bersihkan temulawaknya beserta kencurnya dengan dicuci  menggunakan air sampai kinclong tanpa kotoran biar higienis trus setelah itu diris-iris. \n" +
                        "Ambil wajan yang ukuran sedang untuk menyangrai temulawak dan kencur tanpa minyak goreng dengan waktu sebentar saja jangan lama-lama.Setelah beres sangrai temulawak dan kencurnya masukan kedalam blender beserta asam jawa kawak sama jinta dan air yang udah masak blender sampai benar-benar halus kemudian pinggirkan sejenak.\n" +
                        "Langkah berikutnya ambil panci kecil dipakai untuk merebus air dan gula aren beserta daun pandan sampai masak dan mendidih." +
                        "Langkah Terakir ambil hasil rebusan air gula aren daun pandan untuk di masukan menjadi satu di dalam belender yang sudah berisi hasil blenderan langkah kesatu diteruskan dengan di aduk aduk sampai merata dan menyatu. " +
                        "Tinggal disaring diperas diambil airnya saja pisah di gelas dan bimsalabim jadilah jamu temulawak karya tangan sendiri.\n");
        jamuList.add(a);

        a = new Jamu("Daun Pepaya", 5, covers[9], "Mencegah sekaligus menyembuhkan penyakit malaria",
                "\uF0A7\tDaun pepaya 5 lembar\n" +
                        "\uF0A7\tTemu hitam 0,5 ons\n" +
                        "\uF0A7\tAdas pulowaras 2 ruas\n" +
                        "\uF0A7\tAir matang secukupnya\n" +
                        "\uF0A7\tGaram dapur sedikit atau madu\n",
                "1.\tPertama-tama kupas kulit temu hitam, kemudian campur dengan adas pulowaras dan daun pepaya, cuci bersih ketiga bahan lalu tumbuk hingga halus.\n" +
                        "2.\tTambahkan air putih matang secukupnya kemudian peras airnya.\n" +
                        "3.\tSelanjutnya air perasan tersebut disaring lalu masukkan kedalam gelas.\n" +
                        "4.\tJika ingin meminum jamu daun pepaya, boleh ditambahkan sedikit garam atau madu lalu aduk rata agar tidak terlalu pahit.\n");
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
