package com.egd.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.X_Z_TempSalesOrder;
import org.compiere.model.X_Z_TempSalesOrderLine;
import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;

import com.egd.utils.ClientUtil;

public class ImportOrderEMOS extends SvrProcess{

	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (ImportOrderEMOS.class);
	
	@Override
	protected void prepare() {
		
	}

	@Override
	protected String doIt() throws Exception {
		
		int C_DocType_ID = ClientUtil.getSO_C_DocType_ID();
		if(C_DocType_ID<=0)
			throw new AdempiereException(ClientUtil.ERROR_MSG_SO_DOCTYPE_NULL);
		
		int countHeader = 0;
		int countLine = 0;
		for (Integer tempOrderID : getListTempOrderID()) {
			countHeader = countHeader+1;
			X_Z_TempSalesOrder tempOrder = new X_Z_TempSalesOrder(getCtx(), tempOrderID, get_TrxName());
			
			if(tempOrder.getCreationDate() == null || tempOrder.getCreationDate().isEmpty())
				continue;
			Timestamp creationDate = getTimestampCreationDate(tempOrder.getCreationDate(), tempOrderID);
			
			MBPartner customer = getCustomer(tempOrder.getCustomerSubdist());
			if (customer == null || customer.get_ID() <=0 ) {
				s_log.log(Level.SEVERE, ClientUtil.ERROR_MSG_KODE_BP_NULL+tempOrderID);
				continue;
			}
			
			int M_Warehouse_ID = ClientUtil.getDefaultWarehouseID();
			if(M_Warehouse_ID<=0){
				s_log.log(Level.SEVERE, ClientUtil.ERROR_MSG_WAREHOUSE_NULL+tempOrderID);
				continue;
			}
			
			MOrder order = new MOrder(getCtx(), 0, get_TrxName());
			order.setC_DocType_ID(C_DocType_ID);
			order.setDateOrdered(creationDate);
			order.setDatePromised(creationDate);
			order.setDateAcct(creationDate);
			order.setBPartner(customer);
			order.setSalesRep_ID(customer.getSalesRep_ID());
			order.setC_Currency_ID(ClientUtil.C_CURRENCY_ID_IDR);
			order.setM_Warehouse_ID(M_Warehouse_ID);
			order.setIsSOTrx(true);
			order.set_ValueOfColumn(ClientUtil.COLUMNNAME_Z_TempSalesOrder_ID, tempOrderID);
			order.set_ValueOfColumn(ClientUtil.COLUMNNAME_CustomerPONumber, tempOrder.getCustomerPONumber());
			order.set_ValueOfColumn(ClientUtil.COLUMNNAME_ShipToCustomer, tempOrder.getShipToCustomer());
			order.set_ValueOfColumn(ClientUtil.COLUMNNAME_KodeSubdist, tempOrder.getKodeSubdist());
			order.set_ValueOfColumn(ClientUtil.COLUMNNAME_CustomerSubdist, tempOrder.getCustomerSubdist());
			order.set_ValueOfColumn(ClientUtil.COLUMNNAME_PaymentType, tempOrder.getPaymentType());
			order.set_ValueOfColumn(ClientUtil.COLUMNNAME_StatusPO, tempOrder.getStatusPO());
			order.set_ValueOfColumn(ClientUtil.COLUMNNAME_DescStatus, tempOrder.getDescStatus());
			order.set_ValueOfColumn(ClientUtil.COLUMNNAME_CreationDate, tempOrder.getCreationDate());
			order.saveEx();
			
			int lineNo = 0;
			for (Integer tempOrderLineID : getListTempOrderLineID(tempOrderID)) {
				countLine = countLine+1;
				lineNo = lineNo+10;
				X_Z_TempSalesOrderLine tempOrderLine = new X_Z_TempSalesOrderLine(getCtx(), tempOrderLineID, get_TrxName());
				MProduct product = getProduct(tempOrderLine.getKodeProduct());
				MOrderLine orderLine = new MOrderLine(getCtx(), 0, get_TrxName());
				orderLine.setHeaderInfo(order);
				orderLine.setC_Order_ID(order.get_ID());
				orderLine.setLine(lineNo);
				orderLine.setDateOrdered(creationDate);
				orderLine.setM_Product_ID(product.get_ID());
				orderLine.setC_UOM_ID(product.getC_UOM_ID());
				orderLine.setM_Warehouse_ID(M_Warehouse_ID);
				orderLine.setC_BPartner_Location_ID(order.getC_BPartner_Location_ID());
				BigDecimal qty = new BigDecimal(tempOrderLine.getQuantityPO());
				orderLine.setQty(qty);
				orderLine.setC_Currency_ID(order.getC_Currency_ID());
				orderLine.setPrice();
				orderLine.setTax();//TODO review tax
				orderLine.setLineNetAmt();
				orderLine.set_ValueOfColumn(ClientUtil.COLUMNNAME_Z_TempSalesOrderLine_ID, tempOrderLineID);
				orderLine.set_ValueOfColumn(ClientUtil.COLUMNNAME_CustomerPONumber, tempOrderLine.getCustomerPONumber());
				orderLine.set_ValueOfColumn(ClientUtil.COLUMNNAME_KodeProduct, tempOrderLine.getKodeProduct());
				orderLine.set_ValueOfColumn(ClientUtil.COLUMNNAME_KodeProdPartner, tempOrderLine.getKodeProdPartner());
				orderLine.set_ValueOfColumn(ClientUtil.COLUMNNAME_QuantityPO, tempOrderLine.getQuantityPO());
				orderLine.set_ValueOfColumn(ClientUtil.COLUMNNAME_uom_emos, tempOrderLine.getuom_emos());
				orderLine.set_ValueOfColumn(ClientUtil.COLUMNNAME_Z_TempSalesOrder_ID, tempOrderID);
				orderLine.saveEx();
				
				tempOrderLine.setI_IsImported(true);
				tempOrderLine.saveEx();;
			}
			tempOrder.setI_IsImported(true);
			tempOrder.saveEx();
			
			order.processIt(DocAction.ACTION_Prepare);
			order.saveEx();
		}
		
		StringBuilder returnMsg = new StringBuilder();
		returnMsg.append("SO Header Imported#"+countHeader).append(" ");
		returnMsg.append("SO Line Imported#"+countLine);
		
		return returnMsg.toString();
	}

