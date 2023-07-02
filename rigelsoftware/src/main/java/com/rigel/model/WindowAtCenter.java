package com.rigel.model;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class WindowAtCenter extends JFrame{
	
//	public static void main(String[] args) {
//		WindowAtCenter windowAtCenter = new WindowAtCenter();
//		windowAtCenter.setVisible(true);
//	}
	
	public WindowAtCenter() {
		setSize(300, 250);
		setTitle("Window at center");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Toolkit toolKit = getToolkit();
		Dimension size = toolKit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
	}
}