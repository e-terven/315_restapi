package com.katia.spring.security.model;

public class JwtRequest {
        private static final long serialVersionUID = 5926468583005150707L;

        private String email;
        private String password;

        // Необходим пустой конструктор для десериализации
        public JwtRequest() {}

        public JwtRequest(String email, String password) {
                this.email = email;
                this.password = password;
        }

        public String getEmail() {

                return this.email;
        }

        public void setEmail(String username) {
                this.email = email;
        }

        public String getPassword() {
                return this.password;
        }

        public void setPassword(String password) {
                this.password = password;
        }
}
