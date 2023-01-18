package br.com.crudproduto.verifications;

public class numericValidator {
	
	public static boolean isNumeric(String num) {
		
		Long n;
		
		try {
			n = Long.parseLong(num);
		}
		catch(NumberFormatException ex) {
			return false;
		}
		
		return true;
	}
}
