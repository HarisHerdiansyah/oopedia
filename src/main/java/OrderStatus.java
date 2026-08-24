public enum OrderStatus {
    ON_DELIVERY("1", "On delivery"),
    ARRIVED("2", "Order arrived at location"),
    CANCELLED("3", "Order cancelled by admin"),
    WAIT_FOR_PAYMENT("4", "Wait for payment from customer"),
    PROCESSING("5", "Order is on process"),
    UNKNOWN("0", "Something went wrong");

    private final String code;
    private final String msg;

    OrderStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static OrderStatus fromCode(String code) {
        for (OrderStatus orderStatus: values()) {
            if (orderStatus.code.equals(code)) {
                return orderStatus;
            }
        }
        return UNKNOWN;
    }
}
