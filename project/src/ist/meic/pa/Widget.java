package ist.meic.pa;
import ist.meic.pa.KeywordArgs;

class Widget {
	int width;
	int height;
	int margin;

	@KeywordArgs("width=100,height=50,margin=5")
	public Widget(Object... args) {}

	public String toString() {
		return String.format("width:%s,height:%s,margin:%s",
				width, height, margin);
	}

	public static void main(String[] args) {
		System.err.println(new Widget());
		System.err.println(new Widget("width", 80));
		System.err.println(new Widget("height", 30));
		System.err.println(new Widget("margin", 2));
		System.err.println(new Widget("width", 8, "height", 13, "margin", 21));
	}
}
