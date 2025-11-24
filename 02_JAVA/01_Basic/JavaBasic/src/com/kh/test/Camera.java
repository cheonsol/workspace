package com.kh.test;

import java.util.Set;

public class Camera extends Device {
    public Camera(String id, String name, String category, Set<String> tags) {
        super(id, name, category, tags);
    }
    @Override
    public int getBorrowLimitDays() { return 7; } // 카메라: 7일
    @Override
    public int calcLateFee(int overdueDays) { return 300 * overdueDays; } // 300원/일
}
