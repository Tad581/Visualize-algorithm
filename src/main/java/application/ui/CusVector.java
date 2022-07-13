package application.ui;

public class CusVector {

	public double x, y;

	public CusVector(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public CusVector(CusVector c) {
		this.x = c.x;
		this.y = c.y;
	}
	
	public static CusVector Zero() {return new CusVector(0.0, 0.0);}
	
	public static CusVector add(CusVector a, CusVector b) {
		return new CusVector(a.x + b.x, a.y + b.y); 
	}
	
	public static CusVector sub(CusVector a, CusVector b) {
		return new CusVector(a.x - b.x, a.y - b.y); 
	}
	
	public void scale(double scale) {
		x *= scale;
		y *= scale;
	}
	
	public static CusVector scalar(CusVector a, float scale) {
		return new CusVector(a.x * scale, a.y * scale); 
	}
	
	public double sqLength() {
		return x*x + y*y;
	}
	
	public double length() {
		return Math.sqrt(sqLength());
	}
	
	public void normalize() {
		double length = length();
		x /= length;
		y /= length;
	}
	
	public double getAlpha() {
		return 180.0 / Math.PI * Math.atan(y/x);
	}
	
	public CusVector getPerpendicular() {
		if(x == 0.0) {
			if(y == 0.0) return null;
			return new CusVector(1.0, 0.0);
		}else {
			if(y == 0.0) return new CusVector(0.0, 1.0);
			return new CusVector(1.0, -x/y);			
		}
	}
}
