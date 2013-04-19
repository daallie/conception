import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

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
		factory = SAXParserFactory.newInstance();
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
		File[] ore = fileList("items/ore");
		File[] trees = fileList("items/trees");
		
		totalItems = crops.length + ore.length + trees.length;
		
		// Load Crops
		for(File file : crops)
			cropList.add((Crop) loadFile(file, "crop"));
	}
	
	private Item loadFile(File f, String s)
	{
		Handler handler = null;
		
		switch(s)
		{
		case "crop":
			handler = new CropHandler();
		}
		
		try {

		    InputStream xmlInput  = new FileInputStream(f);
		    SAXParser saxParser = factory.newSAXParser();

		    saxParser.parse(xmlInput, handler);
		    
		} catch (Throwable err) {
		    err.printStackTrace ();
		}
		
		currentLocation++;
		return handler.getItem();
	}
	
	private File[] fileList(String s)
	{
		File folder = new File(s);
		return folder.listFiles();
	}
}

abstract class Handler extends DefaultHandler
{
	public abstract Item getItem();
}

class CropHandler extends Handler 
{
	Crop c;
	Stack<String> s;	
	
	public CropHandler()
	{
		super();
		c = new Crop();
		s = new Stack<String>();
	}

    public void startElement(String uri, String localName, String qName, Attributes attributes)	throws SAXException 
    {
    	s.push(qName);
    }

    public void endElement(String uri, String localName, String qName)
    throws SAXException {
        s.pop();
    }

    public void characters(char ch[], int start, int length)
    throws SAXException 
    {
    	String value = new String(ch, start, length);
    	
    	switch(s.peek())
    	{
    	case "name":
    		c.setName(value);
    		break;
    	case "spread":
    		c.setSpread(Boolean.valueOf(value));
    		break;
    	case "min":
    		String min = s.pop();
    		set(s.peek(), min, value);
    		s.push(min);
    		break;
    	case "max":
    		String max = s.pop();
    		set(s.peek(), max, value);
    		s.push(max);
    		break;
    	case "optimal":
    		String optimal = s.pop();
    		set(s.peek(), optimal, value);
    		s.push(optimal);
    		break;
    	}
    }
    
    private void set(String s, String a, String v)
    {
    	switch(s)
    	{
    	case "fertility":
    		Byte fertility = Byte.valueOf(v);
    		if(a.equals("min"))
    		{
    			c.setMinFertility(fertility);
    		}
    		else if(a.equals("max"))
    		{
    			c.setMaxFertility(fertility);
    		}
    		else
    		{
    			c.setOptimalFertility(fertility);
    		}
    		break;
    	case "elevation":
    		Byte elevation = Byte.valueOf(v);
    		if(a.equals("min"))
    		{
    			c.setMinElevation(elevation);
    		}
    		else if(a.equals("max"))
    		{
    			c.setMaxElevation(elevation);
    		}
    		else
    		{
    			c.setOptimalElevation(elevation);
    		}
    		break;
    	case "humidity":
    		Byte humidity = Byte.valueOf(v);
    		if(a.equals("min"))
    		{
    			c.setMinHumidity(humidity);
    		}
    		else if(a.equals("max"))
    		{
    			c.setMaxHumidity(humidity);
    		}
    		else
    		{
    			c.setOptimalHumidity(humidity);
    		}
    		break;
    	//TODO Add Seasons Cases will need to make changes to Crop
    	}
    }

    public Item getItem()
    {
    	return c;
    }
}
