package id.sch.smktelkom_mlg.project.xirpl406152433.part1;

/**
 * Created by Me on 11/20/2016.
 */

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class JamuAdapter extends RecyclerView.Adapter<JamuAdapter.MyViewHolder> {

    Context mContext;
    List<Jamu> jamuList;
    IJamuAdapter mIJamuAdapter;

    public JamuAdapter(Context mContext, List<Jamu> jamuList) {
        mIJamuAdapter = (IJamuAdapter) mContext;
        this.jamuList = jamuList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jamu_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Jamu jamu = jamuList.get(position);
        holder.thumbnail.setImageResource(jamu.thumbnail);
        holder.title.setText(jamu.getName());
        holder.count.setText(jamu.getBahanpokok() + " bahan dasar pokok");

        // loading jamu cover using Glide library
        //Glide.with(mContext).load(jamu.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_jamu, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    @Override
    public int getItemCount() {
        return jamuList.size();
    }

    public interface IJamuAdapter {
        void doClick(int pos);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count, bahan, cara, deskripsi;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            bahan = (TextView) view.findViewById(R.id.jamu_bahan);
            cara = (TextView) view.findViewById(R.id.jamu_cara);
            deskripsi = (TextView) view.findViewById(R.id.jamu_deskripsi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIJamuAdapter.doClick(getAdapterPosition());
                }
            });
        }
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Ditambahkan Favorit", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_read_later:
                    Toast.makeText(mContext, "Baca nanti", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }
}