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

/** Generated Model for Z_TempSalesOrder
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_Z_TempSalesOrder extends PO implements I_Z_TempSalesOrder, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220304L;

    /** Standard Constructor */
    public X_Z_TempSalesOrder (Properties ctx, int Z_TempSalesOrder_ID, String trxName)
    {
      super (ctx, Z_TempSalesOrder_ID, trxName);
      /** if (Z_TempSalesOrder_ID == 0)
        {
			setZ_TempSalesOrder_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_TempSalesOrder (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_Z_TempSalesOrder[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Creation Date.
		@param CreationDate Creation Date	  */
	public void setCreationDate (String CreationDate)
	{
		set_Value (COLUMNNAME_CreationDate, CreationDate);
	}

	/** Get Creation Date.
		@return Creation Date	  */
	public String getCreationDate () 
	{
		return (String)get_Value(COLUMNNAME_CreationDate);
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

	/** Set Customer Subdist.
		@param CustomerSubdist Customer Subdist	  */
	public void setCustomerSubdist (String CustomerSubdist)
	{
		set_Value (COLUMNNAME_CustomerSubdist, CustomerSubdist);
	}

	/** Get Customer Subdist.
		@return Customer Subdist	  */
	public String getCustomerSubdist () 
	{
		return (String)get_Value(COLUMNNAME_CustomerSubdist);
	}

	/** Set Desc Status.
		@param DescStatus Desc Status	  */
	public void setDescStatus (String DescStatus)
	{
		set_Value (COLUMNNAME_DescStatus, DescStatus);
	}

	/** Get Desc Status.
		@return Desc Status	  */
	public String getDescStatus () 
	{
		return (String)get_Value(COLUMNNAME_DescStatus);
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

	/** Set Kode Subdist.
		@param KodeSubdist Kode Subdist	  */
	public void setKodeSubdist (String KodeSubdist)
	{
		set_Value (COLUMNNAME_KodeSubdist, KodeSubdist);
	}

	/** Get Kode Subdist.
		@return Kode Subdist	  */
	public String getKodeSubdist () 
	{
		return (String)get_Value(COLUMNNAME_KodeSubdist);
	}

	/** Set Payment Type.
		@param PaymentType Payment Type	  */
	public void setPaymentType (String PaymentType)
	{
		set_Value (COLUMNNAME_PaymentType, PaymentType);
	}

	/** Get Payment Type.
		@return Payment Type	  */
	public String getPaymentType () 
	{
		return (String)get_Value(COLUMNNAME_PaymentType);
	}

	/** Set Ship To Customer.
		@param ShipToCustomer Ship To Customer	  */
	public void setShipToCustomer (String ShipToCustomer)
	{
		set_Value (COLUMNNAME_ShipToCustomer, ShipToCustomer);
	}

	/** Get Ship To Customer.
		@return Ship To Customer	  */
	public String getShipToCustomer () 
	{
		return (String)get_Value(COLUMNNAME_ShipToCustomer);
	}

	/** Set Status PO.
		@param StatusPO Status PO	  */
	public void setStatusPO (String StatusPO)
	{
		set_Value (COLUMNNAME_StatusPO, StatusPO);
	}

	/** Get Status PO.
		@return Status PO	  */
	public String getStatusPO () 
	{
		return (String)get_Value(COLUMNNAME_StatusPO);
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
}