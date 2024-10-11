package com.terror.springcommunity.common;


import com.terror.springcommunity.constans.UserRoleEnum;

public enum TestUserEnum {
    TEST_MEMBER_ONE("test1", "!@skdud340", "test1@naver.com", "test1작성자", UserRoleEnum.USER),
    TEST_MEMBER_TWO("test2", "!@skdud341", "test2@naver.com", "test2작성자", UserRoleEnum.USER),
    TEST_MEMBER_THREE("test3", "!@skdud342", "test3@naver.com", "test3작성자", UserRoleEnum.USER),
    TEST_MEMBER_FOUR("test4", "!@skdud343", "test4@naver.com", "test4작성자", UserRoleEnum.USER),
    TEST_MEMBER_FIVE("test5", "!@skdud344", "test5@naver.com", "test5작성자", UserRoleEnum.USER),
    TEST_MEMBER_ADMIN_ONE("admin1", "!@skdud340", "admin1@naver.com", "test1작성자", UserRoleEnum.ADMIN);

    private final String username;
    private final String password;
    private final String email;
    private final String author;
    private final UserRoleEnum role;

    TestUserEnum(String username, String password, String email, String author, UserRoleEnum userRoleEnum) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.author = author;
        this.role = userRoleEnum;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAuthor() {
        return author;
    }

    public UserRoleEnum getRole() {
        return role;
    }
}
