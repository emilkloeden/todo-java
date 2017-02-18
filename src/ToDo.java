
public class ToDo {
	private String name;
	private String description;
	private boolean complete;
	
	public ToDo(String name, String description) {
		this.setName(name);
		this.setDescription(description);
		this.setComplete(false);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	
	public String toString() {
		String completeString = complete ? " {DONE}" : "";
		return "TODO:" + completeString + "\n\t" + name + "\n\t" + description; 
	}

	
	
}
