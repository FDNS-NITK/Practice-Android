package flappybird.failedcoder.com.demo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by com on 14-02-2018.
 */

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public int [] slide_images = {
      R.drawable.garam,
      R.drawable.code,
            R.drawable.owl
    };

    public String [] slide_headings = {
      "Eat",
            "Sleep",
            "Code"
    };

    public String [] slider_desc= {
      "Fdgrgerkwbgjfewo iuo iuheotieiuttjhbjh   iueg foifiiu hi hiuer hip jf udshfo eeh f",
        "dsjhfoui  hr eiojehr erh ehoeiuf efhosdih fih isgfoi sff hosidf hs",
        "hfo iw iuweho hoiwehoiuh hipewhuipwehpihw"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }
    public Object instantiateItem(ViewGroup container,int position){
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
