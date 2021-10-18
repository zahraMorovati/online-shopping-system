package model.enumation;

public enum ProductGroup {
    ELECTRONICS,
    SHOE,
    READABLE;

    public enum ProductType {
        TV(ELECTRONICS),
        RADIO(ELECTRONICS),
        SPORT_SHOE(SHOE),
        FORMAL_SHOE(SHOE),
        BOOK(READABLE),
        NEWSPAPER(READABLE);


        ProductGroup productGroup;

        ProductType(ProductGroup productGroup) {
            this.productGroup = productGroup;
        }

        public ProductType getValue(String str) {

            for (ProductType good : values()) {
                if (good.name().equalsIgnoreCase(str.trim())) {
                    return good;
                }
            }

            return null;

        }


        public ProductGroup getGoodType() {
            return productGroup;
        }

        public void setGoodType(ProductGroup productGroup) {
            this.productGroup = productGroup;
        }


    }
}
