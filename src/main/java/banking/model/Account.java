package banking.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account implements Serializable {
	
	private static final long serialVersionUID = -4148222890645409354L;
	
	@XmlAttribute
	private Integer id;
	
	@XmlAttribute
	private Customer owner;
	
	@XmlAttribute
	private double balance;

	@XmlAttribute
	private String note;

	public Account() {}
	
	public Account(Integer id, Customer owner, double balance, String note) {
		this.id = id;
		this.owner = owner;
		this.balance = balance;
		this.note = note;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getOwner() {
		return owner;
	}
	public void setOwner(Customer owner) {
		this.owner = owner;
	}

	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + 
				", owner=" + ((owner==null) ? "not known" : owner.getName()) + 
				", balance=" + balance + 
				", note=" + note + 
				"]";
	}
	
}
