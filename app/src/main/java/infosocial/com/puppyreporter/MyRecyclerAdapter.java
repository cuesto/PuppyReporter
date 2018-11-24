package infosocial.com.puppyreporter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyRecycleItemViewHolder> {

    private List<Report> reportList;
    private final Context context;

    public MyRecyclerAdapter(Context context, List<Report> reportList) {
        this.context = context;
        this.reportList = reportList;
    }

    @Override
    public void onBindViewHolder(MyRecycleItemViewHolder holder, int position) {

        byte[] decodedString = Base64.decode(reportList.get(position).getPhoto(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(decodedByte);

        holder.imageView.setBackground(bitmapDrawable);

        holder.tvLocation.setText(reportList.get(position).getLocation());
        holder.tvDescription.setText(reportList.get(position).getDescription());

        holder.imgBtnShare.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            String shareBody = "Ayuda a este perrito a recibir ayuda, fue visto por última vez en: "
                    + reportList.get(position).getLocation()
                    + ". Puedes colaborar con un depósito a BPD: 4568526";
            String shareSub = "Puppy Reporter";
            i.putExtra(Intent.EXTRA_SUBJECT, shareSub);
            i.putExtra(Intent.EXTRA_TEXT, shareBody);
            context.startActivity(Intent.createChooser(i, "Compartir"));
        });

    }

    @Override
    public MyRecyclerAdapter.MyRecycleItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview, parent, false);
        MyRecycleItemViewHolder holder = new MyRecycleItemViewHolder(view);

        return holder;
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }



    public static class MyRecycleItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tvLocation;
        public TextView tvDescription;
        public ImageView imageView;
        public ImageButton imgBtnShare;



        public MyRecycleItemViewHolder(View itemView) {
            super(itemView);
            tvLocation = (TextView) itemView.findViewById(R.id.tvLocation);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            imageView =
                    (ImageView) itemView.findViewById(R.id.imageView);

            imgBtnShare = itemView.findViewById(R.id.imgBtnShare);
        }
    }
}
