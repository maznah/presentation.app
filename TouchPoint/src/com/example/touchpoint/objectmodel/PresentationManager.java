package com.example.touchpoint.objectmodel;

import java.util.*;
import android.content.*;

public class PresentationManager {
	
	private Context context = null;
	private List<Presentation> list = new ArrayList<Presentation>();

	public PresentationManager(Context context) {
		this.context = context;
	}

	public Presentation create(Context context) {
		this.context = context;
		if(list!=null){
			int index = list.size();
			list.add(new Presentation(this));
			return list.get(index);
		}
		return null;
	}

}
