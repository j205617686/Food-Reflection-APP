package com.example.fim;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TitleBarProduct extends LinearLayout {
	protected TextView mTextView;
	protected ImageView image;

	public TitleBarProduct(final Context context) {
		super(context);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.title_bar_product, this);

		mTextView = (TextView) findViewById(R.id.titleBarTV);
		image = (ImageView) findViewById(R.id.right_image);

		findViewById(R.id.titleBarHomeButton).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.goHome(context);
			}
		});
	}

	public TitleBarProduct(final Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.title_bar, this);

		mTextView = (TextView) findViewById(R.id.titleBarTV);
		image = (ImageView) findViewById(R.id.right_image);

		findViewById(R.id.titleBarHomeButton).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.goHome(context);
			}
		});

		String initialText = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "text");
		if(initialText != null) {
			// ICEBOXI 有沒有更好的判別方法？
			try {
				setTitle(Integer.parseInt(initialText.substring(1)));
			} catch (Exception e) {
				setTitle(initialText);
			}
		}
	}

	public void setTitle(String text) {
		mTextView.setText(text);
	}

	public void setTitle(int resID) {
		mTextView.setText(resID);
	}

	public void setImageVisibility(int visibility)
	{
		image.setVisibility(visibility);
	}

	public void setImageOnClickListener(OnClickListener l)
	{
		image.setOnClickListener(l);
	}

	public void setImageResources(int resource)
	{
		image.setImageResource(resource);
	}
}
