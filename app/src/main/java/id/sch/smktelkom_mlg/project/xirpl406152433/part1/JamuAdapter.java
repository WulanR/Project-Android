package id.sch.smktelkom_mlg.project.xirpl406152433.part1;

/**
 * Created by Me on 11/20/2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        holder.thumbnail.setImageResource(jamu.getThumbnail());
        holder.title.setText(jamu.getName());
        holder.count.setText(jamu.getBahanpokok() + " bahan dasar pokok");

        // loading jamu cover using Glide library
        //Glide.with(mContext).load(jamu.getThumbnail()).into(holder.thumbnail);


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
            bahan = (TextView) view.findViewById(R.id.jamu_bahan);
            cara = (TextView) view.findViewById(R.id.jamu_cara);
            deskripsi = (TextView) view.findViewById(R.id.jamu_deskripsi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIJamuAdapter.doClick(getAdapterPosition());
                }
            });

            thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIJamuAdapter.doClick(getAdapterPosition());
                }
            });
        }
    }

}