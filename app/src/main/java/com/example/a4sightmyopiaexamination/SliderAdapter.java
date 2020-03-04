package com.example.a4sightmyopiaexamination;

import android.content.Context;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import org.w3c.dom.Text;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

//    public int[] slide_images = {
//
//            R.drawable.eat_icon,
//            R.drawable.sleep_icon,
//            R.drawable.code_icon,
//    };

    public String[] slide_headings = {

            "STEP 1",
            "STEP 2",
            "STEP 3",
    };

    public String[] slide_descs = {
            "Place the phone 5 meters away at eye level.",
            " As the test proceeds it will prompt you to close your left or right eye. When prompted "+
                    "close that eye and than read aloud the letters that appears on the screen of your device",
            "The test will continue until you reach the smallest font size or you incorrectly read 3"+
                    "    letters of the same font size."
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        //ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescriptions = (TextView) view.findViewById(R.id.slide_desc);

        //slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescriptions.setText(slide_descs[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
