package tools;
//////////////////////////////////////////////////////////////////////////////

// file    : Paint.java
// content : basic painting app
//////////////////////////////////////////////////////////////////////////////

/* imports *****************************************************************/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.MouseInputListener;

import common.CircularMenu;
import common.MenuComposant;

/* paint *******************************************************************/

@SuppressWarnings("serial")
class Paint extends JFrame {
	Vector<Shape> shapes = new Vector<>();
	Vector<Color> colors = new Vector<>();
	Color colorSelected = Color.BLACK;

	CircularMenu circularMenu;

	enum toolSelected {
		PEN, RECT, ELLIPSE
	};

	toolSelected toolused;

	class ColorButton extends JButton implements MouseListener {
		String name;
		Color color;

		public ColorButton(String name, int r, int g, int b) {
			this.name = name;
			color = new Color(r, g, b);
			setBackground(color);
			setText("    ");
			addMouseListener(this);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("color selected - " + color);
			colorSelected = color;
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}

	ColorButton[] colorButtons = { new ColorButton("black", 0, 0, 0), new ColorButton("red", 255, 0, 0),
			new ColorButton("green", 0, 255, 0), new ColorButton("blue", 0, 0, 255) };

	class Tool extends AbstractAction implements MouseInputListener {
		Point o;
		Shape shape;

		public Tool(String name) {
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("using tool " + e.getActionCommand());
			toolused = toEnum(e.getActionCommand());
			System.out.println("tools used : " + toolused);
			panel.removeMouseListener(tool);
			panel.removeMouseMotionListener(tool);
			tool = this;
			panel.addMouseListener(tool);
			panel.addMouseMotionListener(tool);
		}

		private toolSelected toEnum(String actionCommand) {
			if (actionCommand.equals("pen")) {
				return toolSelected.PEN;
			} else if (actionCommand.equals("rect")) {
				return toolSelected.RECT;
			} else if (actionCommand.equals("ellipse")) {
				return toolSelected.ELLIPSE;
			} else {
				return null;
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			o = e.getPoint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			shape = null;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}
	}

	Tool tools[] = { new Tool("pen") {
		@Override
		public void mouseDragged(MouseEvent e) {
			Path2D.Double path = (Path2D.Double) shape;
			if (path == null) {
				path = new Path2D.Double();
				path.moveTo(o.getX(), o.getY());
				shapes.add(shape = path);
				colors.add(colorSelected);
			}
			path.lineTo(e.getX(), e.getY());
			panel.repaint();
		}
	}, new Tool("rect") {
		@Override
		public void mouseDragged(MouseEvent e) {
			Rectangle2D.Double rect = (Rectangle2D.Double) shape;
			if (rect == null) {
				rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
				shapes.add(shape = rect);
				colors.add(colorSelected);
			}
			rect.setRect(Math.min(e.getX(), o.getX()), Math.min(e.getY(), o.getY()), Math.abs(e.getX() - o.getX()),
					Math.abs(e.getY() - o.getY()));
			panel.repaint();
		}
	}, new Tool("ellipse") {
		@Override
		public void mouseDragged(MouseEvent e) {
			Ellipse2D.Double ellipse = (Ellipse2D.Double) shape;
			if (ellipse == null) {
				ellipse = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
				shapes.add(shape = ellipse);
				colors.add(colorSelected);
			}
			ellipse.setFrame(Math.min(e.getX(), o.getX()), Math.min(e.getY(), o.getY()), Math.abs(e.getX() - o.getX()),
					Math.abs(e.getY() - o.getY()));
			panel.repaint();
		}
	} };
	Tool tool;

	JPanel panel;

	public Paint(String title) {
		super(title);

		Point pressedPoint = new Point();
		Point releasedPoint = new Point();
		Shape shape2 = null;

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
		add(new JToolBar() {
			{
				for (AbstractAction tool : tools)
					add(tool);
			}
		}, BorderLayout.NORTH);

		add(new JToolBar(SwingConstants.VERTICAL) {
			{
				for (JButton button : colorButtons)
					add(button);
			}
		}, BorderLayout.EAST);

		add(panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g2.setColor(Color.WHITE);
				g2.fillRect(0, 0, getWidth(), getHeight());

				for (int i = 0; i < shapes.size(); i++) {
					g2.setColor(colors.get(i));
					g2.draw(shapes.get(i));
				}
			}
		});
		List<JComponent> items = new ArrayList<JComponent>();
		items.add(new MenuComposant("Tools", new Color(19, 73, 140), new Color(162, 198, 232), 100));
		items.add(new MenuComposant("Color", new Color(19, 73, 140), new Color(162, 198, 232), 100));
		items.add(new MenuComposant("Exit", new Color(19, 73, 140), new Color(162, 198, 232), 100));

		JPanel circularMen = new CircularMenu(new Color(255, 255, 255), 600, 550, 100, 50, items);

		circularMen.setVisible(false);
		add(circularMen);

		pack();
		setVisible(true);

		addMouseListener(new MouseListener() {

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
				} else if (arg0.getButton() == MouseEvent.BUTTON1) {
					pressedPoint.setLocation(arg0.getPoint());

				}

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				circularMen.setVisible(false);
				releasedPoint.setLocation(arg0.getPoint());
				switch (toolused) {
				case PEN:
					System.out.println("released pen, depart :  " + pressedPoint.getLocation() + " arriver : " + releasedPoint.getLocation() );
					Path2D.Double path = (Path2D.Double) shape2;
					if (path == null) {
						path = new Path2D.Double();
						path.moveTo(pressedPoint.getX(), pressedPoint.getY());
						shapes.add( path);
						colors.add(colorSelected);
					}
					path.lineTo(releasedPoint.getX(), releasedPoint.getY());
					panel.repaint();
					
					break;
				case RECT:
					break;
				case ELLIPSE:
					break;
				default:
					break;
				}

			}
		});

	}

	/* main *********************************************************************/

	public static void main(String argv[]) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Paint("Marking menu");
			}
		});
	}
}