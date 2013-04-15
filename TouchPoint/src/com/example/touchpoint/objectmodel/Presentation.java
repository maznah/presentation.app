package com.example.touchpoint.objectmodel;

import java.io.*;
import java.util.*;
import android.graphics.*;

public class Presentation {
	
	private String name = "New Presentation";
	private List<Page> Pages = new ArrayList<Page>();
	
	public Presentation() {
		Pages.add(new Page()); // add one page
	}
	
	public int PagesCount(){
		return Pages.size();
	}
	
	public Page getItem(int index){
		if(Pages!=null) return Pages.get(index);
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Bitmap getThumbnail(){
		return null;
	}
	
	public boolean Save(OutputStream stream){
		return false;
	}
	
	public boolean Load(InputStream stream){
		return false;
	}
}