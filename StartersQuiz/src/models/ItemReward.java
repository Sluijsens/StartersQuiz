package models;

/**
 * Holds info about and item reward.
 * @author Bryan
 *
 */
public class ItemReward {
	private int id;
	private int type;
	private int amount;
	
	/**
	 * Construct an ItemReward with given data
	 * @param id
	 * @param type The type of the given block (For example cobblestone slabs or stone slabs)
	 * @param amount
	 */
	public ItemReward(int id, int type, int amount) {
		this.setId(id);
		this.setType(type);
		this.setAmount(amount);
	}
	
	// Getters
	public int getId() {
		return id;
	}

	public int getType() {
		return type;
	}

	public int getAmount() {
		return amount;
	}
	
	// Setters
	public void setId(int id) {
		this.id = id;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
