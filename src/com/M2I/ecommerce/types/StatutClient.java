package com.M2I.ecommerce.types;

public enum StatutClient {

	PART("Particulier"), PRO("Professionel");

    private String label;

    StatutClient(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

