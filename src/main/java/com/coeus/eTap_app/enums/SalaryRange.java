package com.coeus.eTap_app.enums;

public enum SalaryRange {
    ZERO_TO_500("0-500"),
    FIVE_HUNDRED_TO_1000("500-1000"),
    ONE_THOUSAND_TO_2000("1000-2000"),
    TWO_THOUSAND_TO_5000("2000-5000"),
    FIVE_THOUSAND_PLUS("5000+");

    private final String range;

    SalaryRange(String range) {
        this.range = range;
    }

    public String getRange() {
        return range;
    }


    public static SalaryRange getRangeBySalary(int salary) {
        if (salary <= 500) {
            return SalaryRange.ZERO_TO_500;
        } else if (salary <= 1000) {
            return SalaryRange.FIVE_HUNDRED_TO_1000;
        } else if (salary <= 2000) {
            return SalaryRange.ONE_THOUSAND_TO_2000;
        } else {
            return SalaryRange.FIVE_THOUSAND_PLUS;
        }
    }
}
