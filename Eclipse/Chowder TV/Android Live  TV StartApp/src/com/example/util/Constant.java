package com.example.util;

import java.io.Serializable;

public class Constant implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String ADMIN = "http://www.viaviweb.in/envato/live_tv";

	//imagepath small
	public static final String SERVER_IMAGE_UPFOLDER_THUMB=ADMIN + "/images/thumbs/";

	//imagepath
	public static final String SERVER_IMAGE_UPFOLDER=ADMIN + "/images/";

	//latest
	public static final String LATEST_URL = ADMIN + "/api.php?latest=15";

	//category
	public static final String CATEGORY_URL = ADMIN + "/api.php";

	//category item
	public static final String CATEGORY_ITEM_URL = ADMIN + "/api.php?cat_id=";

	//single channel 
	public static final String SINGLE_CHANNEL_URL = ADMIN + "/api.php?channel_id=";

	//search url
	public static final String SEARCH_URL = ADMIN + "/api.php?search=";

	//report channel
	public static final String REPORT_CHANNEL_URL = ADMIN + "/api.php?review=";

	
	public static final String LATEST_ARRAY_NAME="LIVETV";
	public static final String CHANNEL_ID="id";
	public static final String CHANNEL_CATID="cat_id";
	public static final String CHANNEL_CAT_NAME="category_name";
	public static final String lATETST_CHANNEL_URL="channel_url";
	public static final String LATEST_CHANNEL_NAME="channel_title";
	public static final String LATEST_CHANNEL_DESCRIPTION="channel_desc";
	public static final String LATEST_CHANNEL_IMAGE="channel_thumbnail";


	public static final String CATEGORY_ARRAY_NAME="LIVETV";
	public static final String CATEGORY_NAME="category_name";
	public static final String CATEGORY_CID="cid";
	public static final String CATEGORY_IMAGE="category_image";


	//for title display in CategoryItemF
	public static String CATEGORY_TITLE;
	public static int CATEGORY_ID;
	public static int SINGLE_ID;
	public static String SINGLE_CHANNEL;


	public static final String CATEGORY_ITEM_ARRAY_NAME="LIVETV";
	public static final String CATEGORY_ITEM_CHANNEL_ID="id";
	public static final String CATEGORY_ITEM_CHANNEL_CATID="cat_id";
	public static final String CATEGORY_ITEM_CHANNEL_CAT_NAME="category_name";
	public static final String CATEGORY_ITEM_CHANNEL_URL="channel_url";
	public static final String CATEGORY_ITEM_CHANNEL_NAME="channel_title";
	public static final String CATEGORY_ITEM_CHANNEL_DESCRIPTION="channel_desc";
	public static final String CATEGORY_ITEM_CHANNEL_IMAGE="channel_thumbnail";

	public static final String RELATED_ITEM_ARRAY_NAME="related";
	public static final String RELATED_ITEM_CHANNEL_ID="rel_id";
	public static final String RELATED_ITEM_CHANNEL_NAME="rel_channel_title";
	public static final String RELATED_ITEM_CHANNEL_THUMB="rel_channel_thumbnail";


	public static final String REPORT_CHANNEL_ARRAY_NAME="LIVETV";
	public static final String REPORT_CHANNEL_MSG="msg";


	public static int GET_SUCCESS_MSG;
	public static String SHOP_SEARCH,SUB_DATE,SUB_EMAIL;
	public static String LOGIN_USERNAME,LOGIN_ID;
}
