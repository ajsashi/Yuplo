package com.yuplo.model;

import java.util.List;

public class Model {


    public class Login {
        private Boolean success;
        private String token;
        private String error;

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }

    public class RegisterError {

        private Boolean success;
        private Error error;

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public Error getError() {
            return error;
        }

        public void setError(Error error) {
            this.error = error;
        }

        public class Error {

            private List<String> firstName = null;
            private List<String> email = null;
            private List<String> cPassword = null;

            public List<String> getFirstName() {
                return firstName;
            }

            public void setFirstName(List<String> firstName) {
                this.firstName = firstName;
            }

            public List<String> getEmail() {
                return email;
            }

            public void setEmail(List<String> email) {
                this.email = email;
            }

            public List<String> getCPassword() {
                return cPassword;
            }

            public void setCPassword(List<String> cPassword) {
                this.cPassword = cPassword;
            }

        }
    }
}