	private Timestamp getTimestampCreationDate(String creationDate, int tempOrderID) {
		Timestamp date = null;
		try {
			String[] arr = creationDate.split("/");
			int year = Integer.parseInt(arr[0]);
			int month = Integer.parseInt(arr[1]);
			String strDay = arr[2];
			strDay = ClientUtil.left(strDay, 2);
			int day = Integer.parseInt(strDay);
			date = TimeUtil.getDay(year, month, day);
		}catch(Exception e) {
			date = TimeUtil.getDay(TimeUtil.getToday().getTimeInMillis());
			s_log.log(Level.SEVERE, ClientUtil.ERROR_MSG_CREATION_DATE+tempOrderID);
		}
		return date;
	}
	
	private List<Integer> getListTempOrderID(){
		List<Integer> listTempOrderID = new ArrayList<Integer>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT distinct h.Z_TempSalesOrder_ID FROM Z_TempSalesOrder h ");
		sb.append("JOIN Z_TempSalesOrderLine l on l.Z_TempSalesOrder_ID = h.Z_TempSalesOrder_ID ");
		sb.append("WHERE l.I_IsImported='N'");
		
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sb.toString(), get_TrxName());
			//pstmt.setString(1, kodeProduct);
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				listTempOrderID.add(rs.getInt(1));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sb.toString(), e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		
		return listTempOrderID;
	}

	private List<Integer> getListTempOrderLineID(int Z_TempSalesOrder_ID) {
		List<Integer> list = new ArrayList<Integer>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT l.Z_TempSalesOrderLine_ID FROM Z_TempSalesOrderLine l ");
		sb.append("WHERE l.I_IsImported='N' AND Z_TempSalesOrder_ID=?");
		
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sb.toString(), get_TrxName());
			pstmt.setInt(1, Z_TempSalesOrder_ID);
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add(rs.getInt(1));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sb.toString(), e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		
		return list;
	}
	
	private MBPartner getCustomer(String kodeBP) {
		MBPartner customer = MBPartner.get(getCtx(), kodeBP);
		//if (customer == null)
		//	throw new AdempiereException(ClientUtil.ERROR_MSG_KODE_BP_NULL+kodeBP);
		return customer;
	}
	
	private MProduct getProduct(String kodeProduct) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT M_Product_ID FROM M_Product WHERE Value = ?");
		
		int M_Product_ID = 0;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sb.toString(), get_TrxName());
			pstmt.setString(1, kodeProduct);
			ResultSet rs = pstmt.executeQuery ();
			if (rs.next ())
				M_Product_ID = rs.getInt(1);
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sb.toString(), e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		
		/* no need to throw
		if(M_Product_ID <=0 )
			throw new AdempiereException(ClientUtil.ERROR_MSG_KODE_PRODUCT_NULL+M_Product_ID);*/
		MProduct product = new MProduct(getCtx(), M_Product_ID, get_TrxName());
		
		return product;
	}
	
}
