import parser.NetParser;


public class Main {

	public static void main(String[] args) {
		NetParser p = new NetParser ("../Red 8-3-1.net", 8, 3, 1);
		
		p.parse();

	}

}
