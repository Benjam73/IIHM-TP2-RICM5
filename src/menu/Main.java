package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import common.*;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new BorderLayout());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		List<JComponent> items = new ArrayList<JComponent>();
		items.add(new MenuComposant("Menu1", new Color(19, 73, 140), new Color(162, 198, 232), 100));
		items.add(new MenuComposant("Menu2", new Color(19, 73, 140), new Color(162, 198, 232), 100));
		items.add(new MenuComposant("Menu3", new Color(19, 73, 140), new Color(162, 198, 232), 100));
		items.add(new MenuComposant("Menu4", new Color(19, 73, 140), new Color(162, 198, 232), 100));
//		items.add(new MenuComposant("Menu5", new Color(19, 73, 140), new Color(162, 198, 232), 100));
//		items.add(new MenuComposant("Menu6", new Color(19, 73, 140), new Color(162, 198, 232), 100));
//		items.add(new MenuComposant("Menu7", new Color(19, 73, 140), new Color(162, 198, 232), 100));
//		items.add(new MenuComposant("Menu8", new Color(19, 73, 140), new Color(162, 198, 232), 100));
//		items.add(new MenuComposant("Menu9", new Color(19, 73, 140), new Color(162, 198, 232), 100));
//		items.add(new MenuComposant("Menu10", new Color(19, 73, 140), new Color(162, 198, 232), 100));
//		items.add(new MenuComposant("Menu11", new Color(19, 73, 140), new Color(162, 198, 232), 100));
		f.setSize(600, 600);

		f.setLocationRelativeTo(null);
		
		JPanel circularMen = new CircularMenu(new Color(255, 255, 255), 600, 550, 200, 50, items);
		
		f.add(circularMen, BorderLayout.CENTER);
		
		circularMen.setVisible(false);
		
		
		f.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent me) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON3) {
					System.out.println("coucou");
					circularMen.setVisible(true);
					
					f.setVisible(true);

				} 				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				circularMen.setVisible(false);
				
			}
		});

		f.setVisible(true);

	}

}