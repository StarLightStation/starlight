package model;

public class Test05 {

	public static void main(String[] args) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Hello");

		sb.insert(5, " World");	//	띄워쓰기를 포함 한다.
		
		System.out.println(sb.toString() + "\n");
		
		sb.delete(5, 11);	//	띄워쓰기를 포함 한다.
		
		System.out.println(sb.toString() + "\n");
		
		sb.reverse();
		
		System.out.println(sb.toString() + "\n");
		
	}	//	main()

}	//	main class
