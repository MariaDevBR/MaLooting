package com.maria.looting.utils;

import java.text.DecimalFormat;

public class Format {

	private static String[] charlist;

	static {
		Format.charlist = new String[] { "K", "M", "B", "T", "Q", "QQ", "S", "SS", "OC", "N", "D", "UN", "DD", "TR",
				"QR", "QQD", "SD", "SSD", "OD", "ND", "VG", "UVG", "DVG", "TVG" };
	}

	public static String format(final double d) {
		final DecimalFormat df = new DecimalFormat("###.###");

		if (d < 1000.0) {
			return df.format(d);

		} else if (d < 1000000.0) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1000.0)))))
					+ Format.charlist[0];

		} else if (d < 1.0E9) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1000000.0)))))
					+ Format.charlist[1];

		} else if (d < 1.0E12) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E9)))))
					+ Format.charlist[2];

		} else if (d < 1.0E15) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E12)))))
					+ Format.charlist[3];

		} else if (d < 1.0E18) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E15)))))
					+ Format.charlist[4];

		} else if (d < 1.0E21) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E18)))))
					+ Format.charlist[5];

		} else if (d < 1.0E24) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E21)))))
					+ Format.charlist[6];

		} else if (d < 1.0E27) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E24)))))
					+ Format.charlist[7];

		} else if (d < 1.0E30) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E27)))))
					+ Format.charlist[8];

		} else if (d < 1.0E33) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E30)))))
					+ Format.charlist[9];

		} else if (d < 1.0E36) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E33)))))
					+ Format.charlist[10];

		} else if (d < 1.0E39) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E36)))))
					+ Format.charlist[11];

		} else if (d < 1.0E42) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E39)))))
					+ Format.charlist[12];

		} else if (d < 1.0E45) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E42)))))
					+ Format.charlist[13];

		} else if (d < 1.0E48) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E45)))))
					+ Format.charlist[14];

		} else if (d < 1.0E51) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E48)))))
					+ Format.charlist[15];

		} else if (d < 1.0E54) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E51)))))
					+ Format.charlist[16];

		} else if (d < 1.0E57) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E54)))))
					+ Format.charlist[17];

		} else if (d < 1.0E60) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E57)))))
					+ Format.charlist[18];

		} else if (d < 1.0E63) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E60)))))
					+ Format.charlist[19];

		} else if (d < 1.0E66) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E63)))))
					+ Format.charlist[20];

		} else if (d < 1.0E69) {
			return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E66)))))
					+ Format.charlist[21];

		}
		return String.valueOf(String.valueOf(String.valueOf(String.valueOf(df.format(d / 1.0E69)))))
				+ Format.charlist[22];
	}

}
