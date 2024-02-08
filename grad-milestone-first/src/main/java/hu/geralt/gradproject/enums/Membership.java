package hu.geralt.gradproject.enums;

import lombok.Getter;

@Getter
public enum Membership {

    BASIC(0), ESSENTIAL(5), EXTRA(10), PREMIUM(15);

    final int discount;

    Membership(int discount) {
        this.discount = discount;
    }

}
