/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for Z_TempSalesOrder
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3
 */
public interface I_Z_TempSalesOrder 
{

    /** TableName=Z_TempSalesOrder */
    public static final String Table_Name = "Z_TempSalesOrder";

    /** AD_Table_ID=1000002 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name CreationDate */
    public static final String COLUMNNAME_CreationDate = "CreationDate";

	/** Set Creation Date	  */
	public void setCreationDate (String CreationDate);

	/** Get Creation Date	  */
	public String getCreationDate();

    /** Column name CustomerPONumber */
    public static final String COLUMNNAME_CustomerPONumber = "CustomerPONumber";

	/** Set Customer PO Number	  */
	public void setCustomerPONumber (String CustomerPONumber);

	/** Get Customer PO Number	  */
	public String getCustomerPONumber();

    /** Column name CustomerSubdist */
    public static final String COLUMNNAME_CustomerSubdist = "CustomerSubdist";

	/** Set Customer Subdist	  */
	public void setCustomerSubdist (String CustomerSubdist);

	/** Get Customer Subdist	  */
	public String getCustomerSubdist();

    /** Column name DescStatus */
    public static final String COLUMNNAME_DescStatus = "DescStatus";

	/** Set Desc Status	  */
	public void setDescStatus (String DescStatus);

	/** Get Desc Status	  */
	public String getDescStatus();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name KodeSubdist */
    public static final String COLUMNNAME_KodeSubdist = "KodeSubdist";

	/** Set Kode Subdist	  */
	public void setKodeSubdist (String KodeSubdist);

	/** Get Kode Subdist	  */
	public String getKodeSubdist();

    /** Column name PaymentType */
    public static final String COLUMNNAME_PaymentType = "PaymentType";

	/** Set Payment Type	  */
	public void setPaymentType (String PaymentType);

	/** Get Payment Type	  */
	public String getPaymentType();

    /** Column name ShipToCustomer */
    public static final String COLUMNNAME_ShipToCustomer = "ShipToCustomer";

	/** Set Ship To Customer	  */
	public void setShipToCustomer (String ShipToCustomer);

	/** Get Ship To Customer	  */
	public String getShipToCustomer();

    /** Column name StatusPO */
    public static final String COLUMNNAME_StatusPO = "StatusPO";

	/** Set Status PO	  */
	public void setStatusPO (String StatusPO);

	/** Get Status PO	  */
	public String getStatusPO();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID);

	/** Get Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public String getUUID();

    /** Column name Z_TempSalesOrder_ID */
    public static final String COLUMNNAME_Z_TempSalesOrder_ID = "Z_TempSalesOrder_ID";

	/** Set Temporary Sales Order ID	  */
	public void setZ_TempSalesOrder_ID (int Z_TempSalesOrder_ID);

	/** Get Temporary Sales Order ID	  */
	public int getZ_TempSalesOrder_ID();
}
