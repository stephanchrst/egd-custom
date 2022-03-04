package com.egd.model;

public class EMOrderLine {

	String customerPONumber;
	String kodeProduct;
	String kodeProdPartner;
	String quantityPO;
	String uom;
	
	public String getCustomerPONumber() {
		return customerPONumber;
	}
	public void setCustomerPONumber(String customerPONumber) {
		this.customerPONumber = customerPONumber;
	}
	public void setKodeProduct(String kodeProduct) {
		this.kodeProduct = kodeProduct;
	}
	public EMOrderLine(String customerPONumber, String kodeProduct) {
		super();
		this.customerPONumber = customerPONumber;
		this.kodeProduct = kodeProduct;
	}
	public String getKodeProduct() {
		return kodeProduct;
	}
	public String getKodeProdPartner() {
		return kodeProdPartner;
	}
	public void setKodeProdPartner(String kodeProdPartner) {
		this.kodeProdPartner = kodeProdPartner;
	}
	public String getQuantityPO() {
		return quantityPO;
	}
	public void setQuantityPO(String quantityPO) {
		this.quantityPO = quantityPO;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	
	/*
	 {
                   "kodeProduct": "EMOSAC 01",
                   "kodeProdPartner": "AC 01",
                   "quantityPO": 1,
                   "uom": "FLS"
               }, 
	 */
	
}
