package com.example.touchpoint;

import com.example.touchpoint.objectmodel.*;
import com.example.touchpoint.objectmodel.Presentation;

import android.app.*;

public class Globals extends Application {
	
	public final PresentationManager presentations  = new PresentationManager(this);
	public Presentation currentPresentation = null;

}
