package com.example.touchpoint.objectmodel;

import java.util.*;

import com.example.touchpoint.PresentationElement;

import android.graphics.*;

public class Page {
	
	private List<PresentationElement> elements = new ArrayList<PresentationElement>();
	
	public Page() {
		
	}
	
	public PresentationElement add(PresentationElement object){
		if(elements!=null){
			int index = elements.size();
			elements.add(index, object);
			return elements.get(index);
		}
		return null;
	}
	
	public int getIndex(PresentationElement element){
		if(elements!=null) return elements.indexOf(element);
		return 0;
	}
	
	public void remove(int index){
		if(elements!=null) elements.remove(index);
	}
	
	public int getCount(){
		if(elements!=null)return elements.size();
		return 0;
	}
	
	public PresentationElement getItem(int index){
		if(elements!=null)return elements.get(index);
		return null;
	}
	
	protected void draw(Canvas canvas){
		
	}
}
