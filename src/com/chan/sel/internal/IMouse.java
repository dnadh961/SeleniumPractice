package com.chan.sel.internal;

/**
 * 
 * @author Chanu Vempati
 *
 */
public interface IMouse
{

	public enum Speed
	{
		FAST(1), MEDIUM(2), SLOW(6), VERYSLOW(25);

		private final int delay;

		Speed(int delay)
		{
			this.delay = delay;
		}

		public int getDelay()
		{
			return delay;
		}
	}
	
	void click();

	void doubleClick();

	void rightClick();

	void pressLeftButton();

	void releaseLeftButton();

	void moveTo(int xEnd, int yEnd);

	void setSpeed(Speed speed);

	Speed getSpeed();

	void moveRelative(int x, int y);
	
	void mouseWheel(int wheelAmt);

}
