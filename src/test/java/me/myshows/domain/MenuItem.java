package me.myshows.domain;

public enum MenuItem {
    NEWS("Новости"), REIT("Рейтинги");
    public final String rusName;

    MenuItem(String rusName) {
        this.rusName = rusName;
    }
}