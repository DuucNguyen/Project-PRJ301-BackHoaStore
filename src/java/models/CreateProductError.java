/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author admin
 */
public class CreateProductError {

    String nameLengthErr;
    String categoryInvalidErr;
    String descriptionInvalidErr;
    String priceInvalidErr;
    String storeQuantiyInvalidErr;
    String imgLocationinvalidErr;

    public CreateProductError() {
    }

    public CreateProductError(String nameLengthErr, String categoryInvalidErr, String descriptionInvalidErr, String priceInvalidErr, String storeQuantiyInvalidErr, String imgLocationinvalidErr) {
        this.nameLengthErr = nameLengthErr;
        this.categoryInvalidErr = categoryInvalidErr;
        this.descriptionInvalidErr = descriptionInvalidErr;
        this.priceInvalidErr = priceInvalidErr;
        this.storeQuantiyInvalidErr = storeQuantiyInvalidErr;
        this.imgLocationinvalidErr = imgLocationinvalidErr;
    }

    public String getNameLengthErr() {
        return nameLengthErr;
    }

    public void setNameLengthErr(String nameLengthErr) {
        this.nameLengthErr = nameLengthErr;
    }

    public String getCategoryInvalidErr() {
        return categoryInvalidErr;
    }

    public void setCategoryInvalidErr(String categoryInvalidErr) {
        this.categoryInvalidErr = categoryInvalidErr;
    }

    public String getDescriptionInvalidErr() {
        return descriptionInvalidErr;
    }

    public void setDescriptionInvalidErr(String descriptionInvalidErr) {
        this.descriptionInvalidErr = descriptionInvalidErr;
    }

    public String getPriceInvalidErr() {
        return priceInvalidErr;
    }

    public void setPriceInvalidErr(String priceInvalidErr) {
        this.priceInvalidErr = priceInvalidErr;
    }

    public String getStoreQuantiyInvalidErr() {
        return storeQuantiyInvalidErr;
    }

    public void setStoreQuantiyInvalidErr(String storeQuantiyInvalidErr) {
        this.storeQuantiyInvalidErr = storeQuantiyInvalidErr;
    }

    public String getImgLocationinvalidErr() {
        return imgLocationinvalidErr;
    }

    public void setImgLocationinvalidErr(String imgLocationinvalidErr) {
        this.imgLocationinvalidErr = imgLocationinvalidErr;
    }

}
