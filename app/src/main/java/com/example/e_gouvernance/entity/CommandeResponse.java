package com.example.e_gouvernance.entity;

public class CommandeResponse {

    private String _id;
    private DocumentResponse produit;
    private String user;
    private String datecommande;
    private String etat;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public DocumentResponse getProduit() {
        return produit;
    }

    public void setProduit(DocumentResponse produit) {
        this.produit = produit;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(String datecommande) {
        this.datecommande = datecommande;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
