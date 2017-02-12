package fr.epita.iamcore.datamodel;

public class Identity {
	
	private String Uid;
	private String DisplayName;
	private String Email;
	
	
	public Identity(String uid, String DisplayName,String Email)  {
		this.Uid = uid;
		this.DisplayName = DisplayName;
		this.Email = Email;
		
	}
	
	public String getUid()  {
		return Uid;
	}
	
	public void setUid(String uid)  {
		this.Uid = uid;
	}
	
	public String getDisplayName()  {
		return DisplayName;
	}
	
	public void setDisplayName(String DisplayName)  {
		this.DisplayName = DisplayName;
	}
	
	public String getEmail()  {
		return Email;
	}
	
	public void setEmail(String Email)  {
		this.Email = Email;
	}
	
	public String toString()  {
		return "Identity [uid=" + Uid + ", DisplayName=" + DisplayName + ", Email=" +Email + "]";
	}
	
	

}
