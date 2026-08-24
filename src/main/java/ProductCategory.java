public enum ProductCategory {
    ELECTRONIC("1", "Electronic"),
    FASHION("2", "Fashion"),
    SOFTWARE("3", "Software"),
    EBOOK("4", "E-Book"),
    UNKNOWN("0", "Unknown category");

    private final String code;
    private final String desc;

    ProductCategory(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ProductCategory fromCode(String code) {
        for (ProductCategory category: values()) {
            if (category.code.equals(code)) {
                return category;
            }
        }
        return UNKNOWN;
    }
}
