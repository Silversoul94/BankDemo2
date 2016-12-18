package com.jensen.util;


public class BankUsers {

	private int id;
	private String pernr;
	private String fname;
	private String lname;
	
	public BankUsers(int id,String lname,String fname, String pernr){
		this.id=id;
		this.pernr=pernr;
		this.fname=fname;
		this.lname=lname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPernr() {
		return pernr;
	}

	public void setPersnr(String pernr) {
		this.pernr = pernr;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	@Override
	public String toString() {
		return "BankUsers [pernr=" + pernr + ", fname=" + fname + ", lname=" + lname + "]";
	}

}
