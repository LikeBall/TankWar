package com.haiqiang.tankWar.param;

public interface Parameter {
	public static final int FRAME_WIDTH = 800;
	public static final int FRAME_HEIGHT = 600;
	public static final int X_SPEED = 5;
	public static final int Y_SPEED = 5;
	public static final int T_WIDTH = 20;
	public static final int T_HEIGTH = 20;
	public static final int M_WIDTH = 5;
	public static final int M_HEIGTH = 5;
	public enum Direction {U, D, L, R, LU, RU, LD, RD, STOP};
}
