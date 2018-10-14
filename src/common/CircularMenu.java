package common;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

import javax.swing.JPanel;

public class CircularMenu extends JPanel {

	private static final long serialVersionUID = 8334369610498563446L;

	/**
	 * La liste des items du menu
	 */
	private List<MenuComposant> items;

	/**
	 * La taille d'un rectangle du menu
	 */
	private int taille;

	/**
	 * Le rayon séparant les rectangles
	 */
	private int rayon;

	/**
	 * La couleur de fond du menu
	 */
	private Color couleur;

	public CircularMenu(Color couleur, int width, int height, int rayon, int taille, List<MenuComposant> items) {
		this.couleur = couleur;
		this.items = items;
		this.taille = taille;
		this.rayon = rayon;
		setSize(width, height);
		build();
	}

	/**
	 * Cette procédure permet de placer les rectangles sur le menu
	 */
	private void build() {
		if (items != null && items.size() > 0) {
			setLayout(null);
			double angle = 2 * Math.PI / (8.);
			int xCentre = getWidth() / 2;
			int yCentre = getHeight() / 2;
			if (items.size() < 9) {
				for (int i = items.size() - 1; i >= 0; i--) {
					items.get(i).setBounds((int) (xCentre - taille / 2 + rayon * Math.cos(i * angle - Math.PI / 2)),
							(int) (yCentre - taille / 2 + rayon * Math.sin(i * angle - Math.PI / 2)), taille + 10,
							taille + 10);
					add(items.get(i));
				}
			} else {
				for (int i = 7; i >= 0; i--) {
					items.get(i).setBounds((int) (xCentre - taille / 2 + rayon * Math.cos(i * angle - Math.PI / 2)),
							(int) (yCentre - taille / 2 + rayon * Math.sin(i * angle - Math.PI / 2)), taille + 10,
							taille + 10);
					add(items.get(i));
				}
				int shift = 1;
				for (int i = items.size() -1; i > 7; i--,shift+=2) {
					items.get(i).setBounds((int) (xCentre - taille / 2 + rayon * Math.cos(4 * angle - Math.PI / 2)),
							(int) (yCentre - taille / 2 + rayon * Math.sin(4 * angle - Math.PI / 2)) + taille + (taille*shift) , taille + 10,
							taille + 10);
					add(items.get(i));
				}
			}
		}
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