package com.practice.orderservice.entities;

import com.practice.orderservice.entities.enums.OrderStatus;
import com.practice.orderservice.model.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders") // The name order is rejected by multiple db (like h2) because of conflicts with sql keywords like "order by"
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdDate;
    private OrderStatus status;
    private Long customerId;
    @Transient
    private Customer customer;
    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItems;

    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", createdDate=" + createdDate +
            ", status=" + status +
            ", customerId=" + customerId +
            '}';
    }

}
