package me.isaiah.common.entity;

public interface ICat {

    /**
     * Gets the current type of this cat.
     */
    public CatType get_cat_type();

    /**
     * Sets the current type of this cat.
     */
    public void set_cat_type(CatType type);

    /**
     * Represents the various different cat types there are.
     */
    public enum CatType {
        TABBY,
        BLACK,
        RED,
        SIAMESE,
        BRITISH_SHORTHAIR,
        CALICO,
        PERSIAN,
        RAGDOLL,
        WHITE,
        JELLIE,
        ALL_BLACK;
    }

}