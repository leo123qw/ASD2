package gla.cs.joose.coursework.invmgt.util;

/**
 *
 * @author inah Omoronyia
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.SecureRandom;

import gla.cs.joose.coursework.invmgt.model.Item;
import gla.cs.joose.coursework.invmgt.model.ItemType;

public class Util {
	public static final SecureRandom random = new SecureRandom();
	private static final String item_store_file_path = "inventorystore"; 
	
	public static Item[] getMatchingItems(String pattern, SearchBy searchby, int limit){
		Item items [] = new Item[0];
		if(pattern == null || pattern.trim().length()==0){
			return items;
		}
		File folder = new File(item_store_file_path);
		File[] itemids = folder.listFiles();

		for (int i = 0; i < itemids.length; i++) {
			if (itemids[i].isFile()) {
				String itemId_s = itemids[i].getName();						
				
				Item item = getItem(new Long(itemId_s));
				
				boolean matched = false;
				
				if(searchby !=null){
					switch (searchby) {
					case BARCODE:
						if(Helper.isNumeric(pattern)){
							if(item.getBarcode() == (new Long(pattern))){
								matched = true;
							}
						}
						break;
					case ITEMTYPE:
						if(item.getItemType() != null){
							if(item.getItemType() == ItemType.getItemType(pattern)){
								matched = true;
							}
						}						
						break;
					case DESCRIPTION:
						if(item.getDescription().toLowerCase().contains(pattern.toLowerCase())){
							matched = true;
						}
						break;
					case QUANTITY:
						if(Helper.isNumeric(pattern)){
							if(item.getQuantity() == new Integer(pattern)){
								matched = true;
							}
						}						
						break;
					case SUPPLIER:
						if(item.getSupplier().equalsIgnoreCase(pattern)){
							matched = true;
						}
						break;
					case ITEMNAME:
						if(item.getItemName().toLowerCase().contains(pattern.toLowerCase())){
							matched = true;
						}
						break;
					}
				}
				else{
					if(item.getDescription().toLowerCase().contains(pattern.toLowerCase())){
						matched = true;
					}
					else if(item.getSupplier().toLowerCase().equalsIgnoreCase(pattern.toLowerCase())){
						matched = true;
					}
					else if(item.getItemName().toLowerCase().contains(pattern.toLowerCase())){
						matched = true;
					}
				}				

				if(matched){
					Item tempitems[] = new Item[items.length+1];
					for(int j=0;j<items.length;j++){
						tempitems[j] = items[j];
					}
					tempitems[items.length] = item;
					items = tempitems;
					tempitems = null;
				}
			}
			
			if(items.length == limit){
				break;
			}
		}

		return items;
	}

	private static Item getItem(long id){
		Item item = null;
		String fileName = item_store_file_path+"/"+id;			
		try {
			File file = new File(fileName);
			
			if(file.exists()){
				FileInputStream fileIn = new FileInputStream(file);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				item = (Item) in.readObject();
				in.close();
				fileIn.close();			
			}
			
		} 
		catch (IOException i) {
			
			System.err.println(i+"IO exception @getItem");
		} 
		catch (ClassNotFoundException c) {
			System.err.println("class not found @getItem");
		}		
		return item;
	}
	
	public static boolean deleteItem(Item item){
		
		boolean deleted =false;
		String fileName = item_store_file_path+"/"+item.getId();			
		File file = new File(fileName);
        if(file.exists()){
        	file.delete();
        	deleted =true;
        }
        return deleted;
	}
	
	public static boolean storeItem(Item item){
		String fileName = item_store_file_path+"/"+item.getId();	
		boolean done = false;
		
		try{			
			File folder = new File(item_store_file_path);
			boolean exist = false;
			if(folder.exists()){
				if(folder.isDirectory()){
					exist = true;
				}				
			}
			if(!exist){
				folder.mkdir();
			}	
							
			File file = new File(fileName);
	        if(file.exists()){
	        	file.delete();
	        }
	        file.createNewFile();
	        FileOutputStream fileOut = new FileOutputStream(fileName);
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(item);
	        done = true;
	        out.close();
	        fileOut.close();	         
	      }
		catch(IOException i){
	          i.printStackTrace();
	    }	
		return done;
	}
}
