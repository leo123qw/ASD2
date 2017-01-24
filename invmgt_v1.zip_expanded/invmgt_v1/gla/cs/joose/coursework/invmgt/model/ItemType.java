package gla.cs.joose.coursework.invmgt.model;

/**
 *
 * @author inah Omoronyia
 */

import java.io.Serializable;

public enum ItemType  implements Serializable{
	ELECTRONIC, OUTDOOR, HEALTH, CLOTH, BOOK, CAR, GARDEN, OTHERS;

	public static String getTypeDesc(ItemType type){
		String  typedesc = null;
		
		if(type != null){
			switch (type) {
			case ELECTRONIC:
				typedesc = "electronic";
				break;
			case OUTDOOR:
				typedesc = "outdoor";
				break;
			case HEALTH:
				typedesc = "health";
				break;
			case CLOTH:
				typedesc = "cloth";
				break;
			case BOOK:
				typedesc = "book";
				break;
			case CAR:
				typedesc = "car";
				break;
			case GARDEN:
				typedesc = "garden";
				break;
			case OTHERS:
				typedesc = "others";
				break;
			}
		}
		return typedesc;
	}
	
	public static ItemType getItemType(String desc){
		
		if(desc.contains(getTypeDesc(ItemType.BOOK))){
			return ItemType.BOOK;
		}
		else if(desc.contains(getTypeDesc(ItemType.ELECTRONIC))){
			return ItemType.ELECTRONIC;
		}
		else if(desc.contains(getTypeDesc(ItemType.OUTDOOR))){
			return ItemType.OUTDOOR;
		}
		else if(desc.contains(getTypeDesc(ItemType.HEALTH))){
			return ItemType.HEALTH;
		}
		else if(desc.contains(getTypeDesc(ItemType.CLOTH))){
			return ItemType.CLOTH;
		}
		else if(desc.contains(getTypeDesc(ItemType.CAR))){
			return ItemType.CAR;
		}
		else if(desc.contains(getTypeDesc(ItemType.GARDEN))){
			return ItemType.GARDEN;
		}
		else if(desc.contains(getTypeDesc(ItemType.OTHERS))){
			return ItemType.OTHERS;
		}
		
		return null;
	}
}