/**
 * 
 */
package com.zekelabs.microserviceskafka.util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author edyoda
 *
 */
public class SalesStore {
	private static Map<String,Double> salesUK;;
	private static Map<String,Double> salesFR;
	/**
	 * @return the salesUK
	 */
	public static Map<String, Double> getSalesUK() {
		if(salesUK == null) {
			salesUK = new HashMap<String,Double>();
		}
		return salesUK;
	}
	/**
	 * @param salesUK the salesUK to set
	 */
	public static void setSalesUK(Map<String, Double> salesUK) {
		SalesStore.salesUK = salesUK;
	}
	/**
	 * @return the salesFR
	 */
	public static Map<String, Double> getSalesFR() {
		if(salesFR == null) {
			salesFR = new HashMap<String,Double>();
		}
		return salesFR;
	}
	/**
	 * @param salesFR the salesFR to set
	 */
	public static void setSalesFR(Map<String, Double> salesFR) {
		SalesStore.salesFR = salesFR;
	}

}
