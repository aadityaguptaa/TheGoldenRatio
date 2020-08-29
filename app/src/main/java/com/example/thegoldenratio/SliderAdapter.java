package com.example.thegoldenratio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public  int[] slide_images = {
            R.drawable.horizontalline,
            R.drawable.goldenline
    };

    public String[] head_lines = {
      "Eat",
      "Code"
    };

    public String[] slide_desc = {
      "sjfbsakdbvbashjvbasd f aydf akdhf aeyf ad",
      "skdjhfbjsahd ksdjhafba sdvs vjhasbv sa iuhf sadbksd kasd klasdjfbhk asdbv ksafj ewuif k"
    };

    @Override
    public int getCount() {
        return head_lines.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView)view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView)view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView)view.findViewById(R.id.slide_description);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(head_lines[position]);
        slideDescription.setText(slide_desc[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
