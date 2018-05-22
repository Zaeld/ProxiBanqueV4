package fr.gtm.pbsi.domain;

public abstract class People {
	private String name;
	private String firstName;
	
	public People() {
		super();
	}
	
	public People(String name, String firstName) {
		super();
		this.name = name;
		this.firstName = firstName;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Override
	public String toString() {
		return "People [name=" + name + ", firstName=" + firstName + "]";
	}
	
}
