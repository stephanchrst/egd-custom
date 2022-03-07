package com.egd.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import org.compiere.model.MSysConfig;
import org.compiere.model.X_Z_EMOS_API_LOG;

public class ClientUtil {

	public static final String stdDateFormat = "dd/MM/yyyy";
	public static final String INQUIRYORDER = "INQUIRYORDER";
	public static final String SHA_256 = "SHA-256";
	public static final int C_CURRENCY_ID_IDR = 303;
	
	/**System Configurator Name*/
	public static final String SYSCONFIG_EMOS_KODE_BP = "EMOS_KODE_BP";
	public static final String SYSCONFIG_EMOS_SECRET_KEY_API = "EMOS_SECRET_KEY_API";
	public static final String SYSCONFIG_EMOS_DEBUG_MODE = "EMOS_DEBUG_MODE";
	public static final String SYSCONFIG_DOCTYPE_SO_ID = "DOCTYPE_SO_ID";
	public static final String SYSCONFIG_DEFAULT_WAREHOUSE_ID = "DEFAULT_WAREHOUSE_ID";
	
	/**Error Message*/
	public static final String ERROR_MSG_SO_DOCTYPE_NULL = "System Configurator SO Doc Type belum di setup";
	public static final String ERROR_MSG_KODE_BP_NA = "System Configurator Kode BP belum di setup";
	public static final String ERROR_MSG_SECRET_KEY_NA = "System Configurator Secret Key belum di setup";
	public static final String ERROR_MSG_WAREHOUSE_NULL = "System Configurator Warehouose belum di setup";
	public static final String ERROR_MSG_KODE_BP_NULL = "Customer tidak ditemukan ";
	public static final String ERROR_MSG_KODE_PRODUCT_NULL = "Product tidak ditemukan ";
	public static final String ERROR_MSG_CREATION_DATE = "Date null atau invalid ";
	
	/**New Column C_Order C_OrderLine*/
	public static final String COLUMNNAME_Z_TempSalesOrder_ID="Z_TempSalesOrder_ID";
	public static final String COLUMNNAME_CustomerPONumber="CustomerPONumber";
	public static final String COLUMNNAME_ShipToCustomer="ShipToCustomer";
	public static final String COLUMNNAME_KodeSubdist="KodeSubdist";
	public static final String COLUMNNAME_CustomerSubdist="CustomerSubdist";
	public static final String COLUMNNAME_PaymentType="PaymentType";
	public static final String COLUMNNAME_StatusPO="StatusPO";
	public static final String COLUMNNAME_DescStatus="DescStatus";
	public static final String COLUMNNAME_CreationDate="CreationDate";
	public static final String COLUMNNAME_I_IsImported="I_IsImported";
	public static final String COLUMNNAME_Z_TempSalesOrderLine_ID="Z_TempSalesOrderLine_ID";
	public static final String COLUMNNAME_KodeProduct="KodeProduct";
	public static final String COLUMNNAME_KodeProdPartner="KodeProdPartner";
	public static final String COLUMNNAME_QuantityPO="QuantityPO";
	public static final String COLUMNNAME_uom_emos="uom_emos";
	
	public static String generateSHA_256(String param) {
		return digest(SHA_256, param);
	}
	
	private static String digest(String alg, String input) {
	    try {
	        MessageDigest md = MessageDigest.getInstance(alg);
	        byte[] buffer = input.getBytes("UTF-8");
	        md.update(buffer);
	        byte[] digest = md.digest();
	        return encodeHex(digest);
	    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
	        e.printStackTrace();
	        return e.getMessage();
	    }
	}
	
	private static String encodeHex(byte[] digest) {
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < digest.length; i++) {
	        sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
	    }
	    return sb.toString();
	}
	
	public static String getKodeBP() {
		String kodeBP = MSysConfig.getValue(SYSCONFIG_EMOS_KODE_BP, "N/A");
		return kodeBP;
	}
	
	public static String getSecretKey() {
		String secretKey = MSysConfig.getValue(SYSCONFIG_EMOS_SECRET_KEY_API, "N/A");
		return secretKey;
	}
	
	public static int getSO_C_DocType_ID() {
		int docType_ID = MSysConfig.getIntValue(SYSCONFIG_DOCTYPE_SO_ID, 0);
		return docType_ID;
	}
	
	public static boolean inEMOSDebugMode() {
		boolean debugMode = MSysConfig.getBooleanValue(SYSCONFIG_EMOS_DEBUG_MODE, false);
		return debugMode;
	}
	
	public static int getDefaultWarehouseID() {
		int warehouseID = MSysConfig.getIntValue(SYSCONFIG_DEFAULT_WAREHOUSE_ID, 0);
		return warehouseID;
	}
	
	public static void createLog(String linkUrl, String input, String output, Properties ctx, String trxName) {
		X_Z_EMOS_API_LOG log = new X_Z_EMOS_API_LOG(ctx, 0, trxName);
		log.setLinkURLAPI(linkUrl);
		log.setRequestMsg(input);
		log.setResponseMsg(output);
		log.saveEx();
	}
	
}
