import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ItemTable
{
	private static ArrayList<Crop> cropList;
	private static ArrayList<Ore> oreList;
	private static ArrayList<Tree> treeList;
	private int totalItems;
	private int currentLocation;
	private SAXParserFactory factory;
	
	public ItemTable()
	{
		cropList = new ArrayList<Crop>();
		oreList = new ArrayList<Ore>();
		treeList = new ArrayList<Tree>();
		totalItems = 0;
	}
	
	/*
	 * Loader Status Methods
	 */
	public double status()
	{
		return 0.0;
	}
	
	/*
	 * Loader Methods
	 */
	public void load()
	{
		File[] crops = fileList("items/crops");
		File[] ore = fileList("item/ore");
		File[] trees = fileList("item/trees");
		
		totalItems = crops.length + ore.length + trees.length;
		
		// Load Crops
		for(File file : crops)
			loadFile(file);
	}
	
	private void loadFile(File f)
	{
		factory = SAXParserFactory.newInstance();
		try {

		    InputStream xmlInput  = new FileInputStream(f);
		    SAXParser saxParser = factory.newSAXParser();

		    DefaultHandler handler   = new SaxHandler();
		    saxParser.parse(xmlInput, handler);

		} catch (Throwable err) {
		    err.printStackTrace ();
		}
	}
	
	private File[] fileList(String s)
	{
		File folder = new File(s);
		return folder.listFiles();
	}
}

class SaxHandler extends DefaultHandler {

    public void startDocument() throws SAXException {
        System.out.println("start document   : ");
    }

    public void endDocument() throws SAXException {
        System.out.println("end document     : ");
    }

    public void startElement(String uri, String localName,
        String qName, Attributes attributes)
    throws SAXException {

        System.out.println("start element    : " + qName);
    }

    public void endElement(String uri, String localName, String qName)
    throws SAXException {
        System.out.println("end element      : " + qName);
    }

    public void characters(char ch[], int start, int length)
    throws SAXException {
        System.out.println("start characters : " +
            new String(ch, start, length));
    }

}
