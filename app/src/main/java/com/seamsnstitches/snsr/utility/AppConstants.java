package com.seamsnstitches.snsr.utility;

public class AppConstants {

    /**
     * This field will be used by the adapter when detail fragment is gonna be
     * called to check the purpose of the calling.. if its search option or order option that
     * requires the fragment.. WHATEVER JOOR
     */
    public static final String IS_ORDER_LIST = "order_or_search_list";

    /**
     * Used as a tag when opening a UserDetailBottomSheet fragment
     */
    public static final String TRANSACTION_DETAIL_TAG = "Transaction-detail";
    public static final String PURCHASE_CONFIRMATION_FRAG_TAG = "purchase_confirmation";
    public static final String PURCHASE_CONFIRMATION_FRAG_QUANTITY = "confirmstion_frag_quantity";

    /**
     * Used as a tag to open MeasurementDialogFragment
     */
    public static final String MEASUREMENT_DIALOG_FRAGMENT = "measurement_dialog";

    /**
     * Used to specify which layout is needed when a MeasurementDIalogFrag is opened
     * if its layout for shirt or layout for trousers
     */
    public static final int SHIRT_MEASUREMENT_FRAG = 435;

    /**
     * Used to specify which layout is needed when a MeasurementDIalogFrag is opened
     * if its layout for shirt or layout for trousers
     */
    public static final int TROOUSER_MEASUREMENT_FRAG = 437;

    /**
     * This is used when passing a ShirtMeasurement object in a bundle
     */
    public static final String SHIRT_MEASUREMENT_TAG = "shirt_parceable";

    /**
     * This is used when passing a TrouserMeasurement object in a bundle
     */
    public static final String TROUSER_MEASUREMENT_TAG = "trouser_parceable";
    public static final String EMPLOYEE_DETAILS = "employee_details";
    public static final String ORDERS_FRAGMENT_FASHION_BRAND = "orders_frag_fashion_brand";
    public static final String FASHION_BRAND = "fashion";
    public static final String FINAL_TRANSACTION_FRAG_TAG = "final transaction tag";
    public static final String SEARCH_USER_DETAIL_TAG = "search_user_detail";
    /**
     * This constant field is used as a tag when opening  OrdersFragment
     */
    public static final String ORDERS_FRAGMENT_TAG = "orders_fragment_tag";
    /**
     * String tag used when opening UserSearchDialogFragment
     */
    public static final String USER_SEARCH_DIALOG_FRAGMENT_TAG = "user_search_frag";
    public static String IS_LOGGED_IN = "IS_LOGGED_IN";
    public static String USER_DETAILS = "USER_DETAILS";
    public static String SHARED_PREFERENCE_NAME = "SNSR_SHARED_PREFERENCE";
}
