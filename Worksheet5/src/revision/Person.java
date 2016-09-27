package revision;

public class Person {
	
	public String name;
	public String gender;
	public int age;
	public Person(String name, String gender, int age) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return String.format("DoublyLinkedList [name=%s, gender=%s, age=%s]",
				name, gender, age);
	}
	
	public boolean equals(Person p){
		if(this.getName().equals(p.getName()) &&
				this.getGender().equals(p.getGender()) &&
				this.getAge() == p.getAge() )
			return true;
		else
			return false;
	}
}
