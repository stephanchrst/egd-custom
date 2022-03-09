package com.egd.process;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.X_Z_TempSalesOrder;
import org.compiere.model.X_Z_TempSalesOrderLine;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.egd.model.EMOrder;
import com.egd.model.EMOrderLine;
import com.egd.utils.ClientUtil;

/**
 * @author stephan.christianus
 * shceduler for get data order from API EMOS 
 * and insert into temp sales order
 */
public class EMOSGetOrder extends SvrProcess{
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (EMOSGetOrder.class);
	
	protected final String url_list_order = "https://sandboxdev.enseval.com/external/inquiry/order/listorder";
	protected final String reqMethod = "POST";
	protected final String utf8 = "utf-8";
	protected final String headerPO = "headerPO";
	
	@Override
	protected void prepare() {
		
	}

	@Override
	protected String doIt() throws Exception {
		String respOutput = "";
		
		URL url = new URL(url_list_order);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod(reqMethod);
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		
	    Date date = Calendar.getInstance().getTime();
	    DateFormat dateFormat = new SimpleDateFormat(ClientUtil.stdDateFormat);  
	    String strDate = dateFormat.format(date);  
		strDate = "04/03/2022";//for testing only
	    
		String kodebp = ClientUtil.getKodeBP();
		String secretkey = ClientUtil.getSecretKey();
		
		if(kodebp.equals("N/A"))
			throw new AdempiereException(ClientUtil.ERROR_MSG_KODE_BP_NA);
		if(secretkey.equals("N/A")) {
			throw new AdempiereException(ClientUtil.ERROR_MSG_SECRET_KEY_NA);
		}
		
		StringBuilder wordSignature = new StringBuilder();
		wordSignature.append(ClientUtil.INQUIRYORDER).append(strDate)
		.append(strDate).append(kodebp).append(secretkey);
		
		JSONObject jo = new JSONObject();
		jo.put("kodebp", kodebp);
		jo.put("startdate", strDate);
		jo.put("enddate", strDate);
		jo.put("signature", ClientUtil.generateSHA_256(wordSignature.toString()));
		
		String jsonInput = jo.toString();
		try(OutputStream os = con.getOutputStream()) {
		    byte[] input = jsonInput.getBytes(utf8);
		    os.write(input, 0, input.length);
		}
		
		try(BufferedReader br = new BufferedReader(
			new InputStreamReader(con.getInputStream(), utf8))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			respOutput = response.toString();
		}
		
		// mapping to model class
		JSONObject obj = new JSONObject(respOutput);
		Gson gson = new Gson();
		Type typeOrder = new TypeToken<List<EMOrder>>() {}.getType();
		List<EMOrder> listOrder = gson.fromJson(obj.getJSONArray(headerPO).toString(), typeOrder);
		
		ClientUtil.createLog(url_list_order, jsonInput, respOutput, getCtx(), get_TrxName());
		createTempSalesOrder(listOrder);
		
		return respOutput;
	}
	
	protected void createTempSalesOrder(List<EMOrder> listOrder) {
		
		for (EMOrder order : listOrder) {
			//skip if same customer po number
			String checkPONumber = order.getCustomerPONumber();
			if(alreadyCreatedInTemp(checkPONumber) && !ClientUtil.inEMOSDebugMode())
				continue;
			
			X_Z_TempSalesOrder tempOrder = new X_Z_TempSalesOrder(getCtx(), 0, get_TrxName());
			tempOrder.setCustomerPONumber(order.getCustomerPONumber());
			tempOrder.setShipToCustomer(order.getShipToCustomer());
			tempOrder.setKodeSubdist(order.getKodeSubdist());
			tempOrder.setCustomerSubdist(order.getCustomerSubdist());
			tempOrder.setPaymentType(order.getPaymentType());
			tempOrder.setStatusPO(order.getStatusPO());
			tempOrder.setDescStatus(order.getDescStatus());
			tempOrder.setCreationDate(order.getCreationDate());//TODO need to change to timestamp later
			tempOrder.setI_IsImported(false);
			tempOrder.saveEx();
			for (EMOrderLine orderLine : order.getLinePO()) {
				X_Z_TempSalesOrderLine tempOrderLine = new X_Z_TempSalesOrderLine(getCtx(), 0, get_TrxName());
				tempOrderLine.setZ_TempSalesOrder_ID(tempOrder.get_ID());
				tempOrderLine.setCustomerPONumber(order.getCustomerPONumber());
				tempOrderLine.setKodeProduct(orderLine.getKodeProduct());
				tempOrderLine.setKodeProdPartner(orderLine.getKodeProdPartner());
				tempOrderLine.setQuantityPO(orderLine.getQuantityPO());
				tempOrderLine.setuom_emos(orderLine.getUom());
				tempOrderLine.setI_IsImported(false);	
				tempOrderLine.saveEx();
			}
		}
	}
	
	protected boolean alreadyCreatedInTemp(String custPONumber) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT Z_TempSalesOrder_ID FROM Z_TempSalesOrder WHERE CustomerPONumber=?");
		int Z_TempSalesOrder_ID = 0;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sb.toString(), get_TrxName());
			pstmt.setString(1, custPONumber);
			ResultSet rs = pstmt.executeQuery ();
			if (rs.next ())
				Z_TempSalesOrder_ID = rs.getInt(1);
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
		
		if(Z_TempSalesOrder_ID > 0)
			return true;
		return false;
	}
	
}
