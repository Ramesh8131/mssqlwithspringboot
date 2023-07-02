package com.rigel.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class JPlaceholderTextField extends JTextField {

	private String ph;

	public JPlaceholderTextField(String ph) {
		this.ph = ph;
	}
	
	public JPlaceholderTextField() {
		this.ph = null;
	}

	/**
	 * Gets text, returns placeholder if nothing specified
	 */
	@Override
	public String getText() {
		String text = super.getText();

		if (text.trim().length() == 0 && ph != null) {
			text = ph;
		}

		return text;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (super.getText().length() > 0 || ph == null) {
			return;
		}
		
		Graphics2D g2 = (Graphics2D) g;
			   g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
		
			   g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.LIGHT_GRAY);
		g2.drawString(ph, getInsets().left+10, g.getFontMetrics().getMaxAscent() + getInsets().top+5);
	}
}
