package com.kh.test;

import java.util.Objects;

public class Member {
    private String id;
    private String name;

    public Member() {}
    public Member(String id, String name) {
        this.id = Objects.requireNonNull(id, "id");
        this.name = Objects.requireNonNull(name, "name");
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = Objects.requireNonNull(id); }
    public String getName() { return name; }
    public void setName(String name) { this.name = Objects.requireNonNull(name); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return String.format("Member{id='%s', name='%s'}", id, name);
    }
}
