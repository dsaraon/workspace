package Ex2;

public class Person {
	
	private String name;
	private String gender;
	private int age;
	
	public Person(String name, String gender, int age){
		this.name = name;
		this.gender = gender;
		this.age = age;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return this.gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return this.age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public boolean equals(Person p2){
		return ( this.getName().equals(p2.getName()) &&
				 this.getGender().equals(p2.getGender()) &&
				 this.getAge() == p2.getAge() );
	}
	
	@Override
	public String toString() {
		return "Person [name=" + getName() + ", gender=" + getGender() + ", age=" + getAge()
				+ "]";
	}
	
	
	
	
	
}
