package assignment2;
import java.util.Scanner;

public class ShapeTest {
	
public interface Shape {
	//abstract method re-defined by subclasses
	public abstract String getName();
	
	//shape area, default 0 
	public default double getArea() {return 0;}
	
	//shape volume, default 0
	public default double getVolume() {return 0;}

} //end class shape

public class Point implements Shape { //extends mean "inherits from"
	private int x; private int y; //its co-ordinates
	
	public Point() {
		x =0; y=0;
		}
	public Point(int xval, int yval) {
		x = xval; y = yval;
		}
	
	//get and set methods for the co-ordinates
	public int getX() {return x;}
	public int getY() {return y;}
	public void setX(int xval) {x = xval;}
	public void setY(int yval) {y = yval;}
	
	//implements the Shape abstract method getName 
	public String getName() {return "Point";}
	
	//redefines the Object Method toString (Shape inherits from Object)
	
	public String toString() {return "[" + x +  ",," + y +"]";}
}
public class Circle extends Point {
	private double r; //its radius 
	
	public Circle() {super(); r=0;}
	public Circle(int x, int y, double r) {super(x,y); setRadius(r);}
	
	//get and set methods for the radius 
	public double getRadius() {return r;}
	public void setRadius(double rval) {r = rval < 0 ? 0:rval;}
	
	//redefines the Shape method getArea 
	public double getArea() {return Math.PI*r*r;}
	
	//redefines getName 
	public String getName() {return "Circle";}
	
	//redefines to String and uses the superclass toString implementation 
	public String toString() {return "C="+super.toString()+";R="+r;}

}

public class Cylinder extends Circle {
	private double h; //its height 
	
	public Cylinder() {super(); h = 0;}
	public Cylinder(int x, int y, double r, double h) {super(x,y,r); setHeight(h);}
	
	//get and set method for the height 
	public double getHeight() {return h;}
	public void setHeight(double hval) {h = hval < 0 ? 0:hval;}
	
	//redefines getArea 
	public double getArea() {return 2*super.getArea()+2*Math.PI*getRadius()*h;}
	
	//redefines the Shape Method getVolume 
	public double getVolume() {return super.getArea()*h;}
	
	//redefines getName 
	public String getName() {return "Cylinder";}
	
	//redefines toString and uses the superclass toString implementation 
	public String toString() {return super.toString()+ ";H="+h;}
}


		Point[] shapes = new Point[100];
		int count = 0;
		Scanner input = new Scanner(System.in);
		static ShapeTest st = new ShapeTest();
		
