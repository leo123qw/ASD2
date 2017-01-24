package gla.cs.joose.coursework.invmgt.util;

/**
 *
 * @author inah Omoronyia
 */

import java.io.Serializable;

public enum SearchBy  implements Serializable{
	ITEMTYPE, DESCRIPTION, BARCODE, QUANTITY, SUPPLIER, ITEMNAME;
	
	public static String getSearchByDesc(SearchBy type){
		String  searchbydesc = null;

		switch (type) {
		case ITEMTYPE:
			searchbydesc = "Item Type";
			break;
		case DESCRIPTION:
			searchbydesc = "Description";
			break;
		case BARCODE:
			searchbydesc = "Barcode";
			break;
		case QUANTITY:
			searchbydesc = "Quantity";
			break;
		case SUPPLIER:
			searchbydesc = "Supplier";
			break;
		case ITEMNAME:
			searchbydesc = "Item Name";
			break;
		}
		
			
		

		return searchbydesc;
	}
	
	public static SearchBy getSearchBy(String searchbydesc){
		
		if(searchbydesc.contains(getSearchByDesc(SearchBy.ITEMTYPE))){
			return SearchBy.ITEMTYPE;
		}
		else if(searchbydesc.contains(getSearchByDesc(SearchBy.DESCRIPTION))){
			return SearchBy.DESCRIPTION;
		}
		else if(searchbydesc.contains(getSearchByDesc(SearchBy.BARCODE))){
			return SearchBy.BARCODE;
		}
		else if(searchbydesc.contains(getSearchByDesc(SearchBy.QUANTITY))){
			return SearchBy.QUANTITY;
		}
		else if(searchbydesc.contains(getSearchByDesc(SearchBy.SUPPLIER))){
			return SearchBy.SUPPLIER;
		}
		else if(searchbydesc.contains(getSearchByDesc(SearchBy.ITEMNAME))){
			return SearchBy.ITEMNAME;
		}
		return null;
	}
}