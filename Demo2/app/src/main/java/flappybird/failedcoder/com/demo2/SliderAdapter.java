package flappybird.failedcoder.com.demo2;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public int [] slide_images = {
            R.drawable.presentation,
            R.drawable.code,
            R.drawable.group,
            R.drawable.chick,
            R.drawable.notebook
    };

    public String [] slide_headings = {
            "What we Provide",
            "Learn",
            "FORUM",
            "Flappy-Bird",
            "Notes"
    };

    public String [] slider_desc= {
            "We are here to provide links and access to free resources available at one place. We will try to provide learning materials for various exams like GATE, CAT, RAILWAYS, and specially for CODING",
            "Get links of videos, pdfs, website, books and many more things to learn. Get best links to learn coding stuffs also.",
            "It is a place where you or any one can hold online sessions. For sharing stuffs or for asking doubts.",
            "A traditional and a simple game to play and to get addicted",
            "Here you can write important stuffs and save them for future reference"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }
    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide,container,false);
        ImageView slideimage = view.findViewById(R.id.slideimage);
        TextView sliderhead = view.findViewById(R.id.heading);
        TextView description = view.findViewById(R.id.description);

        slideimage.setImageResource(slide_images[position]);
        sliderhead.setText(slide_headings[position]);
        description.setText(slider_desc[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
