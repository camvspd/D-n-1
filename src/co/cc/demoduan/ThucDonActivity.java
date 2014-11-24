package co.cc.demoduan;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import co.cc.demoduan.adapter.DataBaseHelper;

public class ThucDonActivity extends Activity {
	ImageView ImgMAC, ImgMAV;
	DataBaseHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thuc_don);
		ImgMAC=(ImageView)findViewById(R.id.ImgMAC);
		ImgMAV=(ImageView)findViewById(R.id.ImgMAV);
		db = new DataBaseHelper(getApplicationContext());
		try {
			db.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImgMAC.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent it = new Intent(ThucDonActivity.this,
						HangQuanActivity.class);
				startActivity(it);

			}
		});
		ImgMAV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(ThucDonActivity.this,
						AnVatActivity.class);
				startActivity(it);
			}
		});
	}
}
