package controllers;

import models.Algorithm;
import models.Variable;

/**
 * 
 * @author Jhusseth
 *
 */
public class AlgorithmController {
	
	/**
	 * link to model
	 */
	private Algorithm model;
	
	
	/**
	 * @Contructor
	 */
	public AlgorithmController() {
		model = new Algorithm();
	}
	
	/**
	 * 
	 * @param variables
	 * @param cadena
	 * @return
	 */
	public String[][] getTheGereationMatrix(Variable[] variables, String cadena){
		
		return model.generatesMatrix(variables, cadena);
	}

}
