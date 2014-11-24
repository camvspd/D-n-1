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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListHangQuanActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_hang_quan);

		// Đối tượng quản lý thư mục asset trong một ứng dụng Android
		AssetManager assetManager = getBaseContext().getAssets();
		try {
			Intent nhan = getIntent();
			String n = nhan.getStringExtra("A");
			InputStream is = null;
			if (n.equals("Quán bún bò bà Đào")) {
				// Lấy 1 tập tin làm dữ liệu đầu vào có tên là "order.xml"
				is = assetManager.open("bunbo.xml");
			} else if (n.equals("Quán bún chả cá gia truyền ở Đà Nẵng")) {
				is = assetManager.open("bunchaca.xml");
			} else if (n.equals("Lẩu bò bà Duệ")) {
				is = assetManager.open("laubo.xml");
			} else if (n.equals("Cháo vịt")) {
				is = assetManager.open("chaovit.xml");
			} else if (n.equals("Quán Tây Bắc")) {
				is = assetManager.open("taybac.xml");
			} else if (n.equals("Bánh cuốn Ơ")) {
				is = assetManager.open("banhcuon.xml");
			} else if (n.equals("Heo Tộc xuống phố")) {
				is = assetManager.open("heotoc.xml");
			} else if (n.equals("Bún đậu mắm tôm")) {
				is = assetManager.open("bundaumamtom.xml");
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
			InputSource inStream = new InputSource(is);
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
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
