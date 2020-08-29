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
            R.drawable.leohumangolden,
            R.drawable.euclid,
            R.drawable.goldenline,
            R.drawable.sheep,
            R.drawable.fibonacci,
            R.drawable.nature,
            R.drawable.solarsystem,
            R.drawable.idea
    };

    public String[] head_lines = {
      "The Golden Ratio",
      "Euclid",
            "Calculation",
            "Overview",
            "The Fibonacci Sequence",
            "Nature and Life",
            "The Solar System and Universe",
            ""
    };

    public String[] slide_desc = {
      "In mathematics, two quantities are in the golden ratio if their ratio is the same as the ratio of their sum to the larger of the two quantities.\n\n The golden ratio is also called the golden mean or golden section ",
      "Mathematicians since Euclid have studied the properties of the golden ratio, including its appearance in the dimensions of a regular pentagon and in a golden rectangle, which may be cut into a square and a smaller rectangle with the same aspect ratio. ",
            "Two quantities a and b are said to be in the golden ratio φ if\n" +
                    "\n" +
                    "(a + b) / a  =  a / b  =  φ\n\n",
            "What makes a single number so interesting that ancient Greeks, Renaissance artists, a 17th century astronomer and a 21st century novelist all would write about it?",
            "The Fibonacci sequence” provides yet another way to derive Phi mathematically. The series is quite simple. Start with 0 and add 1 to get 1. Then repeat the process of adding each two numbers in the series to determine the next one: 0, 1, 1, 2, 3, 5, 8, 13, 21 and so on. \n\nThe relationship to the Golden Ratio or Phi is found by dividing each number by the one before it. The further you go in the series, the closer the result gets to Phi",
            "Fibonacci numbers frequently appear in the numbers of petals in a flower and in the spirals of plants.  The positions and proportions of the key dimensions of many animals are based on Phi. Examples include the body sections of ants and other insects, the wing dimensions and location of eye-like spots on moths, the spirals of sea shells and the position of the dorsal fins on porpoises. Even the spirals of human DNA embody phi proportions.",
            "Curiously enough, we even find golden ratio relationships in the solar system and universe. The diameters of the Earth and Moon form a triangle whose dimensions are based on the mathematical characteristics of phi. The distances of the planets from the sun correlate surprisingly closely to exponential powers of Phi. The beautiful rings of Saturn are very close in dimension to the golden ratio of the planet’s diameter. NASA released findings in 2003 that the shape of the Universe is a dodecahedron based on Phi.",
            "Enough talk, Let's actually see examples :)"
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
