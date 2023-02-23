package by.htp.ex.bean;

import java.io.Serializable;
import java.util.Objects;

public class NewUserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private int id;
	private String password;
	private String role;

	public NewUserInfo() {
	}

	public NewUserInfo(String email, int id, String password, String role) {
		this.email = email;
		this.id = id;
		this.password = password;
		this.role = role;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;

	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;

	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, password, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewUserInfo other = (NewUserInfo) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(password, other.password)
				&& Objects.equals(role, other.role);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [email=" + email + ", id=" + id + ", password=" + password + ", role="
				+ role + "]";
	}

}
