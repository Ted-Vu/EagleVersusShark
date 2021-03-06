package model.board;

import java.io.Serializable;

import model.enumtype.CellType;

/**
 * @author kevin & ted
 *
 */
public class Cell implements Serializable {

	private static final long serialVersionUID = -2509379022301576084L;

	private int x;
	private int y;
	private CellType type;
	private boolean occupied;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		this.type = null;
		this.occupied = false;
	}

	/**
	 * Create the cell with x, y, and cell type - occupied will be false by default.
	 * 
	 * @param x
	 * @param y
	 * @param type
	 */
	public Cell(int x, int y, CellType type) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.occupied = false;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Cell))
			return false;
		Cell other = (Cell) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public boolean getOccupied() {
		return occupied;
	}

	public CellType getType() {
		return type;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		return Integer.parseInt((new Integer(x)).toString() + (new Integer(y)).toString());
	}

	public void setOccupied() {
		this.occupied = true;
	}

	public void setType(CellType type) {
		this.type = type;
	}

	public void setUnoccupied() {
		this.occupied = false;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
