package models;

/**
 * 
 * @author Jhusseth
 *
 */
public class Variable {

	/**
	 * Variable of the grammar
	 */
	private String variable;
	
	/**
	 * productions od the grammar
	 */
	private String[] productions;
	
	/**
	 * @Contructor 
	 * @param variable
	 * @param productions
	 */
	public Variable(String variable, String[] productions) {
		this.variable = variable;
		this.productions = productions;
	}
	
	/**
	 * return the variable
	 * @return variable
	 */
	public String getVariable() {
		return variable;
	}
	
	/**
	 * change the variable
	 * @param variable
	 */
	public void setVariable(String variable) {
		this.variable = variable;
	}

	/**
	 * return set of productions
	 * @return productions
	 */
	public String[] getProductions() {
		return productions;
	}

	/**
	 * change the productions
	 * @param producciones
	 */
	public void setProductions(String[] producciones) {
		this.productions = producciones;
	}

	
}
