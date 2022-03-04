package com.egd.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MOrder;
import org.compiere.model.MProduct;
import org.compiere.model.X_Z_TempSalesOrder;
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
		
		for (Integer tempOrderID : getListTempOrderID()) {
			X_Z_TempSalesOrder tempOrder = new X_Z_TempSalesOrder(getCtx(), tempOrderID, get_TrxName());
			
			if(tempOrder.getCreationDate() == null || tempOrder.getCreationDate().isEmpty())
				continue;
			Timestamp creationDate = getTimestampCreationDate(tempOrder.getCreationDate(), tempOrderID);
			
			MBPartner customer = getCustomer(tempOrder.getCustomerSubdist());
			if (customer == null) {
				s_log.log(Level.WARNING, ClientUtil.ERROR_MSG_KODE_BP_NULL+tempOrderID);
				continue;
			}
			
			int M_Warehouse_ID = ClientUtil.getDefaultWarehouseID();
			if(M_Warehouse_ID<=0){
				s_log.log(Level.WARNING, ClientUtil.ERROR_MSG_WAREHOUSE_NULL+tempOrderID);
				continue;
			}
			
			MOrder order = new MOrder(getCtx(), 0, get_TrxName());
			order.setC_DocType_ID(C_DocType_ID);
			order.setDateOrdered(creationDate);
			order.setDatePromised(creationDate);
			order.setDateAcct(creationDate);
			order.setC_BPartner_ID(customer.get_ID());
			order.setSalesRep_ID(customer.getSalesRep_ID());
			order.setC_Currency_ID(ClientUtil.C_CURRENCY_ID_IDR);
			order.setM_Warehouse_ID(M_Warehouse_ID);
			order.setM_PriceList_ID(customer.getM_PriceList_ID());
			order.setC_BPartner_Location_ID(customer.getPrimaryC_BPartner_Location_ID());
			order.setIsSOTrx(true);
			//TODO masih kurang customer po number, shiptocustomer, kodesubdist, 
			//customersubdist, paymenttype, statuspo, descstatus
			
		}
		
		return null;
	}

	protected Timestamp getTimestampCreationDate(String creationDate, int tempOrderID) {
		Timestamp date = null;
		try {
			String[] arr = creationDate.split("/");
			int year = Integer.parseInt(arr[0]);
			int month = Integer.parseInt(arr[1]);
			int day = Integer.parseInt(arr[2]);
			date = TimeUtil.getDay(year, month, day);
		}catch(Exception e) {
			date = TimeUtil.getDay(TimeUtil.getToday().getTimeInMillis());
			s_log.log(Level.WARNING, ClientUtil.ERROR_MSG_CREATION_DATE+tempOrderID);
		}
		return date;
	}
	
	protected List<Integer> getListTempOrderID(){
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

	protected MBPartner getCustomer(String kodeBP) {
		MBPartner customer = MBPartner.get(getCtx(), kodeBP);
		//if (customer == null)
		//	throw new AdempiereException(ClientUtil.ERROR_MSG_KODE_BP_NULL+kodeBP);
		return customer;
	}
	
	protected MProduct getProduct(String kodeProduct) {
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
