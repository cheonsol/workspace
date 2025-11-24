package com.kh.test;

import java.time.LocalDate;
import java.util.*;

/**
 * 저장소(Map/Set) 관리 + 핵심 비즈니스 로직
 * - 정렬 금지: 반환 순서는 보장하지 않음
 */
public class GearRentController {
    private final Map<String, Device> catalog = new HashMap<>();    // itemId -> Device
    private final Map<String, Member> members = new HashMap<>();    // memberId -> Member
    private final Map<String, Loan> activeLoans = new HashMap<>();  // itemId -> Loan

    // === 등록 ===
    public boolean addDevice(Device device) {
        if (device == null) throw new IllegalArgumentException("device=null");
        String id = device.getId();
        if (catalog.containsKey(id)) return false;
        catalog.put(id, device);
        return true;
    }

    public boolean addMember(Member member) {
        if (member == null) throw new IllegalArgumentException("member=null");
        String id = member.getId();
        if (members.containsKey(id)) return false;
        members.put(id, member);
        return true;
    }

    // === 대여/반납 ===
    public Loan borrow(String memberId, String itemId, LocalDate today) {
        if (memberId == null || memberId.isBlank()) throw new IllegalArgumentException("memberId blank");
        if (itemId == null || itemId.isBlank()) throw new IllegalArgumentException("itemId blank");
        if (today == null) throw new IllegalArgumentException("today=null");

        Member mem = members.get(memberId);
        if (mem == null) throw new IllegalArgumentException("회원이 존재하지 않습니다: " + memberId);

        Device dev = catalog.get(itemId);
        if (dev == null) throw new IllegalArgumentException("장비가 존재하지 않습니다: " + itemId);

        if (activeLoans.containsKey(itemId)) throw new IllegalStateException("이미 대여 중: " + itemId);

        int limit = dev.getBorrowLimitDays();
        Loan loan = new Loan(itemId, memberId, today, today.plusDays(limit));
        activeLoans.put(itemId, loan);
        dev.increaseBorrowCount();
        return loan;
    }

    public int returnItem(String itemId, LocalDate today) {
        if (itemId == null || itemId.isBlank()) throw new IllegalArgumentException("itemId blank");
        if (today == null) throw new IllegalArgumentException("today=null");

        Loan loan = activeLoans.get(itemId);
        if (loan == null) throw new IllegalStateException("대여 중이 아닙니다: " + itemId);

        loan.setReturnedDate(today);
        int overdue = loan.overdueDays(today); // 0 이상
        Device dev = catalog.get(itemId);
        int fee = dev.calcLateFee(overdue);
        activeLoans.remove(itemId);
        return fee;
    }

    // === 검색/조회 ===
    public ArrayList<Device> findByTag(String tag) {
        ArrayList<Device> result = new ArrayList<>();
        if (tag == null) return result;
        for (Device d : catalog.values()) {
            if (d.hasTag(tag)) result.add(d);
        }
        return result; // 정렬 금지
    }

    public ArrayList<Device> findByKeyword(String keyword) {
        ArrayList<Device> result = new ArrayList<>();
        if (keyword == null || keyword.isBlank()) return result;
        String kw = keyword.toLowerCase(Locale.ROOT);
        for (Device d : catalog.values()) {
            String name = (d.getName() == null) ? "" : d.getName().toLowerCase(Locale.ROOT);
            String cat  = (d.getCategory() == null) ? "" : d.getCategory().toLowerCase(Locale.ROOT);
            if (name.contains(kw) || cat.contains(kw)) result.add(d);
        }
        return result; // 정렬 금지
    }

    public Collection<Device> getAllDevices() {
        return Collections.unmodifiableCollection(catalog.values());
    }

    public Collection<Loan> getActiveLoans() {
        return Collections.unmodifiableCollection(activeLoans.values());
    }
}
