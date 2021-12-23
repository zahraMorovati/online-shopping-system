package model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private int nationalCode;
    private String phoneNumber;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;
    @Column(name = "user_name")
    private String userName;
    private String password;
    private double balance;


    @Override
    public String toString() {
        return
                " userName='" + userName + '\'' +
                        ", password='" + password + '\'' +
                        ", name='" + firstName + " " + lastName + '\'' +
                        ", birthDate='" + birthDate +'\'' +
                        ", balance=" + balance;
    }


    public static final class UserBuilder {
        private User user;

        private UserBuilder() {
            user = new User();
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder setId(int id) {
            user.setId(id);
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }

        public UserBuilder setNationalCode(int nationalCode) {
            user.setNationalCode(nationalCode);
            return this;
        }

        public UserBuilder setPhoneNumber(String phoneNumber) {
            user.setPhoneNumber(phoneNumber);
            return this;
        }

        public UserBuilder setBirthDate(Date birthDate) {
            user.setBirthDate(birthDate);
            return this;
        }

        public UserBuilder setEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public UserBuilder setCart(Cart cart) {
            user.setCart(cart);
            return this;
        }

        public UserBuilder setUserName(String userName) {
            user.setUserName(userName);
            return this;
        }

        public UserBuilder setPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public UserBuilder setBalance(double balance) {
            user.setBalance(balance);
            return this;
        }

        public UserBuilder but() {
            return anUser().setId(user.getId()).setFirstName(user.getFirstName()).setLastName(user.getLastName()).setNationalCode(user.getNationalCode()).setPhoneNumber(user.getPhoneNumber()).setBirthDate(user.getBirthDate()).setEmail(user.getEmail()).setCart(user.getCart()).setUserName(user.getUserName()).setPassword(user.getPassword()).setBalance(user.getBalance());
        }

        public User build() {
            return user;
        }
    }
}
