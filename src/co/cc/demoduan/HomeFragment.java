package co.cc.demoduan;


import android.os.Bundle;
import android.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class HomeFragment extends Fragment {
	TextView tvThucDon;
	public static HomeFragment newInstance(String param1, String param2) {
		HomeFragment fragment = new HomeFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	public HomeFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view= inflater.inflate(R.layout.fragment_home, container, false);
		tvThucDon=(TextView)view.findViewById(R.id.tvThucDon);
		tvThucDon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent a=new Intent(getActivity(),ThucDonActivity.class);
				startActivity(a);
			}
		});
		return view;
	}
}
