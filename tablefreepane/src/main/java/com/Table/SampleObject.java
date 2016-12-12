package com.Table;

import com.util.EntityCommonInterface;

public class SampleObject implements Comparable<SampleObject>,EntityCommonInterface{
	
	String header1;
	String header2;
	String header3;
	String header4;
	String header5;
	String header6;
	String header7;
//	String header8;
//	String header9;
	
	public SampleObject(String header1, String header2, String header3,
			String header4, String header5, String header6,
			String header7, String header8, String header9){
		
		this.header1 = header1;
		this.header2 = header2;
		this.header3 = header3;
		this.header4 = header4;
		this.header5 = header5;
		this.header6 = header6;
		this.header7 = header7;
//		this.header8 = header8;
//		this.header9 = header9;
		
	}

	public String getHeader1() {
		return header1;
	}

	public void setHeader1(String header1) {
		this.header1 = header1;
	}

	public String getHeader2() {
		return header2;
	}

	public void setHeader2(String header2) {
		this.header2 = header2;
	}

	public String getHeader3() {
		return header3;
	}

	public void setHeader3(String header3) {
		this.header3 = header3;
	}

	public String getHeader4() {
		return header4;
	}

	public void setHeader4(String header4) {
		this.header4 = header4;
	}

	public String getHeader5() {
		return header5;
	}

	public void setHeader5(String header5) {
		this.header5 = header5;
	}

	public String getHeader6() {
		return header6;
	}

	public void setHeader6(String header6) {
		this.header6 = header6;
	}

	public String getHeader7() {
		return header7;
	}

	public void setHeader7(String header7) {
		this.header7 = header7;
	}

	@Override
	public int compareTo(SampleObject another) {
		if(getHeader1().equals("haha3haha1")) {
			return Integer.parseInt(this.getHeader1()) - Integer.parseInt(another.getHeader1());
		}else{
			return -(Integer.parseInt(this.getHeader1()) - Integer.parseInt(another.getHeader1()));
		}
	}

	@Override
	public String getElement(int i) {
		return null;
	}

	@Override
	public int getTotalNum() {
		return 0;
	}
}
