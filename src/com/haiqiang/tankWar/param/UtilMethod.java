package com.haiqiang.tankWar.param;

public class UtilMethod {
	public static Parameter.Direction randomDir() {
		Parameter.Direction dir = Parameter.Direction.STOP;
		switch((int)Math.floor(8*Math.random())) {
		case 0 :
			dir = Parameter.Direction.U;
			break;
		case 1 :
			dir = Parameter.Direction.D;
			break;
		case 2 :
			dir = Parameter.Direction.L;
			break;
		case 3 :
			dir = Parameter.Direction.R;
			break;
		case 4 :
			dir = Parameter.Direction.LU;
			break;
		case 5 :
			dir = Parameter.Direction.RU;
			break;
		case 6 :
			dir = Parameter.Direction.LD;
			break;
		case 7 :
			dir = Parameter.Direction.RD;
			break;
		default :
			
		}
		return dir;
	}
}
