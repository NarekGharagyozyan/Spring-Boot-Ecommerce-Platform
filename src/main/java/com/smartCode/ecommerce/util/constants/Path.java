package com.smartCode.ecommerce.util.constants;

public interface Path {
    String USERS = "/users";
    String PRODUCTS = "/products";
    String CARDS = "/cards";
    String NOTIFY = "/notify";
    String LOGIN = "/login";
    String REGISTER = "/register";
    String FIND = "/find/{id}";
    String VERIFY = "/verify";
    String CHANGE_PASSWORD = "/changePassword";
    String FILTER = "/filter";
    String SEARCH = "/search";
    String FIND_ALL = "/find/all";
    String CREATE = "/create";
    String UPDATE_ALL = "/update/all/{id}";
    String UPDATE = "/update";
    String DELETE = "/delete/{id}";
    String FIND_WITH_OWNER_ID = "/find/{ownerId}";
    String DELETE_WITH_OWNER_ID = "/delete/owner/{ownerId}";
    String DELETE_WITH_CARD_ID = "/delete/{cardId}";
    String LOGOUT = "/logout";
    String READY = "/ready";
    String WAITING = "/waiting";
    String SAVE = "/save";
}
