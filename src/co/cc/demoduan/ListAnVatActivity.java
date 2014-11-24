package co.cc.demoduan;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import co.cc.xml.Doan;
import co.cc.xml.QuanlyXMLHandler;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListAnVatActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_an_vat);
		
		// Đối tượng quản lý thư mục asset trong một ứng dụng Android
		AssetManager assetManager= getBaseContext().getAssets();
		try{
			Intent nhan=getIntent();
			String n=nhan.getStringExtra("B");
			InputStream as=null;
			if (n.equals("Nem chua rán")) {
				// Lấy 1 tập tin làm dữ liệu đầu vào có tên là "order.xml"
				as = assetManager.open("nemchua.xml");
			} else if (n.equals("Zaua muối")) {
				as = assetManager.open("zaua.xml");
			} else if (n.equals("Bánh ép Huế")) {
				as = assetManager.open("banhephue.xml");
			} else if (n.equals("Phô mai que và Tiramisu")) {
				as = assetManager.open("phomaique.xml");
			} else if (n.equals("Chè nóng Phan Châu Trinh")) {
				as = assetManager.open("chenong.xml");
			} else if (n.equals("Bánh tráng kẹp")) {
				as = assetManager.open("banhtrangkep.xml");
			} else if (n.equals("Ốc Ba Dzớt")) {
				as = assetManager.open("oc.xml");
			}
			

			// Tạo đối tượng dùng cho việc phân tích cú pháp tài liệu XML
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			// Đối tượng đọc XML
			XMLReader xr = sp.getXMLReader();

			// Tạo đối tượng xử lý XML theo tuần tự của mình
			QuanlyXMLHandler myXMLHandler = new QuanlyXMLHandler();
			// Thiết lập nội dung xử lý
			xr.setContentHandler(myXMLHandler);
			// Nguồn dữ liệu vào
			InputSource inStream = new InputSource(as);
			// Bắt đầu xử lý dữ liệu vào
			xr.parse(inStream);

			// Lấy dữ liệu in thông tin 
			String tua = myXMLHandler.getTuaTruyen();
			LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout1);
			

			TextView tv_tua = (TextView) findViewById(R.id.tv_tua);
			tv_tua.setText("" + tua + "");

			TextView tv = new TextView(this);
			tv.setText("");
			ll.addView(tv);

			// In chi tiết 
			ArrayList<Doan> doanList = myXMLHandler.getDoanList();
			for (Doan doanCT : doanList) {
				tv = new TextView(this);
				tv.setText(doanCT.getDoanSo());
				tv.setTextColor(Color.argb(255, 66, 0, 66));
				ll.addView(tv);

				tv = new TextView(this);
				tv.setText(" ");
				ll.addView(tv);
			}
			as.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
