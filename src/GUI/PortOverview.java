/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author muro
 */
public class PortOverview extends JPanel {

    /**
     * Creates new form PortOverview
     */
    public PortOverview() {
		this.setSize(new Dimension(500, 700));
		this.setLayout(new BorderLayout());
		
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.CENTER);
		this.add(bottom(), BorderLayout.SOUTH);
		this.assign();
	}
	
	private JPanel top(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("PANEL NAME"));
		return panel;
	}
	
	private JPanel middle(){
		JPanel panel = new JPanel();
		
		return panel;
	}

	private JPanel bottom(){
		JPanel panel = new JPanel();
		
		return panel;
	}
	
	private void assign(){
		
	}
}