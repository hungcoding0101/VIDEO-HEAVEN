package com.hung.Model;

public class VIDCounter {
	
		private static int currentVID;

		public static int getCurrentVID() {
			return currentVID;
		}

		public static void setCurrentVID(int VID) {
			currentVID = VID;
		}
		
		public static void increment() {
			++currentVID;
		}
		
}
