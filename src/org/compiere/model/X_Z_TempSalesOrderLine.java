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
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for Z_TempSalesOrderLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_Z_TempSalesOrderLine extends PO implements I_Z_TempSalesOrderLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220304L;

    /** Standard Constructor */
    public X_Z_TempSalesOrderLine (Properties ctx, int Z_TempSalesOrderLine_ID, String trxName)
    {
      super (ctx, Z_TempSalesOrderLine_ID, trxName);
      /** if (Z_TempSalesOrderLine_ID == 0)
        {
			setZ_TempSalesOrderLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_TempSalesOrderLine (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_Z_TempSalesOrderLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Customer PO Number.
		@param CustomerPONumber Customer PO Number	  */
	public void setCustomerPONumber (String CustomerPONumber)
	{
		set_Value (COLUMNNAME_CustomerPONumber, CustomerPONumber);
	}

	/** Get Customer PO Number.
		@return Customer PO Number	  */
	public String getCustomerPONumber () 
	{
		return (String)get_Value(COLUMNNAME_CustomerPONumber);
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Kode Product Partner.
		@param KodeProdPartner Kode Product Partner	  */
	public void setKodeProdPartner (String KodeProdPartner)
	{
		set_Value (COLUMNNAME_KodeProdPartner, KodeProdPartner);
	}

	/** Get Kode Product Partner.
		@return Kode Product Partner	  */
	public String getKodeProdPartner () 
	{
		return (String)get_Value(COLUMNNAME_KodeProdPartner);
	}

	/** Set Kode Product.
		@param KodeProduct Kode Product	  */
	public void setKodeProduct (String KodeProduct)
	{
		set_Value (COLUMNNAME_KodeProduct, KodeProduct);
	}

	/** Get Kode Product.
		@return Kode Product	  */
	public String getKodeProduct () 
	{
		return (String)get_Value(COLUMNNAME_KodeProduct);
	}

	/** Set Quantity PO.
		@param QuantityPO Quantity PO	  */
	public void setQuantityPO (String QuantityPO)
	{
		set_Value (COLUMNNAME_QuantityPO, QuantityPO);
	}

	/** Get Quantity PO.
		@return Quantity PO	  */
	public String getQuantityPO () 
	{
		return (String)get_Value(COLUMNNAME_QuantityPO);
	}

	/** Set UOM EMOS.
		@param uom_emos UOM EMOS	  */
	public void setuom_emos (String uom_emos)
	{
		set_Value (COLUMNNAME_uom_emos, uom_emos);
	}

	/** Get UOM EMOS.
		@return UOM EMOS	  */
	public String getuom_emos () 
	{
		return (String)get_Value(COLUMNNAME_uom_emos);
	}

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}

	/** Set Temporary Sales Order ID.
		@param Z_TempSalesOrder_ID Temporary Sales Order ID	  */
	public void setZ_TempSalesOrder_ID (int Z_TempSalesOrder_ID)
	{
		if (Z_TempSalesOrder_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_TempSalesOrder_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_TempSalesOrder_ID, Integer.valueOf(Z_TempSalesOrder_ID));
	}

	/** Get Temporary Sales Order ID.
		@return Temporary Sales Order ID	  */
	public int getZ_TempSalesOrder_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_TempSalesOrder_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Temporary Sales Order Line ID.
		@param Z_TempSalesOrderLine_ID Temporary Sales Order Line ID	  */
	public void setZ_TempSalesOrderLine_ID (int Z_TempSalesOrderLine_ID)
	{
		if (Z_TempSalesOrderLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_TempSalesOrderLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_TempSalesOrderLine_ID, Integer.valueOf(Z_TempSalesOrderLine_ID));
	}

	/** Get Temporary Sales Order Line ID.
		@return Temporary Sales Order Line ID	  */
	public int getZ_TempSalesOrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_TempSalesOrderLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}