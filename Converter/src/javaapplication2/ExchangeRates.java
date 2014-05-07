package javaapplication2;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.math.BigDecimal;
import java.net.*; // for URLs
import java.util.*; // for lists, data structures

/**
 *
 * @author Biljana
 */
public class ExchangeRates {
    public static double rate;
    private HashMap<String, Double> rates;
    
    public ExchangeRates () {
        rates = new HashMap<String, Double> ();
    }
    
    public double convertTo (String ToUnits) {
       double rate = 0;
       
       return rate;
        
    }
    
    public static void main (String[] args) {
        ExchangeRates exRates = new ExchangeRates();
      
         if (!exRates.update(exRates.getUnits()))
            System.out.println("RSS feed error. Cannot get exchange rates.");
            
    }

    /*
    
    */
    boolean update(String usd) {
        boolean successful = true;
        
        String URLStr = "http://themoneyconverter.com/rss-feed/";
        String FromCurrency = usd;
       // System.out.println(usd);
        SyndFeed feed = null;
        try {
            URL feedUrl = new URL(URLStr+FromCurrency+"/rss.xml");
            
            SyndFeedInput input = new SyndFeedInput();
            feed = input.build(new XmlReader(feedUrl));  
            
             //System.out.println(feed);
        }
        catch (Exception ex)
        {
            successful = false;
            ex.printStackTrace();
            System.out.println("ERROR: "+ ex.getMessage());
        }
        
        if (feed != null)
        {
            for (SyndEntry entry: (List<SyndEntry>) feed.getEntries())
            {
                String title = entry.getTitle();
                String descr = entry.getDescription().getValue();
                String[] tokens = descr.split("=");
                tokens = tokens[1].split(" ");
              //  System.out.println(descr);
              //  System.out.println(tokens[1]);
                if (tokens[1].contains(","))
                    rate = Double.parseDouble(tokens[1].replaceAll(",",""));
                else
                    // System.out.println(tokens[6]);
                    rate = Double.parseDouble(tokens[1]);
                // put it to hash table
                rates.put(title, rate);
            }
        }
        else
        {
            successful = false;
            System.out.println("RSS Feed Unavailable!");
        }
        
        return successful;
        
    }

    /*
    
    */
   public Double get(String To, String From) {
        String key = To.toUpperCase() + "/" + From.toUpperCase();
       // System.out.println(key);
        double rate = 0.0;
        if (rates.containsKey(key))
            rate = rates.get(key);
        return rate;
    }

    private String getUnits() {
        
        return null;
        
    }
    
}