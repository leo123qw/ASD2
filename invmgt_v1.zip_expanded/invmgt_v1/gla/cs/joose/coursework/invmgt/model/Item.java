package gla.cs.joose.coursework.invmgt.model;

/**
 *
 * @author inah Omoronyia
 */

import java.io.Serializable;
import gla.cs.joose.coursework.invmgt.util.Util;

public class Item implements Serializable{
	private static final long serialVersionUID = 4942418270209975417L;
	
	private long id;
	private long barcode; //99 .. 9999
	private String itemName;
	private int quantity; //0 ...100
	private String supplier;
	private ItemType itemType;
	private String description;
	
	public Item(){
		
	}
	
	public Item(long barcode, String itemName, ItemType itemType, int qty, String supplier, String desc){
		this.barcode = barcode;
		this.itemName = itemName;
		this.setItemType(itemType);
		this.quantity = qty;
		this.supplier = supplier;
		this.description = desc;
		this.setId(Util.random.nextLong());
	}
	
	public long getBarcode() {
		return barcode;
	}
	public void setBarcode(long barcode) {
		this.barcode = barcode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	
	@Override
	public String toString(){
		return barcode+":"+itemName+":"+quantity+":"+supplier+":"+itemType;
	}
	
	@Override
	public boolean equals(Object object){
		boolean same = false;
		
		if(object instanceof Item){
			if(barcode == ((Item)object).getBarcode()){
				if(itemName.equals(((Item) object).getItemName())){
					if(quantity == ((Item)object).getQuantity()){
						if(supplier.equals(((Item) object).getSupplier())){
							if(ItemType.getTypeDesc(itemType).equals(ItemType.getTypeDesc(((Item) object).getItemType()))){
								same = true;
							}							
						}
					}
				}					
			}
		}
		return same;
	}

}
