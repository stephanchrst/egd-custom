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

/** Generated Model for Z_EMOS_API_LOG
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_Z_EMOS_API_LOG extends PO implements I_Z_EMOS_API_LOG, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220304L;

    /** Standard Constructor */
    public X_Z_EMOS_API_LOG (Properties ctx, int Z_EMOS_API_LOG_ID, String trxName)
    {
      super (ctx, Z_EMOS_API_LOG_ID, trxName);
      /** if (Z_EMOS_API_LOG_ID == 0)
        {
			setZ_EMOS_API_LOG_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_EMOS_API_LOG (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_Z_EMOS_API_LOG[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Link URL API.
		@param LinkURLAPI Link URL API	  */
	public void setLinkURLAPI (String LinkURLAPI)
	{
		set_Value (COLUMNNAME_LinkURLAPI, LinkURLAPI);
	}

	/** Get Link URL API.
		@return Link URL API	  */
	public String getLinkURLAPI () 
	{
		return (String)get_Value(COLUMNNAME_LinkURLAPI);
	}

	/** Set Request Message.
		@param RequestMsg Request Message	  */
	public void setRequestMsg (String RequestMsg)
	{
		set_Value (COLUMNNAME_RequestMsg, RequestMsg);
	}

	/** Get Request Message.
		@return Request Message	  */
	public String getRequestMsg () 
	{
		return (String)get_Value(COLUMNNAME_RequestMsg);
	}

	/** Set Response Message.
		@param ResponseMsg Response Message	  */
	public void setResponseMsg (String ResponseMsg)
	{
		set_Value (COLUMNNAME_ResponseMsg, ResponseMsg);
	}

	/** Get Response Message.
		@return Response Message	  */
	public String getResponseMsg () 
	{
		return (String)get_Value(COLUMNNAME_ResponseMsg);
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

	/** Set Z_EMOS_API_LOG ID.
		@param Z_EMOS_API_LOG_ID Z_EMOS_API_LOG ID	  */
	public void setZ_EMOS_API_LOG_ID (int Z_EMOS_API_LOG_ID)
	{
		if (Z_EMOS_API_LOG_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_EMOS_API_LOG_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_EMOS_API_LOG_ID, Integer.valueOf(Z_EMOS_API_LOG_ID));
	}

	/** Get Z_EMOS_API_LOG ID.
		@return Z_EMOS_API_LOG ID	  */
	public int getZ_EMOS_API_LOG_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_EMOS_API_LOG_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}