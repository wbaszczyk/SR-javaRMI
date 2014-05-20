package agh.sr.rmi.baszczyk.checkers.api;

public enum Color {
	BLACK, WHITE;

	public static Color reverse(Color c) {

		return (c == Color.BLACK) ? Color.WHITE : Color.BLACK;
	}
}
