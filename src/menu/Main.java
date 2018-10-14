package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import common.*;


public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new BorderLayout());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		List<MenuComposant> items = new ArrayList<MenuComposant>();
		items.add(new MenuComposant("Menu1", new Color(19, 73, 140), new Color(162, 198, 232), 100));
		items.add(new MenuComposant("Menu2", new Color(19, 73, 140), new Color(162, 198, 232), 100));
		items.add(new MenuComposant("Menu3", new Color(19, 73, 140), new Color(162, 198, 232), 100));
		items.add(new MenuComposant("Menu4", new Color(19, 73, 140), new Color(162, 198, 232), 100));
		items.add(new MenuComposant("Menu5", new Color(19, 73, 140), new Color(162, 198, 232), 100));
		items.add(new MenuComposant("Menu6", new Color(19, 73, 140), new Color(162, 198, 232), 100));
		items.add(new MenuComposant("Menu7", new Color(19, 73, 140), new Color(162, 198, 232), 100));
		items.add(new MenuComposant("Menu8", new Color(19, 73, 140), new Color(162, 198, 232), 100));
		items.add(new MenuComposant("Menu9", new Color(19, 73, 140), new Color(162, 198, 232), 100));
		items.add(new MenuComposant("Menu10", new Color(19, 73, 140), new Color(162, 198, 232), 100));
		items.add(new MenuComposant("Menu11", new Color(19, 73, 140), new Color(162, 198, 232), 100));
		f.setSize(600, 600);
		f.add(new CircularMenu(new Color(255, 255, 255), 600, 550, 200, 50, items), BorderLayout.CENTER);
		
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

}