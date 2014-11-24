package co.cc.xml;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
 
public class QuanlyXMLHandler extends DefaultHandler 
{
 
    boolean currentElement = false;
    String currentValue = "";
 
    String tuatruyen;

    Doan doanInfo;
    ArrayList<Doan> doanList;
 
    public String getTuaTruyen() 
    {
        return tuatruyen;
    }
 
    public ArrayList<Doan> getDoanList() 
    {
        return doanList;
    }
 
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException 
   {
 
        currentElement = true;
 
        if (qName.equals("TruyenCoTich")) 
        {
            doanList = new ArrayList<Doan>();
        } else if (qName.equals("Doanchitiet")) 
        {
            doanInfo = new Doan();
        }
    }
 
    public void endElement(String uri, String localName, String qName)
            throws SAXException 
    {
 
        currentElement = false;
 
        if (qName.equalsIgnoreCase("TuaTruyen"))
            tuatruyen = currentValue.trim();
        else if (qName.equalsIgnoreCase("LineNumber"))
            doanInfo.setDoanSo(currentValue.trim());
        else if (qName.equalsIgnoreCase("Doanchitiet"))
            doanList.add(doanInfo);
 
        currentValue = "";
    }
 
    public void characters(char[] ch, int start, int length)
            throws SAXException {
 
        if (currentElement) {
            currentValue = currentValue + new String(ch, start, length);
        }
    }
}