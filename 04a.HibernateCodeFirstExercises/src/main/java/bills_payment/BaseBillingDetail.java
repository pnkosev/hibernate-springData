//package bills_payment;
//
//import javax.persistence.*;
//
//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//public abstract class BaseBillingDetail {
//    private int number;
//    private Owner owner;
//
//    protected BaseBillingDetail() {
//    }
//
//    @Id
//    public int getNumber() {
//        return this.number;
//    }
//
//    public void setNumber(int number) {
//        this.number = number;
//    }
//
//    @ManyToOne(targetEntity = Owner.class)
//    @JoinColumn(name = "owner_id", referencedColumnName = "id")
//    public Owner getOwner() {
//        return this.owner;
//    }
//
//    public void setOwner(Owner owner) {
//        this.owner = owner;
//    }
//}
