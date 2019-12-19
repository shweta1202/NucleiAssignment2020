package service;

import exception.InvalidItemTypeException;
import model.Item;
import constants.ItemType;

// todo use constants			-  done
public class CalculateTaxObject {
	ItemType itemType;
	public Item calculateTax (Item item)throws InvalidItemTypeException{
		itemType = ItemType.valueOf(item.getItemType());
		switch (itemType){
		case RAW :
			RawItem obj = new RawItem();
			return obj.calculateTax(item);
		case MANUFACTURED:
			ManufacuredItem obj1 = new ManufacuredItem();
			return obj1.calculateTax(item);
		case IMPORTED:
			ImportedItem obj2 = new ImportedItem();
			return obj2.calculateTax(item); 
		default:
			throw new InvalidItemTypeException("Invalid Type!");
		}
	}
}
