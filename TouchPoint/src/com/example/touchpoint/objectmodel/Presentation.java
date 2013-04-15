package com.example.touchpoint.objectmodel;

import java.io.*;
import java.util.*;

import com.example.touchpoint.Globals;

import android.graphics.*;

public class Presentation {
	
	private Globals global;
	private PresentationManager host;
	private String name = "New Presentation";
	private List<Page> Pages = new ArrayList<Page>();
	
	public Presentation(PresentationManager manager) {
		this.host = manager;
		global = new Globals();
		
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

	public void activate() {
		if(host!=null){
			global.currentPresentation = this;
		}
	}
}