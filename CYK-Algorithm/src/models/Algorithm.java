package models;

/**
 * 
 * @author Jhusseth
 *
 */
public class Algorithm {

	/**
	* @Description This method generates a CYK matrix from the variables and the
	* string that receives as parameter.
	* @param variables variables that are part of the grammar
	* @param string string on which the CYK array will be built
	* @return matrixCYK result of CYK algorithm applied to variables and
	* input string, null if input string is empty
	*/
	public String[][] generatesMatrix(Variable[] variables, String cadena) {
		
		String[][] matrixCYK = new String[cadena.length()][cadena.length()];
		if (cadena.length() == 0)
			return null;
		fillFirstColumn(matrixCYK, variables, cadena);
		fillColumns(matrixCYK, variables, cadena);
		
		return matrixCYK;
		
	}

	/**
	 * @Description This method fills the first column
	 * @param matrixCYK
	 * @param variables
	 * @param cadena
	 */
	private void fillFirstColumn(String[][] matrixCYK, Variable[] variables, String cadena) {
		
		char[] characters = cadena.toCharArray();
		for (int i = 0; i < characters.length; i++) {
			matrixCYK[i][0] = "";
			for (Variable v : variables) {
				boolean wasAdded = false;
				for (String production : v.getProductions()) {
					if (production.equals(characters[i] + "") && !wasAdded) {
						matrixCYK[i][0] += v.getVariable() + ",";
						wasAdded = true;
					}
				}
			}
			if (matrixCYK[i][0] != null && !matrixCYK[i][0].trim().equals("")) {
				matrixCYK[i][0] = matrixCYK[i][0].substring(0, matrixCYK[i][0].length() - 1);
			}
		}
	}

	/**
	 * @Description This method fills the other columns
	 * @param matrixCYK
	 * @param variables
	 * @param cadena
	 */
	private void fillColumns(String[][] matrixCYK, Variable[] variables, String cadena) {
		for (int j = 2; j <= cadena.length(); j++) {
			for (int i = 1; i <= cadena.length() - j +1; i++) {
				matrixCYK[i-1][j-1] = "";
				for (int k = 1; k <j; k++) {
					matrixCYK[i-1][j-1] += product(matrixCYK[i-1][k-1], matrixCYK[i+k-1][j-1-k]) + ",";
				}
				if (matrixCYK[i-1][j-1] != null && !matrixCYK[i-1][j-1].trim().equals("")) {
					matrixCYK[i-1][j-1] = matrixCYK[i-1][j-1].substring(0, matrixCYK[i-1][j-1].length() - 1);
				}
				replaceVariables(matrixCYK, variables, i-1, j-1);
			}
		}
	}

	/**
	 * @Description This method replace variables with the new productions 
	 * @param matrixCYK
	 * @param variables
	 * @param row
	 * @param column
	 */
	private void replaceVariables(String[][] matrixCYK, Variable[] variables, int row, int column) {
		
		String[] prod = matrixCYK[row][column].split(",");
		matrixCYK[row][column] = "";
		for (int i = 0; i < prod.length; i++) {
			for (Variable v : variables) {
				boolean wasAdded = false;
				String[] previous = matrixCYK[row][column].split(",");
				for (int j = 0; j < previous.length; j++) {
					if(v.getVariable().equals(previous[j]))
						wasAdded = true;
				}
				for (String productions : v.getProductions()) {
					if (productions.equals(prod[i] + "") && !wasAdded) {
						matrixCYK[row][column] += v.getVariable() + ",";
						wasAdded = true;
					}
				}
			}
		}
		if (matrixCYK[row][column] != null && !matrixCYK[row][column].trim().equals("")) {
			matrixCYK[row][column] = matrixCYK[row][column].substring(0, matrixCYK[row][column].length() - 1);
		}
	}

	/**
	 * @Description 
	 * @param a
	 * @param b
	 * @return product 
	 */
	private String product(String a, String b) {
		
		String product = "";
		if(a.equals("") || b.equals("")) {
			return product;
		}
		if (a.split(",").length > 1 || b.split(",").length > 1) {
			String[] aux_a = a.split(",");
			String[] aux_b = b.split(",");
			for (String a1 : aux_a) {
				for (String b1 : aux_b) {
					product += a1 + b1 + ",";
				}
			}
			product = product.substring(0, product.length() - 1);
		}
		else {
			product = a + b;
		}
		
		return product;
	}
}