		//main function
		public static void main(String[] args) {
			st.menu();
		}
		// print method defined below 
		public void Print() {
			for (int i = 0; i < count; i++) {
				System.out.printf(i + 1 + "." + shapes[i].getName() + "\n");
			}
			System.out.println(count + 1 + "." + "Back to Main Menu");
		}
		public void PrintPrevious() {
			System.out.println("The previous shapes created:\n");
			for (int z = 0; z < count; z++) {
				System.out.printf(z + 1 + "." + shapes[z].getName() + "\n");
			}
		}
		// main menu method defined below 
		public void menu() {
			boolean flag = true;
			//Transient variable
			int x = 0;
			int y = 0;
			double r = 0;
			double h = 0;
			do { //user interface
				System.out.println("\nPlease enter a number to select one of the following functions: ");
				System.out.println("------------------------------------------------------------------");
				System.out.println("1.Create a Point");
				System.out.println("2.Create a Circle");
				System.out.println("3.Create a Cylinder ");
				System.out.println("4.Information for a specific shape");
				System.out.println("5.Shapes created previously");
				System.out.println("6.Exit");
				System.out.println("------------------------------------------------------------------");

				//Option input 
				int choice = input.nextInt();		 
				switch (choice) {
				
				//If user chooses option 1
				case 1:
					Point point = new Point();
					System.out.println("Please enter the x co-ordinates:");
					try {
						x = input.nextInt();
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Error!");
						st.menu();
					}
					System.out.println("Please enter the y co-ordinates");
					try {
						y = input.nextInt();
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Error! Inputted wrong data type");
						st.menu();
					}
					point.setX(x);
					point.setY(y);
					shapes[count] = point;
					count++;
					System.out.println("Data Stored Sucessfully! \n");
					break;
					
				//If user chooses option 2
				case 2:
					System.out.println("Please enter the x co-ordinates");
					try {
						x = input.nextInt();
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Entered something wrong, please re-enter!");
						st.menu();
					}
					System.out.println("Please enter the y coordinates \n");
					try {
						y = input.nextInt();
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Entered something wrong, please re-enter! ");
						st.menu();
					}
					System.out.println("Please enter the radius of the circle");

					try {
						r = input.nextDouble();
						if(r < 0 )
							throw new Exception("The radius cannot be negative. Positive value required");
						if(r == 0 )
							throw new Exception("The radius cannot be zero. Positive value required");
					} 
					catch (Exception e) {
						// TODO: handle exception
						System.out.println("Error: " + e.getMessage());
						System.out.println("Wrong input, please re-enter!");
						st.menu();
					}
					Point circle = new Circle(x, y, r);
					shapes[count] = circle;
					count++;
					System.out.println("Data stored successfully! \n");
					break;
				//If user chooses option 3
				case 3:
					try {
						System.out.println("Please enter x co-ordinates:");
						x = input.nextInt();
						System.out.println("Please enter y co-ordinates");
						y = input.nextInt();
						System.out.println("Please enter radius of the circle:");
						r = input.nextDouble();
						if(r < 0 )
							throw new Exception("The radius cannot be negative. Positive value required");
						if(r == 0 )
							throw new Exception("The radius cannot be zero. Positive value required");
						System.out.println("Please enter height of cylinder");
						h = input.nextDouble();
						if(h < 0 )
							throw new Exception("The height cannot be negative. Positive value required");
						if(h == 0 )
							throw new Exception("The height cannot be zero. Positive value required");
						Point cylinder = new Cylinder(x, y, r, h);
						shapes[count] = cylinder;
						count++;
						System.out.println("Shape stored successfully! \n");
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Entered something wrong, Please re-enter");
						System.out.println("Error: " + e.getMessage());
						st.menu();
						break;
					}

					break;
				//If user chooses option 4
				case 4:
					if (count==0) {
						System.out.println("There is no data entered in the system, no shape information found! \n");
					}
					else {
						st.subMenu();
						flag=false;
					}
					break;
				//If user chooses option 5
				case 5:
					if (count==0) {
						System.out.println("There is no data entered in the system, no shape information found! \n");
					break;
					}
					else st.PrintPrevious();
					break;

				case 6:
					flag=st.exit();
					break;
				default:
					System.out.println("Entered something wrong, please re-enter");
					st.menu();
					break;
				}
				input = new Scanner(System.in);
			
			} while (flag);
		}
		// sub-menu defined below: access more info on your stored shapes
		public void subMenu(){
			boolean flag = true;
			do {		
				st.Print();
				System.out.println("\nPlease enter a number to select one of the following shapes:");
				int series = input.nextInt();
				if(series == (count + 1))
					st.menu();
				System.out.println("\nPlease enter a number for the details of the shape selected: ");
				System.out.println("1.Name of Shape");
				System.out.println("2.Area of Shape");
				System.out.println("3.Volume of Shape");
				System.out.println("4.Back to Main Menu");
				System.out.println("5.Exit");
				String choice = input.next();

				switch (choice) {
				//If user chooses option 1
				case "1":
					System.out.printf(shapes[series-1].getName());
					System.out.printf("\n");
					break;
				//If user chooses option 2
				case "2":
					System.out.println(shapes[series-1].getArea());
					System.out.printf("\n");
					break;
				//If user chooses option 3
				case "3":
					System.out.println(shapes[series-1].getVolume());
					System.out.printf("\n");
					break;
				//If user chooses option 4
				case "4":
					System.out.printf("\n");
					st.menu();
					break;
				//If user chooses option 5
				case "5":
					System.out.printf("\n");
					flag = st.exit();
					break;
				default:
					System.out.println("Typed something wrong..!");
					st.menu();
					break;
				}
			} while (flag);

		} 
		
		// exit method defined below 
		public boolean exit() {
			System.out.println("Are you sure you want to quit? The program will be exited.");
			System.out.println("Press 1. to Cancel or 2. to Exitˆ");
			String choice = input.next();
			if (choice.equals("1")) {
				return true;
			}
			if (choice.equals("2")) {

				return false;

			} else {

				System.out.println("Error!");
				return true;
			}
		}
	}

