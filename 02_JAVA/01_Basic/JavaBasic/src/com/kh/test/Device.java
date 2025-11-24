package com.kh.test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 추상 부모: 공통 필드/행동 + 오버라이딩 대상(대여일수/연체료)
 */
public abstract class Device {
    private String id;           // 고유 식별자 (Map의 key로 사용됨)
    private String name;         // 장비명
    private String category;     // 예: "Camera", "Laptop"
    private int borrowCount;     // 누적 대여 횟수
    private Set<String> tags;    // 중복 없는 태그 모음

    public Device(String id, String name, String category, Set<String> tags) {
        this.id = Objects.requireNonNull(id, "id");
        this.name = Objects.requireNonNull(name, "name");
        this.category = Objects.requireNonNull(category, "category");
        // 방어적 복사 + null 방지
        this.tags = (tags == null) ? new HashSet<>() : new HashSet<>(tags);
    }

    /** 하위 타입에서 반드시 구현해야 하는 규칙 메서드들 */
    public abstract int getBorrowLimitDays();
    public abstract int calcLateFee(int overdueDays);

    /** 공통 유틸 */
    public void increaseBorrowCount() { this.borrowCount++; }

    /** 대소문자 무시 태그 포함 여부 */
    public boolean hasTag(String tag) {
        if (tag == null) return false;
        String t = tag.toLowerCase();
        for (String s : tags) {
            if (s != null && s.toLowerCase().equals(t)) return true;
        }
        return false;
    }

    // === getters / setters ===
    public String getId() { return id; }
    public void setId(String id) { this.id = Objects.requireNonNull(id); } // 주의: catalog key와 일치 필요
    public String getName() { return name; }
    public void setName(String name) { this.name = Objects.requireNonNull(name); }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = Objects.requireNonNull(category); }
    public int getBorrowCount() { return borrowCount; }
    public Set<String> getTags() { return new HashSet<>(tags); }
    public void setTags(Set<String> tags) {
        this.tags = (tags == null) ? new HashSet<>() : new HashSet<>(tags);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) | 대여수=%d | tags=%s",
                id, name, category, borrowCount, tags);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Device)) return false;
        Device device = (Device) o;
        return Objects.equals(id, device.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
