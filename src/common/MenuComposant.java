package common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MenuComposant extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * largeur du rectangle
	 */
	private int largeur;

	/**
	 * couleur du rectangle
	 */
	private Color couleur;

	/**
	 * Couleur du rectangle actif
	 */
	private Color couleurActif;

	/**
	 * Couleur du rectangle normale
	 */
	private Color couleurNormal;

	private String texte;

	/**
	 * Constructeur du rectangle
	 * 
	 * @param couleur : la couleur
	 * @param taille  : la taille
	 */
	public MenuComposant(String texte, Color couleurNormal, Color couleurActif, int taille) {
		this.couleur = couleurNormal;
		this.couleurNormal = couleurNormal;
		this.couleurActif = couleurActif;
		this.texte = texte;
		largeur = taille;
		this.setSize(new Dimension(largeur + 10, largeur + 10));
		this.setOpaque(false);
		ajouterListener();
	}

	private void ajouterListener() {
		addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
				couleur = couleurNormal;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				couleur = couleurActif;
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				repaint();

			}

		});
	}

	/**
	 * Surcharge de paintComponent
	 */
	public void paint(Graphics arg0) {
		super.paint(arg0);
		
		Graphics2D g2d = (Graphics2D) arg0;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(couleur);
		g2d.setStroke(new BasicStroke(3));
		
		GradientPaint gradient = new GradientPaint(largeur / 2, 4, couleur, (largeur) / 2, 4 + largeur,
				new Color(255, 255, 255, 200));
		
		g2d.setPaint(gradient);
		g2d.fillRect(4, 4, largeur, largeur);
		
		gradient = new GradientPaint(largeur / 2, 4, Color.white, largeur / 2, 4 + largeur / 2,
				new Color(couleur.getRed(), couleur.getGreen(), couleur.getBlue(), 0));
		
		g2d.setPaint(gradient);
		g2d.fillRect(4 + largeur / 5, 4, 5 * largeur / 8, largeur / 3);
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Calibri", Font.BOLD, 14));
		
		FontMetrics fm = g2d.getFontMetrics();
		
		int x = (this.getWidth() - fm.stringWidth(texte)) / 2;
		int y = (fm.getAscent() + (this.getHeight() - (fm.getAscent() + fm.getDescent())) / 2);
		
		g2d.drawString(texte, x, y);
	}
}
