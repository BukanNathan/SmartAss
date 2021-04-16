//package com.example.product;
//
//import androidx.viewpager.widget.PagerAdapter;
//import android.content.Context;
//import androidx.viewpager.widget.ViewPager;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//public class ImageAdapter extends PagerAdapter {
//    Context mContext;
//
//    ImageAdapter(Context context) {
//        this.mContext = context;
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == ((ImageView) object);
//    }
//
//    private int[] sliderImageId = new int[]{
//            R.drawable.bowl, R.drawable.food, R.drawable.necklace
//    };
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        ImageView imageView = new ImageView(mContext);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setImageResource(sliderImageId[position]);
//        ((ViewPager) container).addView(imageView, 0);
//        return imageView;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        ((ViewPager) container).removeView((ImageView) object);
//    }
//
//    @Override
//    public int getCount() {
//        return sliderImageId.length;
//    }
//}
