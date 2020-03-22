
package models.pieces;


import java.util.Map;


public class VisionaryEagle extends AbstractPiece{
	
	private boolean canSwapPosition = true;

	public VisionaryEagle(int x, int y) {
		super(x, y);
	}
	
	
	//not  finish yet
//	public boolean isCanSwapPosition() {
//		return canSwapPosition;
//	}
//
//	public void setCanSwapPosition(boolean canSwapPosition) {
//		this.canSwapPosition = canSwapPosition;
//	}
	

	/*
	 * validate the new position and set it if it's valid
	 * 
	 * @param int newX - new x position
	 * 
	 * @param int newY - new y position
	 * 
	 * @return position valid based on rule ? true : false
	 */
	@Override
	public boolean movePiece(int newX, int newY) {
		
		Map<String, Integer> currentPosition= this.getPosition();
		
		if(newX > currentPosition.get("x") + 2 || newY > currentPosition.get("y") + 2 || 
				newX < currentPosition.get("x") - 2 || newY < currentPosition.get("y") - 2) {
			return false;
		}
		else {
			setPosition(newX, newY);
			return true;
		}
		
		
	}
	
	



}
