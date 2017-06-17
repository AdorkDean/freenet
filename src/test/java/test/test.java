package test;

public class test {
	{
		System.out.println("yyy");
	}
	// public static test test1;
	static {
		System.out.println("hhh");
	}

	public static void main(String[] args) {
		test test1 = new test();
		System.out.println("xxx");
		System.out.println(null != "");
	}
}