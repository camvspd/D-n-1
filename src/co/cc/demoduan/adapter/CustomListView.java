package co.cc.demoduan.adapter;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import co.cc.demoduan.R;

public class CustomListView extends ArrayAdapter<Obj> implements Filterable {
	int layoutID;
	private Context mContext;
	private ArrayList<Obj> originalList = new ArrayList<Obj>();
	private ArrayList<Obj> viewList = new ArrayList<Obj>();

	public CustomListView(Context mContext, int layoutID, ArrayList<Obj> arrList) {
		super(mContext, layoutID, layoutID);
		this.mContext = mContext;
		// mInflater = LayoutInflater.from(this.mContext);
		this.layoutID = layoutID;
		this.originalList = arrList;
		viewList= originalList;
	}

	public class ViewHolder {
		TextView tvtest;
		ImageView ivWordImage;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return viewList.size();
	}

	@Override
	public Obj getItem(int position) {
		// TODO Auto-generated method stub
		return viewList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			// LayoutInflater mInflater=(LayoutInflater)
			// mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
			// View convertView =
			// mInflater.inflate(layout.word_list2_item,parent,false);
			convertView = View.inflate(mContext, layoutID, null);
			holder.ivWordImage = (ImageView) convertView
					.findViewById(R.id.ivWordImage);
			holder.tvtest = (TextView) convertView.findViewById(R.id.tvtest);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		byte[] outImage = viewList.get(position).getWord_image();
		ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
		Bitmap theImage = BitmapFactory.decodeStream(imageStream);
		holder.ivWordImage.setImageBitmap(theImage);
		holder.tvtest.setText(viewList.get(position).getWord_meaning());
		return convertView;
	}

	public Filter getFilter() {
		return new Filter() {

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				final FilterResults oReturn = new FilterResults();
				final ArrayList<Obj> results = new ArrayList<Obj>();
				Log.d("hello", "constraint: " + constraint + "-------------------------------------");
				if (constraint != null && constraint.toString().length() > 0) {
					String query = constraint.toString();

					for (int i = 0; i < originalList.size(); i++) {
						if (originalList.get(i).getWord_meaning().indexOf(query) >= 0) {
							results.add(originalList.get(i));
						}
					}

					oReturn.count = results.size();
					oReturn.values = results;
				} else {
					synchronized (this) {
						oReturn.values = originalList;
						oReturn.count = originalList.size();
					}
				}

				return oReturn;
			}

			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence constraint,
					FilterResults results) {
				if (results .values!= null)
					viewList = (ArrayList<Obj>) results.values;
				notifyDataSetChanged();
			}
		};
	}

	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();

	}
}
