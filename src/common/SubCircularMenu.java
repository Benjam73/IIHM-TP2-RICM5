package common;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SubCircularMenu extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5202002727889793234L;

	List<JComponent> items;

	private int taille;

	private int rayon;

	private Color couleur;

	public SubCircularMenu(Color couleur, int width, int height, int rayon, int taille, List<JComponent> items) {
		this.couleur = couleur;
		this.items = items;
		this.taille = taille;
		this.rayon = rayon;
		setSize(width, height);
		build();
		setInvisible();
	}

	public void addItem(JComponent newItem) {
		items.add(newItem);
	}

	private void build() {
		if (items != null && items.size() > 0) {
			setLayout(null);
			double angle = 2 * Math.PI / (8.);
			int xCentre = getWidth() / 2;
			int yCentre = getHeight() / 2;
			for (int i = items.size() - 1; i >= 0; i--) {
				items.get(i).setBounds((int) (xCentre - taille / 2 + rayon * Math.cos(i * angle - Math.PI / 2)),
						(int) (yCentre - taille / 2 + rayon * Math.sin(i * angle - Math.PI / 2)), taille + 10,
						taille + 10);
				add(items.get(i));
			}

		}
	}

	public void setVisible() {
		setVisible(true);
	}
	
	public void setInvisible() {
		setVisible(false);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gradient = new GradientPaint(new Point(0, 0), couleur, new Point(getWidth(), getHeight()),
				couleur);
		g2d.setPaint(gradient);
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}

	
	
	
}
