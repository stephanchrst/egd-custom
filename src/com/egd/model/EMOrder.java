package com.egd.model;

import java.util.List;

public class EMOrder {

	String customerPONumber;
	String shipToCustomer;
	String kodeSubdist;
	String customerSubdist;
	String paymentType;
	String statusPO;
	String descStatus;
	String creationDate;
	List<EMOrderLine> linePO;
	
	
	public EMOrder(String customerPONumber) {
		super();
		this.customerPONumber = customerPONumber;
	}

	public List<EMOrderLine> getLinePO() {
		return linePO;
	}

	public void setLinePO(List<EMOrderLine> linePO) {
		this.linePO = linePO;
	}
	
	public void setCustomerPONumber(String customerPONumber) {
		this.customerPONumber = customerPONumber;
	}

	public String getCustomerPONumber() {
		return customerPONumber;
	}
	public String getShipToCustomer() {
		return shipToCustomer;
	}
	public void setShipToCustomer(String shipToCustomer) {
		this.shipToCustomer = shipToCustomer;
	}
	public String getKodeSubdist() {
		return kodeSubdist;
	}
	public void setKodeSubdist(String kodeSubdist) {
		this.kodeSubdist = kodeSubdist;
	}
	public String getCustomerSubdist() {
		return customerSubdist;
	}
	public void setCustomerSubdist(String customerSubdist) {
		this.customerSubdist = customerSubdist;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getStatusPO() {
		return statusPO;
	}
	public void setStatusPO(String statusPO) {
		this.statusPO = statusPO;
	}
	public String getDescStatus() {
		return descStatus;
	}
	public void setDescStatus(String descStatus) {
		this.descStatus = descStatus;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	
	/*
	 {
           "customerPONumber": "22-M-0012113131-BPTEM001",
           "shipToCustomer": "100200257257",
           "kodeSubdist": "TEM001",
           "customerSubdist": "TEST002",
           "paymentType": "Cash",
           "statusPO": "N",
           "descStatus": "Baru",
           "creationDate": "2022/03/04 10:28:45",
           "linePO": [
               {
                   "kodeProduct": "EMOSAC 01",
                   "kodeProdPartner": "AC 01",
                   "quantityPO": 1,
                   "uom": "FLS"
               },
               {
                   "kodeProduct": "EMOSACK",
                   "kodeProdPartner": "ACK",
                   "quantityPO": 1,
                   "uom": "BTL"
               }
           ]
       }
	 */
	
}
