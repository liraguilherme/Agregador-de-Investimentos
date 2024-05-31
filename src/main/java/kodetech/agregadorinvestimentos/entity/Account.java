package kodetech.agregadorinvestimentos.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_accounts")
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    @ManyToOne //Varias contas para UM usuario
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "account")
    @PrimaryKeyJoinColumn //esse relacionamento vai passar a primaryKey para o BillingAdress
    private BillingAddress billingAddress;


    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "account") //Uma conta pode consultar varias ações,o mapped pra informar que está mapeado do outro lado
    private List<AccountStock> AccountStocks;

    public Account() {
    }

    public Account(UUID accountId, String description) {
        this.accountId = accountId;
        this.description = description;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
