package gla.cs.joose.coursework.invmgt.model;

/**
 *
 * @author inah Omoronyia
 */

import gla.cs.joose.coursework.invmgt.util.SearchBy;
import gla.cs.joose.coursework.invmgt.util.Util;

public class ItemFactory {
		
	public static boolean addItem(long barcode, String itemName, String itemType_s, int qty, String supplier, String desc){
		boolean done = false;
		if(itemType_s !=null){
			ItemType itemType = ItemType.getItemType(itemType_s);
			Item item = new Item(barcode, itemName, itemType, qty, supplier, desc);
			
			done = Util.storeItem(item);	
		}
				
		return done;
	}
	
	public static Item[] search(String searchbydesc, String pattern, int limit){
		SearchBy searchby = SearchBy.getSearchBy(searchbydesc);		
		Item [] results = Util.getMatchingItems(pattern, searchby, limit);
		
		return results;
	}
	
	public static boolean delete(Item item){
		return Util.deleteItem(item);
	}
	
	
	
	
	

}
