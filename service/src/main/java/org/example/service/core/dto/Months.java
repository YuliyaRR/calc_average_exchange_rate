package org.example.service.core.dto;

public enum Months {
    DECEMBER(new Month("December", 12, 31)),
    JANUARY(new Month("January", 1, 31)),
    FEBRUARY(new Month("February", 2, 28)),
    MARCH(new Month("March", 3, 31)),
    APRIL(new Month("April", 4, 30)),
    MAY(new Month("May", 5, 31));

    Months(Month month) {
        this.month = month;
    }

    private Month month;

    public Month getMonth() {
        return month;
    }

}
