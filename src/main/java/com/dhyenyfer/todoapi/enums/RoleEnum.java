package com.dhyenyfer.todoapi.enums;

// Enum é um tipo especial em Java usado para representar um conjunto fixo de constantes
public enum RoleEnum {

    // Definição dos valores possíveis do enum
    // Cada um chama o construtor passando um valor String
    ADMIN ("admin"),
    USER ("user");

    private final String role;

    RoleEnum (String role) {
        this.role = role;
    }

    public String getRole (){
        return role;
    }
}
