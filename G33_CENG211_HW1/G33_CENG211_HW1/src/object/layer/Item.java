package object.layer;

public class Item {
	
	//Item Properties
	private int id;
	private String name;
	private String category;

	public Item() {
		// Default Constructor
		this.id = 0;
		this.name = "0";
		this.category = "0";
	}
	
	public Item(int id, String name, String category) {
		// Parameterized Constructor
		this.id = id;
		this.name = name;
		this.category = category;
	}
	
	public Item (Item item) {
		// Copy Constructor
		this.id = item.getId();
		this.name = item.getName();
		this.category = item.getCategory();
	}
	
	//----------ITEM CLASS GETTER-SETTER METHODS----------//
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
