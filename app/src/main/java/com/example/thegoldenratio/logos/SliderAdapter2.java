package com.example.thegoldenratio.logos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.thegoldenratio.R;

public class SliderAdapter2 extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter2(Context context) {
        this.context = context;
    }

    public  int[] slide_images = {
            R.drawable.applegolden,
            R.drawable.nationalgeographicgolden,
            R.drawable.pepsigoldenratio,
            R.drawable.toyotagolden,
            R.drawable.twittergolden,
            R.drawable.googlegolden,
            R.drawable.nikegolden,
            R.drawable.adidas
    };

    public String[] head_lines = {
            "Apple",
            "National Geographic",
            "Pepsi",
            "Toyota",
            "Twitter",
            "Google",
            "Nike",
            "Adidas"
    };


    @Override
    public int getCount() {
        return head_lines.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
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
        slideDescription.setText("");

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
